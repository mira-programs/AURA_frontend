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

    // ViewHolder class to hold views for recycling
    private static class ViewHolder {
        TextView tvDescription;
        TextView tvPoints;
    }

    public ChallengeAdapter(@NonNull Context context, @NonNull List<Challenge> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_challenge, parent, false);

            // Create a new ViewHolder and store references to the views
            viewHolder = new ViewHolder();
            viewHolder.tvDescription = convertView.findViewById(R.id.tvChallengeDescription);
            viewHolder.tvPoints = convertView.findViewById(R.id.tvChallengePoints);

            // Store the ViewHolder with the view
            convertView.setTag(viewHolder);

        // Get the data item for this position
        Challenge challenge = getItem(position);

        // Populate the data into the template view using the data object
        if (challenge != null) {
            viewHolder.tvDescription.setText(challenge.getDescription());
            viewHolder.tvPoints.setText(String.valueOf(challenge.getPoints()));
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
