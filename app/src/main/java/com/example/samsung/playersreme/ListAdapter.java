package com.example.samsung.playersreme;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.R.*;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private ArrayList<Player> data;
    private Activity activity;
    private LayoutInflater inflater;

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) view = inflater.inflate(R.layout.item_layut, null);
        TextView name = view.findViewById(R.id.playername);
        TextView positionName = view.findViewById(R.id.playerposition);
        TextView jerseyNumber = view.findViewById(R.id.playerJerseyNumber);
        TextView height = view.findViewById(R.id.playerheight);
        TextView weight = view.findViewById(R.id.playerweight);
        TextView age = view.findViewById(R.id.playerage);
        ImageView playerPhoto = view.findViewById(R.id.playerphoto);

        Player p = this.data.get(position);
        name.setText(p.getName());
        positionName.setText(p.getPositionName());
        jerseyNumber.setText(p.getJerseyNumber() + " ");
        height.setText(p.getHeight());
        weight.setText(p.getWeight() + " lbs");
        age.setText(p.getAge() + " ");
        playerPhoto.setImageDrawable(p.getImage());
        return view;
    }

    public ListAdapter(ArrayList<Player> data, Activity activity) {
        this.data = data;
        this.activity = activity;
        this.inflater = (LayoutInflater)this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
}
