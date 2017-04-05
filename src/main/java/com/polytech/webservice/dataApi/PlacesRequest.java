package com.polytech.webservice.dataApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by User on 03/04/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlacesRequest {
    private ArrayList<PlacesValue> results;
    private String status;

    public PlacesRequest(){

    }

    public ArrayList<PlacesValue> getResults(){
        return results;
    }

    public void setResults(ArrayList<PlacesValue> results){
        this.results = results;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){

    }

}
