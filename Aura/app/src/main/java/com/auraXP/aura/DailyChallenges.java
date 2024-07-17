package com.auraXP.aura;

import android.app.Activity;
import android.content.Intent;
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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.Manifest.permission.CAMERA;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class DailyChallenges extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PERMISSION = 100;
    private ImageView imageView;
    private Button btnTakePicture;

    private LinearLayout layoutAcceptDailyChallenge;
    private LinearLayout layoutAcceptedChallenge;
    private LinearLayout layoutChallengeCompleted;
    private TextView tvChallengeStatus; // Added TextView for challenge status

    private Bitmap capturedImageBitmap; // Store the captured image bitmap

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_challenges);

        imageView = findViewById(R.id.ivTakenImage);
        btnTakePicture = findViewById(R.id.btnTakePicture);
        tvChallengeStatus = findViewById(R.id.tvChallengeStatus); // Initialize TextView

        layoutAcceptDailyChallenge = findViewById(R.id.layoutAcceptDailyChallenge);
        layoutAcceptedChallenge = findViewById(R.id.layoutAcceptedChallenge);
        layoutChallengeCompleted = findViewById(R.id.layoutChallengeCompleted);

        showAcceptDailyChallenge();

        boolean challengeCompleted = getIntent().getBooleanExtra("challengeCompleted", false);
        if (challengeCompleted) {
            showChallengeCompleted(); // Show the layout for the completed challenge
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
                    // Handle the captured image here
                    Bundle extras = result.getData().getExtras();
                    capturedImageBitmap = (Bitmap) extras.get("data");
                    if (capturedImageBitmap != null) {
                        // Display the captured image in your ImageView
                        imageView.setImageBitmap(capturedImageBitmap);
                        imageView.setVisibility(View.VISIBLE);

                        // Update the button text and behavior
                        toggleButton();

                        // Update challenge status text
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
                    Toast.makeText(DailyChallenges.this, "Image Submitted", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(DailyChallenges.this, ChallengeCompleted.class);
                    intent.putExtra("capturedImageBitmap", capturedImageBitmap);
                    startActivity(intent);
                }
            });
        } else {
            // Keep the button text as "Submit Image" state
            btnTakePicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dispatchTakePictureIntent();
                }
            });
        }
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
        layoutChallengeCompleted.setVisibility(View.VISIBLE); // Show layoutChallengeCompleted
    }
}
