package com.auraXP.aura;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Set;

public class FriendsAdapter extends BaseAdapter {

    private Context context;
    private List<String> friends;
    private Set<String> requestedFriends;

    public FriendsAdapter(Context context, List<String> friends, Set<String> requestedFriends) {
        this.context = context;
        this.friends = friends;
        this.requestedFriends = requestedFriends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int position) {
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.friend_item, parent, false);
        }

        TextView friendName = convertView.findViewById(R.id.friend_name);
        Button auraButton = convertView.findViewById(R.id.aura_button);
        TextView requestSentIndicator = convertView.findViewById(R.id.request_sent_indicator);

        String friend = friends.get(position);
        friendName.setText(friend);

        if (context instanceof FriendsActivity) {
            if (((FriendsActivity) context).header.getText().equals("Friends")) {
                if (requestedFriends.contains(friend)) {
                    auraButton.setVisibility(View.GONE);
                    requestSentIndicator.setVisibility(View.VISIBLE);
                } else {
                    auraButton.setVisibility(View.VISIBLE);
                    requestSentIndicator.setVisibility(View.GONE);
                    auraButton.setOnClickListener(v -> {
                        ((FriendsActivity) context).addFriend(friend);
                    });
                }
            } else {
                auraButton.setVisibility(View.GONE);
                requestSentIndicator.setVisibility(View.GONE);
            }
        }

        return convertView;
    }
}
