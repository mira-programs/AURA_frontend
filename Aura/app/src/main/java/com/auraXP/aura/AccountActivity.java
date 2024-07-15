package com.auraXP.aura;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {

    private TextView accountHeader;
    private TextView accountUsername;
    private ImageButton profileIcon;
    private ImageButton auraPointsIcon;
    private ImageButton challengesAchievedIcon;
    private Button settingsButton;
    private Button privacyButton;
    private Button languageButton;
    private Button helpButton;
    private Button termsButton;
    private Button aboutButton;
    private Button logoutButton;
    private ImageButton friendsNav;
    private ImageView centerNav;
    private ImageButton accountNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Binding views
        accountHeader = findViewById(R.id.account_header2);
        accountUsername = findViewById(R.id.account_username);
        profileIcon = findViewById(R.id.profile_icon);
        auraPointsIcon = findViewById(R.id.aura_points_icon);
        challengesAchievedIcon = findViewById(R.id.challenges_achieved_icon);
        settingsButton = findViewById(R.id.settings_button);
        privacyButton = findViewById(R.id.privacy_button);
        languageButton = findViewById(R.id.language_button);
        helpButton = findViewById(R.id.help_button);
        termsButton = findViewById(R.id.terms_button);
        aboutButton = findViewById(R.id.about_button);
        logoutButton = findViewById(R.id.btn_logout);
//        friendsNav = findViewById(R.id.friends_nav);
//        centerNav = findViewById(R.id.center_nav);
//        accountNav = findViewById(R.id.account_nav);

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
                Intent intent = new Intent (AccountActivity.this, AurapointsAccountActivity.class);
                startActivity(intent);
            }
        });

        challengesAchievedIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (AccountActivity.this, ChallengesAccountActivity.class);
                startActivity(intent);
            }
        });


        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        privacyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (AccountActivity.this, PrivacyAccountActivity.class);
                startActivity(intent);
            }
        });

        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        termsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        friendsNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (AccountActivity.this, FriendsActivity.class);
                startActivity(intent);
            }
        });

        centerNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (AccountActivity.this, DailyChallenges.class);
                startActivity(intent);
            }
        });

        accountNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is it
            }
        });
    }
}
