package com.polytech.webservice.dataBdd;


import org.springframework.data.annotation.Id;

import java.util.ArrayList;

/**
 * Created by Cyprien on 05/04/2017.
 */
public class Place {

    @Id
    private String id;

    private String place_id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String icon;
    private ArrayList<String> types;
    private int rating;
    private String phoneNumber;
    private String website;
    private Horaires horaires;
    private ArrayList<Comment> comment;
    private ArrayList<String> photoRefs;

    public Place(){

    }

    public Place(String place_id, String name, String address, double latitude, double longitude, String icon, ArrayList<String> types, int rating) {
        this.place_id = place_id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.icon = icon;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public Horaires getHoraires() {
        return horaires;
    }

    public void setHoraires(Horaires horaires) {
        this.horaires = horaires;
    }

    public ArrayList<Comment> getComments() {
        return comment;
    }

    public void setComments(ArrayList<Comment> comment) {
        this.comment = comment;
    }

    public ArrayList<String> getPhotoRefs() {
        return photoRefs;
    }

    public void setPhotoRefs(ArrayList<String> photoRefs) {
        this.photoRefs = photoRefs;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", place_id='" + place_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", icon='" + icon + '\'' +
                ", types=" + types +
                ", rating=" + rating +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", website='" + website + '\'' +
                ", horaires=" + horaires +
                ", comment=" + comment +
                ", photoRefs=" + photoRefs +
                '}';
    }
}
