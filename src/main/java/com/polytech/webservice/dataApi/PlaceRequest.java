package com.polytech.webservice.dataApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by User on 03/04/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceRequest {
    private ArrayList<PlaceValue> results;
    private String status;

    public PlaceRequest(){

    }

    public ArrayList<PlaceValue> getResults(){
        return results;
    }

    public void setResults(ArrayList<PlaceValue> results){
        this.results = results;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){

    }

}
