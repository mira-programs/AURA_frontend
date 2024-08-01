package com.auraXP.aura;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.auraXP.aura.api.ApiService;
import com.auraXP.aura.api.ApiServiceInstance;
import com.auraXP.aura.api.GeminiResponse;
import com.auraXP.aura.api.GeminiService;
import com.auraXP.aura.api.models.Challenge;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CAMERA;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class DailyChallenges extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 100;
    private static final String STATE_LAYOUT = "currentLayout";
    private static final String STATE_IMAGE = "capturedImage";
    private static final String STATE_CHALLENGE_STATUS = "challengeStatus";

    private ImageView imageView;
    private Button btnTakePicture;
    private LinearLayout layoutAcceptDailyChallenge;
    private LinearLayout layoutAcceptedChallenge;
    private LinearLayout layoutChallengeCompleted;
    private TextView tvChallengeStatus;
    private Bitmap capturedImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_challenges);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_friends:
                    startActivity(new Intent(DailyChallenges.this, FriendsActivity.class));
                    return true;
                case R.id.navigation_center:
                    startActivity(new Intent(DailyChallenges.this, DailyChallenges.class));
                    return true;
                case R.id.navigation_account:
                    startActivity(new Intent(DailyChallenges.this, AccountActivity.class));
                    return true;
            }
            return false;
        });

        fetchChallenges();

        imageView = findViewById(R.id.ivTakenImage);
        btnTakePicture = findViewById(R.id.btnTakePicture);
        tvChallengeStatus = findViewById(R.id.tvChallengeStatus);

        layoutAcceptDailyChallenge = findViewById(R.id.layoutAcceptDailyChallenge);
        layoutAcceptedChallenge = findViewById(R.id.layoutAcceptedChallenge);
        layoutChallengeCompleted = findViewById(R.id.layoutChallengeCompleted);

        if (savedInstanceState != null) {
            int currentLayout = savedInstanceState.getInt(STATE_LAYOUT);
            if (currentLayout == R.id.layoutAcceptDailyChallenge) {
                showAcceptDailyChallenge();
            } else if (currentLayout == R.id.layoutAcceptedChallenge) {
                showAcceptedChallenge();
            } else if (currentLayout == R.id.layoutChallengeCompleted) {
                showChallengeCompleted();
            }

            capturedImageBitmap = savedInstanceState.getParcelable(STATE_IMAGE);
            if (capturedImageBitmap != null) {
                imageView.setImageBitmap(capturedImageBitmap);
                imageView.setVisibility(View.VISIBLE);
                toggleButton();
            }

            String challengeStatus = savedInstanceState.getString(STATE_CHALLENGE_STATUS);
            if (challengeStatus != null) {
                tvChallengeStatus.setText(challengeStatus);
            }
        } else {
            showAcceptDailyChallenge();
        }

        boolean challengeCompleted = getIntent().getBooleanExtra("challengeCompleted", false);
        if (challengeCompleted) {
            showChallengeCompleted();
        } else {
            showAcceptDailyChallenge();
        }

        btnTakePicture.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(DailyChallenges.this, CAMERA) != PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(DailyChallenges.this, new String[]{CAMERA}, REQUEST_PERMISSION);
            } else {
                dispatchTakePictureIntent();
            }
        });
    }

    private final ActivityResultLauncher<Intent> takePictureLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Bundle extras = result.getData().getExtras();
                    capturedImageBitmap = (Bitmap) extras.get("data");
                    if (capturedImageBitmap != null) {
                        imageView.setImageBitmap(capturedImageBitmap);
                        imageView.setVisibility(View.VISIBLE);
                        toggleButton();
                        tvChallengeStatus.setText(getString(R.string.challenge_completed_message));
                    }
                }
            }
    );

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            takePictureLauncher.launch(takePictureIntent);
        }
    }

    private void toggleButton() {
        if (btnTakePicture.getText().equals(getString(R.string.btnPicture))) {
            btnTakePicture.setText(R.string.btnSubmitImage);
            btnTakePicture.setOnClickListener(v -> submitImageToGemini());
        } else {
            btnTakePicture.setOnClickListener(v -> dispatchTakePictureIntent());
        }
    }

    private void submitImageToGemini() {
        File file = new File(getExternalFilesDir(null), "temp.jpg");
        try (FileOutputStream out = new FileOutputStream(file)) {
            capturedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            Log.d("success", "compressed image");
        } catch (IOException e) {
            Log.e("DailyChallenges", "Error saving image", e);
            return;
        }

        // Create RequestBody for the image file
        RequestBody requestFile = RequestBody.create(file, MediaType.parse("image/jpeg"));
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        // Create RequestBody for the description
        RequestBody descriptionBody = RequestBody.create("Describe your challenge here", MediaType.parse("text/plain"));

        GeminiService service = ApiServiceInstance.getGeminiService();
        Call<GeminiResponse> call = service.processImage(body, descriptionBody);

        call.enqueue(new Callback<GeminiResponse>() {
            @Override
            public void onResponse(@NonNull Call<GeminiResponse> call, @NonNull Response<GeminiResponse> response) {
                if (response.isSuccessful()) {
                    GeminiResponse geminiResponse = response.body();
                    if (geminiResponse != null && geminiResponse.getDescription().equalsIgnoreCase("challenge completed")) {
                        Intent intent = new Intent(DailyChallenges.this, ChallengeCompleted.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(DailyChallenges.this, ChallengeFailed.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(DailyChallenges.this, "Failed to process image", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GeminiResponse> call, @NonNull Throwable t) {
                Toast.makeText(DailyChallenges.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onAcceptDailyChallengeClicked(View view) {
        showAcceptedChallenge();
    }

    private void showAcceptDailyChallenge() {
        layoutAcceptDailyChallenge.setVisibility(View.VISIBLE);
        layoutAcceptedChallenge.setVisibility(View.GONE);
        layoutChallengeCompleted.setVisibility(View.GONE);
    }

    private void showAcceptedChallenge() {
        layoutAcceptDailyChallenge.setVisibility(View.GONE);
        layoutAcceptedChallenge.setVisibility(View.VISIBLE);
        layoutChallengeCompleted.setVisibility(View.GONE);
    }

    private void showChallengeCompleted() {
        layoutAcceptDailyChallenge.setVisibility(View.GONE);
        layoutAcceptedChallenge.setVisibility(View.GONE);
        layoutChallengeCompleted.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (layoutAcceptDailyChallenge.getVisibility() == View.VISIBLE) {
            savedInstanceState.putInt(STATE_LAYOUT, R.id.layoutAcceptDailyChallenge);
        } else if (layoutAcceptedChallenge.getVisibility() == View.VISIBLE) {
            savedInstanceState.putInt(STATE_LAYOUT, R.id.layoutAcceptedChallenge);
        } else if (layoutChallengeCompleted.getVisibility() == View.VISIBLE) {
            savedInstanceState.putInt(STATE_LAYOUT, R.id.layoutChallengeCompleted);
        }

        savedInstanceState.putParcelable(STATE_IMAGE, capturedImageBitmap);
        savedInstanceState.putString(STATE_CHALLENGE_STATUS, tvChallengeStatus.getText().toString());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void fetchChallenges() {
        ApiService apiService = ApiServiceInstance.getApiService();
        Call<List<Challenge>> call = apiService.readChallenges(0, 10); // Adjust skip and limit as needed

        call.enqueue(new Callback<List<Challenge>>() {
            @Override
            public void onResponse(@NonNull Call<List<Challenge>> call, @NonNull Response<List<Challenge>> response) {
                if (response.isSuccessful()) {
                    List<Challenge> challenges = response.body();
                    Log.d("API Response", "Challenges: " + challenges);
                    if (challenges != null && !challenges.isEmpty()) {
                        displayChallenges(challenges);
                    } else {
                        Toast.makeText(DailyChallenges.this, "No challenges available", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DailyChallenges.this, "Failed to fetch challenges", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Challenge>> call, @NonNull Throwable t) {
                Toast.makeText(DailyChallenges.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("haha loser", "failed");
            }
        });
    }

    private void displayChallenges(List<Challenge> challenges) {
        ListView lvMiniChallenges = findViewById(R.id.lvMiniChallenges);
        ChallengeAdapter adapter = new ChallengeAdapter(this, challenges);
        lvMiniChallenges.setAdapter(adapter);
    }
}
