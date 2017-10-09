package com.example.ibrahim.assignment_inteliment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.ibrahim.assignment_inteliment.adapters.LocationSpinnerAdapter;
import com.example.ibrahim.assignment_inteliment.model.MapDataModel;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class SecondActivity extends AppCompatActivity {

    private Spinner mapSpinner;
    private MapDataModel[] dropDownArray;
    private LocationSpinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mapSpinner = (Spinner) findViewById(R.id.spinner);

        dropDownArray = new GsonBuilder().create().fromJson(loadJSONFromAsset(), MapDataModel[].class);

        ArrayList<MapDataModel> dataModels = new ArrayList<>(Arrays.asList(dropDownArray));
        MapDataModel model = new MapDataModel();
        model.setName("Select one");
        dataModels.add(model);
        adapter = new LocationSpinnerAdapter(this, android.R.layout.simple_spinner_item, dataModels);
        mapSpinner.setAdapter(adapter);
        mapSpinner.setSelection(adapter.getCount());//index starts from 0
        /*mapSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i < dropDownArray.length){  // because i max is empty
                    MapDataModel mapDataModel = dropDownArray[i];
                    Intent intent = new Intent(SecondActivity.this, MapsActivity.class);
                    intent.putExtra("data", mapDataModel);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
    }



    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(
                    getResources().getIdentifier("map_json",
                            "raw", getPackageName()));
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void openInMap(View view) {
        int i = mapSpinner.getSelectedItemPosition();
        if (i < dropDownArray.length){  // because i max is empty
            MapDataModel mapDataModel = dropDownArray[i];
            Intent intent = new Intent(SecondActivity.this, MapsActivity.class);
            intent.putExtra("data", mapDataModel);
            startActivity(intent);
        }
    }
}
