package com.auraXP.aura;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FriendsAdapter adapter;
    private List<String> friendsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        friendsList = new ArrayList<>(); // Replace with your data source

        // Initialize adapter with empty list (initially)
        adapter = new FriendsAdapter(friendsList);
        recyclerView.setAdapter(adapter);

        // TODO: Implement search functionality for friends
    }
}
