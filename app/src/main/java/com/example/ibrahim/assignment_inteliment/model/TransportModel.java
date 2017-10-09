package com.example.ibrahim.assignment_inteliment.model;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by ibrahim on 9/10/17.
 */

public class TransportModel implements Serializable {

    private String car;
    private String train;


    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
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
