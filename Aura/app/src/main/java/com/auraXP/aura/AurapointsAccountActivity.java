package com.auraXP.aura;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AurapointsAccountActivity extends AppCompatActivity {

    private TextView xpTextView, levelTextView, nextLevelTextView;
    private ProgressBar progressBar;
    private LinearLayout activitiesList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aurapoints_account);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        xpTextView = findViewById(R.id.aura_points_xp);
        levelTextView = findViewById(R.id.aura_points_level);
        nextLevelTextView = findViewById(R.id.aura_points_next_level);
        progressBar = findViewById(R.id.aura_points_progress);
        activitiesList = findViewById(R.id.activities_list);
        listView = findViewById(R.id.listViewAuraPoints);

        // Example data - replace with actual data
        int xp = 2330;
        int level = 3;
        int nextLevel = 4;
        int progress = 60; // progress in percentage
        Activity[] activities = {
                new Activity("+300", "Daily challenge"),
                new Activity("-60", "Unfollowed a friend"),
                new Activity("+400", "Side quest"),
                new Activity("+20", "Stayed hydrated")
        };

        updateAuraPoints(xp, level, nextLevel, progress, activities);
    }

    private void updateAuraPoints(int xp, int level, int nextLevel, int progress, Activity[] activities) {
        xpTextView.setText(xp + " XP");
        levelTextView.setText("level " + level);
        nextLevelTextView.setText("level " + nextLevel);
        progressBar.setProgress(progress);

        for (Activity activity : activities) {
            addActivityItem(activity);
        }
    }

    public void addActivityItem(Activity activity) {
        LinearLayout itemLayout = new LinearLayout(this);
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);
        itemLayout.setPadding(16, 16, 16, 16);
        itemLayout.setGravity(Gravity.CENTER_VERTICAL);

        TextView pointsTextView = new TextView(this);
        pointsTextView.setText(activity.points);
        pointsTextView.setTextSize(18);
        pointsTextView.setTextColor(getResources().getColor(R.color.colorPrimary));

        TextView descriptionTextView = new TextView(this);
        descriptionTextView.setText(activity.description);
        descriptionTextView.setTextSize(18);
        descriptionTextView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        descriptionTextView.setPadding(16, 0, 0, 0);

        itemLayout.addView(pointsTextView);
        itemLayout.addView(descriptionTextView);

        activitiesList.addView(itemLayout);
    }

    private class Activity {
        String points;
        String description;

        Activity(String points, String description) {
            this.points = points;
            this.description = description;
        }
    }

    // Example method to add new activity dynamically
    public void addNewActivity(String points, String description) {
        Activity newActivity = new Activity(points, description);
        addActivityItem(newActivity);
    }
}
