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

import com.auraXP.aura.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button loginButton = findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(view -> {
            if (validate()) {
                Intent intent = new Intent(LoginActivity.this, DailyChallenges.class);
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean validate() {
        boolean isValid = true;

        if (TextUtils.isEmpty(binding.etUsernameLogin.getText().toString())) {
            binding.etUsernameLogin.setError("Username is required.");
            isValid = false;
        }
        if (TextUtils.isEmpty(binding.etPasswordLogin.getText().toString())) {
            binding.etPasswordLogin.setError("Password is required.");
            isValid = false;
        }

        return isValid;
    }
}
