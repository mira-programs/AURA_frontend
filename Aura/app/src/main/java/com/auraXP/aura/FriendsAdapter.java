package com.auraXP.aura;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class FriendsAdapter extends BaseAdapter {

    private Context context;
    private List<String> friends;

    public FriendsAdapter(Context context, List<String> friends) {
        this.context = context;
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

        friendName.setText(friends.get(position));
        auraButton.setOnClickListener(v -> {
            // Handle Aura++ button click
        });

        return convertView;
    }
}
