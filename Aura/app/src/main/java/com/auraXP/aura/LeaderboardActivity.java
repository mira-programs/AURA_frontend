package com.auraXP.aura;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LeaderboardAdapter leaderboardAdapter;
    private List<LeaderboardEntry> leaderboardEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.rvLeaderboard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize data
        leaderboardEntries = new ArrayList<>();
        // Add sample data (this should be replaced with actual data)
        leaderboardEntries.add(new LeaderboardEntry("Player 1", 1000));
        leaderboardEntries.add(new LeaderboardEntry("Player 2", 900));
        leaderboardEntries.add(new LeaderboardEntry("Player 3", 800));

        // Set up adapter
        leaderboardAdapter = new LeaderboardAdapter(leaderboardEntries);
        recyclerView.setAdapter(leaderboardAdapter);
    }
}
