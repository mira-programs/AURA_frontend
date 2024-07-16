package com.auraXP.aura;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendViewHolder> {

    private List<Friend> friendsList;

    public FriendsAdapter(List<Friend> friendsList) {
        this.friendsList = friendsList;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        Friend friend = friendsList.get(position);
        holder.friendNameTextView.setText(friend.getName());
        holder.friendXPTextView.setText(friend.getXp());
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder {
        TextView friendNameTextView;
        TextView friendXPTextView;
        ImageView profileImageView;
        Button auraButton;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            friendNameTextView = itemView.findViewById(R.id.friendNameTextView);
            friendXPTextView = itemView.findViewById(R.id.friendXPTextView);
            profileImageView = itemView.findViewById(R.id.profileImageView);
            auraButton = itemView.findViewById(R.id.auraButton);
        }
    }
}
