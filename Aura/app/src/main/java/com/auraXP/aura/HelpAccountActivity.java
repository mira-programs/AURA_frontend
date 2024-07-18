package com.auraXP.aura;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HelpAccountActivity extends AppCompatActivity {

    private LinearLayout linearLayout2;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText messageEditText;
    private Button saveChangesButton;
    private LinearLayout topCardLayout;
    private TextView textView;
    private TextView helpAccountHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_account);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_friends:
                    startActivity(new Intent(HelpAccountActivity.this, FriendsActivity.class));
                    return true;
                case R.id.navigation_center:
                    startActivity(new Intent(HelpAccountActivity.this, DailyChallenges.class));
                    return true;
                case R.id.navigation_account:
                    startActivity(new Intent(HelpAccountActivity.this, AccountActivity.class));
                    return true;
            }
            return false;
        });

        // Initialize views
        linearLayout2 = findViewById(R.id.linearLayout2);
        firstNameEditText = findViewById(R.id.first_name);
        lastNameEditText = findViewById(R.id.last_name);
        emailEditText = findViewById(R.id.email);
        messageEditText = findViewById(R.id.message);
        saveChangesButton = findViewById(R.id.save_changes_button);
        topCardLayout = findViewById(R.id.top_card_layout);
        textView = findViewById(R.id.textView);
        helpAccountHeader = findViewById(R.id.help_account_header);

        // Set up any necessary listeners
        saveChangesButton.setOnClickListener(view -> {
            // Save changes action
            saveChanges();
        });
    }

    private void saveChanges() {
        // Implement the save changes logic here
        // You can get the text from the EditTexts and process it as needed
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String message = messageEditText.getText().toString();

        // For example, show a Toast message (you can replace this with actual save logic)
        Toast.makeText(this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
    }
}
