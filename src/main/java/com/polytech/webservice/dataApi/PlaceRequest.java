package com.polytech.webservice.dataApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by User on 03/04/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceRequest {
    private String next_page_token;
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

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    @Override
    public String toString() {
        return "PlaceRequest{" +
                "next_page_token='" + next_page_token + '\'' +
                ", results=" + results +
                ", status='" + status + '\'' +
                '}';
    }
}
