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

    private String type_search;             //(Initial, preference, autocomplete, advance_search
    private double longitude ;
    private double latitude ;
    private String meteo ;                  //Initial
    private int heure ;
    private int jour;
    private int mois;
    private int annee;
    private String autocompleteString ;     //champs autocomplete
    private int rayon;                      //advance_search
    private String types;                   //advance_search
    private String openNow;                 //advance_search


    public Search() {
        this.type_search = "";
        this.meteo = "" ;
        this.autocompleteString = "";
        this.types = "";
        this.openNow = "";
    }

    @PersistenceConstructor
    public Search (String id, String type_search, double longitude, double latitude, String meteo, int heure, int jour, int mois, int annee, String autocompleteString, int rayon, String types, String openNow){
        this.id = id ;
        this.type_search = type_search;
        this.longitude = longitude ;
        this.latitude = latitude ;
        this.meteo = meteo ;
        this.heure = heure ;
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.autocompleteString = autocompleteString;
        this.rayon = rayon;
        this.types = types;
        this.openNow = openNow;
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

    public String getType_search() {
        return type_search;
    }

    public void setType_search(String type_search) {
        this.type_search = type_search;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getAutocompleteString() {
        return autocompleteString;
    }

    public void setAutocompleteString(String autocompleteString) {
        this.autocompleteString = autocompleteString;
    }

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getOpenNow() {
        return openNow;
    }

    public void setOpenNow(String openNow) {
        this.openNow = openNow;
    }
}
