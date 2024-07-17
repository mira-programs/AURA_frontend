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
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.Objects;
import static android.Manifest.permission.CAMERA;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class DailyChallenges extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PERMISSION = 100;
    private ImageView imageView;
    private Button btnTakePicture;

    private LinearLayout layoutAcceptDailyChallenge;
    private LinearLayout layoutAcceptedChallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_challenges);

        imageView = findViewById(R.id.ivTakenImage);
        btnTakePicture = findViewById(R.id.btnTakePicture);

        layoutAcceptDailyChallenge = findViewById(R.id.layoutAcceptDailyChallenge);
        layoutAcceptedChallenge = findViewById(R.id.layoutAcceptedChallenge);

        showAcceptDailyChallenge();

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
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    if (imageBitmap != null) {
                        // Display the captured image in your ImageView
                        imageView.setImageBitmap(imageBitmap);
                        imageView.setVisibility(View.VISIBLE);

                        // Update the button text and behavior
                        toggleButton();
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = Objects.requireNonNull(data).getExtras();
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap((android.graphics.Bitmap) extras.get("data"));
            toggleButton();
        }
    }

    private void toggleButton() {
        if (btnTakePicture.getText().equals(getString(R.string.btnPicture))) {
            btnTakePicture.setText(R.string.btnSubmitImage);
            btnTakePicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle submit image action
                    Toast.makeText(DailyChallenges.this, "Image Submitted", Toast.LENGTH_SHORT).show();
                    // Optionally, reset the button and image view
                    resetButtonAndImage();
                }
            });
        } else {
            // Reset to "Take Picture" state
            btnTakePicture.setText(R.string.btnPicture);
            btnTakePicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Dispatch the camera intent again
                    dispatchTakePictureIntent();
                }
            });
        }
    }

    private void resetButtonAndImage() {
        // Reset button text and behavior
        btnTakePicture.setText(R.string.btnPicture);
        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dispatch the camera intent again
                dispatchTakePictureIntent();
            }
        });

        // Clear the ImageView
        imageView.setImageResource(0);
        imageView.setVisibility(View.GONE);
    }

    public void onAcceptDailyChallengeClicked(View view) {
        showAcceptedChallenge();
    }

    private void showAcceptDailyChallenge() {
        layoutAcceptDailyChallenge.setVisibility(View.VISIBLE);
        layoutAcceptedChallenge.setVisibility(View.GONE);
    }

    private void showAcceptedChallenge() {
        layoutAcceptDailyChallenge.setVisibility(View.GONE);
        layoutAcceptedChallenge.setVisibility(View.VISIBLE);
    }
}
