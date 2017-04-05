package com.polytech.webservice.dataApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Cyprien on 05/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDetailRequest {
    private ArrayList<PlaceValue> results;
    private String status;

    public PlaceDetailRequest(){

    }

    public ArrayList<PlaceValue> getResults() {
        return results;
    }

    public void setResults(ArrayList<PlaceValue> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
