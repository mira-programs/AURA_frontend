package com.auraXP.aura;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText etUsernameLogin = findViewById(R.id.etUsernameLogin);
    EditText etPasswordLogin = findViewById(R.id.etPasswordLogin);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    Intent intent = new Intent(LoginActivity.this, DailyChallenges.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean validate() {
        boolean isValid =true;

        if(TextUtils.isEmpty(etUsernameLogin.getText().toString())) {
            etUsernameLogin.setError("Username is required.");
            isValid = false;
        }
        if (TextUtils.isEmpty(etPasswordLogin.getText().toString())) {
            etPasswordLogin.setError("Password is required.");
            isValid = false;
        }

        return isValid;
    }
}