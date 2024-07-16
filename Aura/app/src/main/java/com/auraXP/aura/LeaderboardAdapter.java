package com.auraXP.aura;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder> {

    private List<LeaderboardEntry> leaderboardEntries;

    public LeaderboardAdapter(List<LeaderboardEntry> leaderboardEntries) {
        this.leaderboardEntries = leaderboardEntries;
    }

    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_leaderboard_entry, parent, false);
        return new LeaderboardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
        LeaderboardEntry entry = leaderboardEntries.get(position);
        holder.playerName.setText(entry.getPlayerName());
        holder.playerScore.setText(String.valueOf(entry.getPlayerScore()));
    }

    @Override
    public int getItemCount() {
        return leaderboardEntries.size();
    }

    static class LeaderboardViewHolder extends RecyclerView.ViewHolder {

        TextView playerName;
        TextView playerScore;
        Button xpButton;

        public LeaderboardViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.tvPlayerName);
            playerScore = itemView.findViewById(R.id.tvPlayerScore);
            xpButton = itemView.findViewById(R.id.btnXP);
        }
    }
}
