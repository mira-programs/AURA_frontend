package com.auraXP.aura;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
