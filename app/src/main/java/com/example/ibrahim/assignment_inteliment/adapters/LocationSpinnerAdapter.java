package com.example.ibrahim.assignment_inteliment.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ibrahim.assignment_inteliment.R;
import com.example.ibrahim.assignment_inteliment.model.MapDataModel;

import java.util.ArrayList;

/**
 * Created by ibrahim on 9/10/17.
 */

public class LocationSpinnerAdapter extends ArrayAdapter<MapDataModel> {

    private int layoutId;
    private Activity context;
    private ArrayList<MapDataModel> mapObjects;

    public LocationSpinnerAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull ArrayList<MapDataModel> mapDataModels) {
        super(context, resource, mapDataModels);
        this.context = context;
        this.layoutId = resource;
        this.mapObjects = mapDataModels;

    }

    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;

        if(row == null)
        {
            //inflate your customlayout for the textview
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.spinner_layout, parent, false);
        }
        //put the data in it
        MapDataModel item = mapObjects.get(position);
        if(item != null)
        {
            TextView text1 = row.findViewById(R.id.row_text);
            text1.setText(item.getName());
        }
        return row;
    }


    // we need to copy paste the content of the getDropDownView, else
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;

        if(row == null)
        {
            //inflate your customlayout for the textview
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(R.layout.spinner_layout, parent, false);
        }
        //put the data in it
        MapDataModel item = mapObjects.get(position);
        if(item != null)
        {
            TextView text1 = row.findViewById(R.id.row_text);
            text1.setText(item.getName());
        }
        return row;
    }
}
