package com.example.ibrahim.assignment_inteliment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.example.ibrahim.assignment_inteliment.model.MapDataModel;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private Spinner mapDataSpinner;
    private ArrayList<MapDataModel> dropDownArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mapDataSpinner = (Spinner) findViewById(R.id.spinner);



        Type listType = new TypeToken <ArrayList <MapDataModel>>(){}.getType();
        dropDownArrayList = new GsonBuilder().create().fromJson(loadJSONFromAsset(), listType);
        
    }
    void openInMap(){
//        mapDataSpinner.get
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("map_json.txt");
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
}
