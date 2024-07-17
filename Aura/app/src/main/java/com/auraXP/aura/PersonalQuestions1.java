package com.auraXP.aura;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalQuestions1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_questions1);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button buttonLessThan18 = findViewById(R.id.button_less_than_18);
        Button button18To21 = findViewById(R.id.button_18_21);
        Button button22To26 = findViewById(R.id.button_22_26);
        Button buttonMoreThan27 = findViewById(R.id.button_more_than_27);
        TextView textViewSkip = findViewById(R.id.textViewSkip);

        buttonLessThan18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click for "Less than 18"
                navigateToNextActivity();
            }
        });

        button18To21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click for "18 to 21"
                navigateToNextActivity();
            }
        });

        button22To26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click for "22 to 26"
                navigateToNextActivity();
            }
        });

        buttonMoreThan27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click for "More than 27"
                navigateToNextActivity();
            }
        });

        textViewSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle skip button click
                navigateToNextActivity();
            }
        });
    }

    private void navigateToNextActivity() {
        Intent intent = new Intent(PersonalQuestions1.this, PersonalQuestions2.class);
        startActivity(intent);
    }
}
