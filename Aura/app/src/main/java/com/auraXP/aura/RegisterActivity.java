package com.auraXP.aura;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsernameSignup;
    EditText etPasswordSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        etUsernameSignup = findViewById(R.id.etUsername);
        etPasswordSignup = findViewById(R.id.etPassword);

        Button signUpButton = findViewById(R.id.buttonSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    Intent intent = new Intent(RegisterActivity.this, PersonalQuestions1.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean validate() {
        boolean isValid = true;

        if (TextUtils.isEmpty(etUsernameSignup.getText().toString())) {
            etUsernameSignup.setError("Username is required.");
            isValid = false;
        }
        if (TextUtils.isEmpty(etPasswordSignup.getText().toString())) {
            etPasswordSignup.setError("Password is required.");
            isValid = false;
        }

        return isValid;
    }
}
