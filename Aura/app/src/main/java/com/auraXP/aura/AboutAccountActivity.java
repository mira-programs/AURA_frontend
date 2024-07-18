package com.auraXP.aura;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutAccountActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private LinearLayout topCardLayout;
    private TextView aboutTextView;
    private TextView aboutAccountHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_account);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        scrollView = findViewById(R.id.scrollView);
        topCardLayout = findViewById(R.id.top_card_layout);
        aboutTextView = findViewById(R.id.aboutTextView);
        aboutAccountHeader = findViewById(R.id.about_account_header);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_friends:
                    startActivity(new Intent(AboutAccountActivity.this, FriendsActivity.class));
                    return true;
                case R.id.navigation_center:
                    startActivity(new Intent(AboutAccountActivity.this, DailyChallenges.class));
                    return true;
                case R.id.navigation_account:
                    startActivity(new Intent(AboutAccountActivity.this, AccountActivity.class));
                    return true;
            }
            return false;
        });
    }
}
