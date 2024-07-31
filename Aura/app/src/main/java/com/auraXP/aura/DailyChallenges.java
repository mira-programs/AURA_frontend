package com.auraXP.aura;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.Manifest.permission.CAMERA;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DailyChallenges extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
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

        imageView = findViewById(R.id.ivTakenImage);
        btnTakePicture = findViewById(R.id.btnTakePicture);
        tvChallengeStatus = findViewById(R.id.tvChallengeStatus);

        layoutAcceptDailyChallenge = findViewById(R.id.layoutAcceptDailyChallenge);
        layoutAcceptedChallenge = findViewById(R.id.layoutAcceptedChallenge);
        layoutChallengeCompleted = findViewById(R.id.layoutChallengeCompleted);

        if (savedInstanceState != null) {
            // Restore the saved state
            int currentLayout = savedInstanceState.getInt(STATE_LAYOUT);
            if (currentLayout == R.id.layoutAcceptDailyChallenge) {
                showAcceptDailyChallenge();
            } else if (currentLayout == R.id.layoutAcceptedChallenge) {
                showAcceptedChallenge();
            } else if (currentLayout == R.id.layoutChallengeCompleted) {
                showChallengeCompleted();
            }

            // Restore the captured image
            capturedImageBitmap = savedInstanceState.getParcelable(STATE_IMAGE);
            if (capturedImageBitmap != null) {
                imageView.setImageBitmap(capturedImageBitmap);
                imageView.setVisibility(View.VISIBLE);
                toggleButton();
            }

            // Restore challenge status text
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

        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(DailyChallenges.this, CAMERA) != PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(DailyChallenges.this, new String[]{CAMERA}, REQUEST_PERMISSION);
                } else {
                    dispatchTakePictureIntent();
                }
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
            btnTakePicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    submitImageToGemini();
                }
            });
        } else {
            btnTakePicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dispatchTakePictureIntent();
                }
            });
        }
    }

    private void submitImageToGemini() {
        // Convert Bitmap to File
        File file = new File(getExternalFilesDir(null), "temp.jpg");
        try (FileOutputStream out = new FileOutputStream(file)) {
            capturedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Prepare the file for upload
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        // Create Retrofit client and service
        Retrofit retrofit = RetrofitClient.getClient("http://<127.0.0.1>:8000");
        GeminiService service = retrofit.create(GeminiService.class);

        // Make the network request
        Call<GeminiResponse> call = service.processImage(body);
        call.enqueue(new Callback<GeminiResponse>() {
            @Override
            public void onResponse(Call<GeminiResponse> call, Response<GeminiResponse> response) {
                if (response.isSuccessful()) {
                    GeminiResponse geminiResponse = response.body();
                    if (geminiResponse.getDescription().equalsIgnoreCase("challenge completed")) {
                        // Navigate to ChallengeCompleted activity
                        Intent intent = new Intent(DailyChallenges.this, ChallengeCompleted.class);
                        startActivity(intent);
                    } else {
                        // Navigate to ChallengeFailed activity
                        Intent intent = new Intent(DailyChallenges.this, ChallengeFailed.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(DailyChallenges.this, "Failed to process image", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GeminiResponse> call, Throwable t) {
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

        // Save the current layout visibility state
        if (layoutAcceptDailyChallenge.getVisibility() == View.VISIBLE) {
            savedInstanceState.putInt(STATE_LAYOUT, R.id.layoutAcceptDailyChallenge);
        } else if (layoutAcceptedChallenge.getVisibility() == View.VISIBLE) {
            savedInstanceState.putInt(STATE_LAYOUT, R.id.layoutAcceptedChallenge);
        } else if (layoutChallengeCompleted.getVisibility() == View.VISIBLE) {
            savedInstanceState.putInt(STATE_LAYOUT, R.id.layoutChallengeCompleted);
        }

        // Save the captured image bitmap
        savedInstanceState.putParcelable(STATE_IMAGE, capturedImageBitmap);

        // Save the challenge status text
        savedInstanceState.putString(STATE_CHALLENGE_STATUS, tvChallengeStatus.getText().toString());
    }
}
