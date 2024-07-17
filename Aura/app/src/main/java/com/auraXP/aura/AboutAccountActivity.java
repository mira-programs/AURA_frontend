package com.auraXP.aura;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

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

    }
}
