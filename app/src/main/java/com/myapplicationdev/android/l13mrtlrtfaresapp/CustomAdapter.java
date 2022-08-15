package com.myapplicationdev.android.l13mrtlrtfaresapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Fares> fareList;

    public CustomAdapter(Context context, int resource, ArrayList<Fares> fareList) {
        super(context, resource, fareList);
        this.parent_context = context;
        this.layout_id = resource;
        this.fareList = fareList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);


        TextView tvType = rowView.findViewById(R.id.tvTypeEdit);
        TextView tvTime = rowView.findViewById(R.id.tvTimeEdit);
        TextView tvDist = rowView.findViewById(R.id.tvDistEdit);
        TextView tvFare = rowView.findViewById(R.id.tvFareEdit);
        Fares currentFare = fareList.get(position);

        tvType.setText(": " + currentFare.getType());
        tvTime.setText(": " + currentFare.getTime());
        tvDist.setText(": " + currentFare.getDistance());
        tvFare.setText(": " + currentFare.getFare());

        return rowView;
    }
}