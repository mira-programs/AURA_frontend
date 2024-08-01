package com.auraXP.aura;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.auraXP.aura.api.models.Challenge;

import java.util.List;

public class ChallengeAdapter extends ArrayAdapter<Challenge> {

    public ChallengeAdapter(Context context, List<Challenge> challenges) {
        super(context, 0, challenges);
    }

    @NonNull
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Challenge challenge = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_challenge, parent, false);
        }

        // Lookup view for data population
        TextView tvDescription = convertView.findViewById(R.id.tvChallengeDescription);
        TextView tvPoints = convertView.findViewById(R.id.tvChallengePoints);

        // Populate the data into the template view using the data object
        if (challenge != null) {
            tvDescription.setText(challenge.getDescription());
            tvPoints.setText(String.valueOf(challenge.getPoints()));
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
