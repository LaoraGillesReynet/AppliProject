package com.polytech.webservice.dataBdd;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Laora on 01/05/2017.
 */

@Document
public class Search {

    @Id
    private String id ;

    private double longitude ;
    private double latitude ;
    private String meteo ;
    private int heure ;


    public Search() {
    }

    @PersistenceConstructor
    public Search (String id, double longitude, double latitude, String meteo, int heure){
        this.id = id ;
        this.longitude = longitude ;
        this.latitude = latitude ;
        this.meteo = meteo ;
        this.heure = heure ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getMeteo() {
        return meteo;
    }

    public void setMeteo(String meteo) {
        this.meteo = meteo;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }
}
