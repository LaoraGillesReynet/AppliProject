package com.polytech.webservice.dataBdd;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

/**
 * Created by Cyprien on 05/04/2017.
 */
@SuppressWarnings("serial")
@Document(collection = "places")
public class Place{

    @Indexed(unique=true)
    private String id;

    private String place_id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private ArrayList<String> types;
    private int rating;
    private String phoneNumber;
    private String website;
    private HorairesHebdo horaires_hebdo;

    private ArrayList<Comment> comment;
    private ArrayList<Photo> photoRefs;

    public Place(){

    }

    public Place(String place_id, String name, String address, double latitude, double longitude, String icon, ArrayList<String> types, int rating) {
        this.place_id = place_id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.types = types;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public HorairesHebdo getHoraires_hebdo() {
        return horaires_hebdo;
    }

    public void setHoraires_hebdo(HorairesHebdo horaires_hebdo) {
        this.horaires_hebdo = horaires_hebdo;
    }

    public ArrayList<Comment> getComment() {
        return comment;
    }

    public void setComment(ArrayList<Comment> comment) {
        this.comment = comment;
    }

    public ArrayList<Photo> getPhotoRefs() {
        return photoRefs;
    }

    public void setPhotoRefs(ArrayList<Photo> photoRefs) {
        this.photoRefs = photoRefs;
    }
}
