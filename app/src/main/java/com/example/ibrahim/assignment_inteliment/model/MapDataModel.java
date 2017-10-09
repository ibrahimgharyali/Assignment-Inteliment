package com.example.ibrahim.assignment_inteliment.model;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by ibrahim on 9/10/17.
 */

public class MapDataModel implements Serializable {

    private int id;
    private String name;
    private TransportModel fromcentral;
    private LocationModel location;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransportModel getFromcentral() {
        return fromcentral;
    }

    public void setFromcentral(TransportModel fromcentral) {
        this.fromcentral = fromcentral;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
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
