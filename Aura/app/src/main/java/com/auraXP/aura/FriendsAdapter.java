package com.auraXP.aura;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    private List<String> friendsList;

    public FriendsAdapter(List<String> friendsList) {
        this.friendsList = friendsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String friendName = friendsList.get(position);
        holder.friendNameTextView.setText(friendName);
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView friendNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            friendNameTextView = itemView.findViewById(R.id.friendNameTextView);
        }
    }
}
