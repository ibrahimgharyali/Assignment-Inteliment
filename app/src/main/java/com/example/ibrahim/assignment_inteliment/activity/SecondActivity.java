package com.example.ibrahim.assignment_inteliment.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ibrahim.assignment_inteliment.R;
import com.example.ibrahim.assignment_inteliment.adapters.LocationSpinnerAdapter;
import com.example.ibrahim.assignment_inteliment.model.MapDataModel;
import com.google.gson.GsonBuilder;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class SecondActivity extends AppCompatActivity {

    public static String API_URL = "http://express-it.optusnet.com.au/sample.json";
    private Spinner mapSpinner;
    private TextView trainTV, carTV;
    private MapDataModel[] dropDownArray;
    private LocationSpinnerAdapter adapter;
    private AVLoadingIndicatorView loadingView;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        mapSpinner = (Spinner) findViewById(R.id.spinner);
        carTV = (TextView) findViewById(R.id.car_distance);
        trainTV = (TextView) findViewById(R.id.train_distance);
        loadingView = (AVLoadingIndicatorView) findViewById(R.id.loader);


        /**
         * 2 way of doing it:-  getting data from a file and parse
         * */
        /*dropDownArray = new GsonBuilder().create().fromJson(loadJSONFromAsset(), MapDataModel[].class);

        ArrayList<MapDataModel> dataModels = new ArrayList<>(Arrays.asList(dropDownArray));
        MapDataModel model = new MapDataModel();
        model.setName("Select one");
        dataModels.add(model);
        setupDropDown(dataModels);*/

        makeApiCall();
    }

    private void makeApiCall() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loadingView.setVisibility(View.GONE);
                        dropDownArray = new GsonBuilder().create().fromJson(response, MapDataModel[].class);
                        ArrayList<MapDataModel> dataModels = new ArrayList<>(Arrays.asList(dropDownArray));
                        MapDataModel model = new MapDataModel();
                        // add one default item
                        model.setName("Select one");
                        dataModels.add(model);
                        setupDropDown(dataModels);
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    loadingView.setVisibility(View.GONE);
                    Toast.makeText(SecondActivity.this, "Error in Connection.", Toast.LENGTH_SHORT).show();
                }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    // After data received, set and display
    private void setupDropDown(ArrayList<MapDataModel> dataModels) {
        adapter = new LocationSpinnerAdapter(this, android.R.layout.simple_spinner_item, dataModels);
        mapSpinner.setAdapter(adapter);
        mapSpinner.setSelection(adapter.getCount());//index starts from 0
        mapSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i < dropDownArray.length){  // because i max is empty
                    MapDataModel mapDataModel = dropDownArray[i];
                    String car = mapDataModel.getFromcentral().getCar();
                    String train = mapDataModel.getFromcentral().getTrain();
                    carTV.setText(String.format("Car - %s", car));
                    trainTV.setText(String.format("Train - %s", train));

                    if (car != null)
                        carTV.setVisibility(View.VISIBLE);
                    else
                        carTV.setVisibility(View.GONE);

                    if (train != null)
                        trainTV.setVisibility(View.VISIBLE);
                    else
                        trainTV.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mapSpinner.setSelection(adapter.getCount());//index starts from 0

            }
        });
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
