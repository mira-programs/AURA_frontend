package com.auraXP.aura;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FriendsActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "friends_prefs";
    private static final String REQUESTED_FRIENDS_KEY = "requested_friends";

    private ListView friendsListView;
    private FriendsAdapter friendsAdapter;
    TextView header;
    private EditText searchBar;
    private List<String> friends;
    private List<String> leaderboard;
    private Set<String> requestedFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Initialize data
        friends = new ArrayList<>();
        friends.add("Maria Eid");
        friends.add("Mira Hussein");
        friends.add("Mariam Sonji");

        leaderboard = new ArrayList<>();
        leaderboard.add("1. John Doe - 1500 Points");
        leaderboard.add("2. Jane Smith - 1400 Points");
        leaderboard.add("3. Alice Johnson - 1300 Points");

        header = findViewById(R.id.header_title);
        searchBar = findViewById(R.id.search_bar);
        friendsListView = findViewById(R.id.friends_list);

        // Load requested friends from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        requestedFriends = sharedPreferences.getStringSet(REQUESTED_FRIENDS_KEY, new HashSet<>());

        // Initialize adapter for Friends section
        friendsAdapter = new FriendsAdapter(this, friends, requestedFriends);
        friendsListView.setAdapter(friendsAdapter);

        Button friendsButton = findViewById(R.id.friends_button);
        Button leaderboardButton = findViewById(R.id.leaderboard_button);

        friendsButton.setOnClickListener(v -> showFriends());
        leaderboardButton.setOnClickListener(v -> showLeaderboard());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_friends:
                    startActivity(new Intent(FriendsActivity.this, FriendsActivity.class));
                    return true;
                case R.id.navigation_center:
                    startActivity(new Intent(FriendsActivity.this, DailyChallenges.class));
                    return true;
                case R.id.navigation_account:
                    startActivity(new Intent(FriendsActivity.this, AccountActivity.class));
                    return true;
            }
            return false;
        });

        // Initialize with Friends section
        showFriends();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Save requested friends to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(REQUESTED_FRIENDS_KEY, requestedFriends);
        editor.apply();
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
        requestedFriends.add(friendName);
        friendsAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Sent " + friendName + " a friend request!", Toast.LENGTH_SHORT).show();
    }
}
