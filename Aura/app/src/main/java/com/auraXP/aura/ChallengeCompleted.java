package com.auraXP.aura;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChallengeCompleted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_completed);

        Intent intent = getIntent();
        intent.getExtras();
    }

    public void onClickGoHome(View view) {
        // Navigate back to DailyChallenges activity and indicate challenge completion
        Intent intent = new Intent(ChallengeCompleted.this, DailyChallenges.class);
        intent.putExtra("challengeCompleted", true); // Add extra to indicate challenge completion
        startActivity(intent);
    }
}