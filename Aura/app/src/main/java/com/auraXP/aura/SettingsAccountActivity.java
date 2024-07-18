package com.auraXP.aura;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsAccountActivity extends AppCompatActivity {


    private Button notificationsButton;
    private Button streaksButton;
    private Button restrictedButton;
    private Button datetimeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings_account);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_friends:
                    startActivity(new Intent(SettingsAccountActivity.this, FriendsActivity.class));
                    return true;
                case R.id.navigation_center:
                    startActivity(new Intent(SettingsAccountActivity.this, DailyChallenges.class));
                    return true;
                case R.id.navigation_account:
                    startActivity(new Intent(SettingsAccountActivity.this, AccountActivity.class));
                    return true;
            }
            return false;
        });
        notificationsButton = findViewById(R.id.notifications_button);
        streaksButton = findViewById(R.id.streaks_button);
        restrictedButton = findViewById(R.id.restricted_button);
        datetimeButton = findViewById(R.id.datetime_button);



        notificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(SettingsAccountActivity.this, ProfileAccountActivity.class);
               // startActivity(intent);
            }
        });

        streaksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent (SettingsAccountActivity.this, AurapointsAccountActivity.class);
              //  startActivity(intent);
            }
        });

        restrictedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(SettingsAccountActivity.this, ProfileAccountActivity.class);
              //  startActivity(intent);
            }
        });

        datetimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent (SettingsAccountActivity.this, AurapointsAccountActivity.class);
                // startActivity(intent);
            }
        });
    }
}