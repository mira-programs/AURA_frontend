package com.auraXP.aura;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {

    private TextView accountHeader;
    private ImageButton profileIcon;
    private ImageButton auraPointsIcon;
    private ImageButton challengesAchievedIcon;
    private Button settingsButton;
    private Button privacyButton;
    private Button languageButton;
    private Button helpButton;
    private Button aboutButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Binding views
        accountHeader = findViewById(R.id.textView);
        profileIcon = findViewById(R.id.profile_icon);
        auraPointsIcon = findViewById(R.id.aura_points_icon);
        challengesAchievedIcon = findViewById(R.id.challenges_achieved_icon);
        settingsButton = findViewById(R.id.settings_button);
        privacyButton = findViewById(R.id.privacy_button);
        languageButton = findViewById(R.id.language_button);
        helpButton = findViewById(R.id.help_button);
        aboutButton = findViewById(R.id.about_button);
        logoutButton = findViewById(R.id.btn_logout);

        // Setting click listeners
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, ProfileAccountActivity.class);
                startActivity(intent);
            }
        });

        auraPointsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, AurapointsAccountActivity.class);
                startActivity(intent);
            }
        });

        challengesAchievedIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, ChallengesAccountActivity.class);
                startActivity(intent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, SettingsAccountActivity.class);
                startActivity(intent);
            }
        });

        privacyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, PrivacyAccountActivity.class);
                startActivity(intent);
            }
        });

        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, LanguageAccountActivity.class);
                startActivity(intent);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle help button click
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, AboutAccountActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Close activity
            }
        });
    }
}
