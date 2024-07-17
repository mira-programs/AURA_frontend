package com.auraXP.aura;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {

    private ListView friendsListView;
    private FriendsAdapter friendsAdapter;
    TextView header;
    private EditText searchBar;
    private List<String> friends;
    private List<String> leaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        // Initialize data
        friends = new ArrayList<>();
        friends.add("Maria Eid");
        friends.add("Mira Hussein");
        friends.add("Mariam Sonji");

        leaderboard = new ArrayList<>();
        leaderboard.add("1. John Doe: 1500 AURA");
        leaderboard.add("2. Jane Smith - 1400 AURA");
        leaderboard.add("3. Alice Johnson - 1300 AURA");

        header = findViewById(R.id.header);
        searchBar = findViewById(R.id.search_bar);
        friendsListView = findViewById(R.id.friends_list);

        // Initialize adapter for Friends section
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

        // Initialize with Friends section
        showFriends();
    }

    private void showFriends() {
        header.setText("Friends");
        searchBar.setVisibility(android.view.View.VISIBLE);
        friendsListView.setVisibility(android.view.View.VISIBLE);
        friendsAdapter.setFriends(friends);  // Update the adapter data
        friendsAdapter.notifyDataSetChanged();
    }

    private void showLeaderboard() {
        header.setText("Leaderboard");
        searchBar.setVisibility(android.view.View.GONE);
        friendsListView.setVisibility(android.view.View.VISIBLE);
        friendsAdapter.setFriends(leaderboard);  // Update the adapter data for leaderboard
        friendsAdapter.notifyDataSetChanged();
    }

    public void addFriend(String friendName) {
        // Add the friend to your friends list or perform other actions
        // For now, just show a Toast message
        Toast.makeText(this, "Sent " + friendName + " a friend request!", Toast.LENGTH_SHORT).show();
    }
}
