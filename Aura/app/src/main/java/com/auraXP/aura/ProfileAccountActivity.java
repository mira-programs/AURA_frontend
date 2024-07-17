package com.auraXP.aura;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLOutput;

public class ProfileAccountActivity extends AppCompatActivity {

    private Button saveChangesButton;
    private Button changePasswordButton;
    private Button deleteAccountButton;
    private Button signOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_account);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        saveChangesButton = findViewById(R.id.save_changes_button);
        changePasswordButton = findViewById(R.id.change_password_button);
        deleteAccountButton = findViewById(R.id.delete_account_button);
        signOutButton = findViewById(R.id.sign_out_button);

        //listeners
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveChanges();
            }
        });

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });

        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAccount();
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }

    private void saveChanges() {
        //saving data to the database (?)
        Toast.makeText(ProfileAccountActivity.this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
    }

    private void changePassword() {
//       //new activity for password changing
        Intent intent = new Intent(ProfileAccountActivity.this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    private void deleteAccount() {
            new AlertDialog.Builder(ProfileAccountActivity.this)
                    .setTitle("Delete Account")
                    .setMessage("Are you sure you want to delete your account?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // deletion in the backend
                            Toast.makeText(ProfileAccountActivity.this, "Account deleted successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

    }

    private void signOut() {
        Intent intent = new Intent(ProfileAccountActivity.this, LoginActivity.class); //back to login
        startActivity(intent);
        finish(); //close activity
    }
}