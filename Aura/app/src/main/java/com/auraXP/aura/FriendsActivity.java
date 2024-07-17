package com.auraXP.aura;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {

    private ListView friendsListView;
    private FriendsAdapter friendsAdapter;
    private TextView header;
    private List<String> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        friends = new ArrayList<>();
        // Add sample data
        friends.add("Maria Eid");
        friends.add("Mira Hussein");
        friends.add("Mariam Sonji");

        header = findViewById(R.id.header);
        friendsListView = findViewById(R.id.friends_list);
        friendsAdapter = new FriendsAdapter(this, friends);
        friendsListView.setAdapter(friendsAdapter);

        Button friendsButton = findViewById(R.id.friends_button);
        Button leaderboardButton = findViewById(R.id.leaderboard_button);

        friendsButton.setOnClickListener(v -> showFriends());
        leaderboardButton.setOnClickListener(v -> showLeaderboard());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_friends:
                        startActivity(new Intent(FriendsActivity.this, FriendsActivity.class));
                        return true;
                    case R.id.navigation_center:
                        // Handle the center button click
                        return true;
                    case R.id.navigation_account:
                        startActivity(new Intent(FriendsActivity.this, AccountActivity.class));
                        return true;
                }
                return false;
            }
        });
    }

    private void showFriends() {
        header.setText("Friends");
        // Update the adapter data if needed
    }

    private void showLeaderboard() {
        header.setText("Leaderboard");
        // Update the adapter data to show leaderboard information
    }
}
