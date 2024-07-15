package com.auraXP.aura;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalQuestions2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_questions2);

        Button buttonStudent = findViewById(R.id.ansStudent);
        Button buttonWorking = findViewById(R.id.ansWorking);
        Button buttonUnemployed = findViewById(R.id.ansUnemployed);
        Button buttonParent = findViewById(R.id.ansParent);
        TextView textViewSkip = findViewById(R.id.textViewSkip);

        buttonStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click
                navigateToNextActivity();
            }
        });

        buttonWorking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click
                navigateToNextActivity();
            }
        });

        buttonUnemployed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click
                navigateToNextActivity();
            }
        });

        buttonParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click
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
        Intent intent = new Intent(PersonalQuestions2.this, Tutorial.class);
        startActivity(intent);
    }
}
