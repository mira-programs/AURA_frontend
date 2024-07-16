package com.auraXP.aura;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FriendsAdapter adapter;
    private List<Friend> friendsList;
    private Button friendsTab, leaderboardTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        friendsList = new ArrayList<>();

        // Add some sample data
        friendsList.add(new Friend("Maria Eid", "759 XP"));
        friendsList.add(new Friend("Mira Hussein", "759 XP"));
        friendsList.add(new Friend("Mariam Sonji", "759 XP"));
        // Add more friends as needed

        // Initialize adapter with the list of friends
        adapter = new FriendsAdapter(friendsList);
        recyclerView.setAdapter(adapter);

        // Initialize tabs
        friendsTab = findViewById(R.id.friends_tab);
        leaderboardTab = findViewById(R.id.leaderboard_tab);

        // Set tab click listeners
        friendsTab.setOnClickListener(v -> showFriends());
        leaderboardTab.setOnClickListener(v -> showLeaderboard());

        // TODO: Implement search functionality for friends
    }

    private void showFriends() {
        // TODO: Implement showFriends
    }

    private void showLeaderboard() {
        // TODO: Implement showLeaderboard
    }
}
