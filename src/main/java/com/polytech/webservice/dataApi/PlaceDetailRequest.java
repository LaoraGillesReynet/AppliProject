package com.polytech.webservice.dataApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Cyprien on 05/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDetailRequest {
    private PlaceDetailValue result;
    private String status;

    public PlaceDetailRequest(){

    }

    public PlaceDetailValue getResult() {
        return result;
    }

    public void setResult(PlaceDetailValue result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
