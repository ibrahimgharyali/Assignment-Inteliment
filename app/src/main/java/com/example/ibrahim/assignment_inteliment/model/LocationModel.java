package com.example.ibrahim.assignment_inteliment.model;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by ibrahim on 9/10/17.
 */

public class LocationModel implements Serializable {

    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String asString() {
        Gson gson = new Gson();
        JSONObject object = new JSONObject();
        try {
            object = new JSONObject(gson.toJson(this));
        } catch (Exception ignored) {

        }
        return object.toString();
    }
}
