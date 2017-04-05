package com.polytech.webservice.dataApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Cyprien on 05/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDetailRequest {
    private ArrayList<PlaceDetailValue> results;
    private String status;

    public PlaceDetailRequest(){

    }

    public ArrayList<PlaceDetailValue> getResults() {
        return results;
    }

    public void setResults(ArrayList<PlaceDetailValue> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
