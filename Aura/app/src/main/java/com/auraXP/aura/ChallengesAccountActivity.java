package com.auraXP.aura;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.auraXP.aura.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ChallengesAccountActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges_account);

        listView = findViewById(R.id.listView);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_friends:
                    startActivity(new Intent(ChallengesAccountActivity.this, FriendsActivity.class));
                    return true;
                case R.id.navigation_center:
                    startActivity(new Intent(ChallengesAccountActivity.this, DailyChallenges.class));
                    return true;
                case R.id.navigation_account:
                    startActivity(new Intent(ChallengesAccountActivity.this, AccountActivity.class));
                    return true;
            }
            return false;
        });

        // Define your Activity class
        class Activity {
            String points;
            String description;

            Activity(String points, String description) {
                this.points = points;
                this.description = description;
            }

            @Override
            public String toString() {
                return points + ": " + description;
            }
        }

        // Create an array of activities
        Activity[] activities = {
                new Activity("+300", "Daily challenge"),
                new Activity("-60", "Unfollowed a friend"),
                new Activity("+400", "Side quest"),
                new Activity("+20", "Stayed hydrated")
        };

        // Convert the array to a list for the adapter
        ArrayList<String> activityList = new ArrayList<>();
        for (Activity activity : activities) {
            activityList.add(activity.toString());
        }

        // Create an ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, activityList);

        // Set the adapter for the ListView
        listView.setAdapter(adapter);
    }
}
