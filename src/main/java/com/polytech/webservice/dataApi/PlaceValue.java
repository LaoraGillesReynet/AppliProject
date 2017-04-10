package com.polytech.webservice.dataApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by User on 03/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceValue {
    private Geometry geometry;
    private String name;
    private String id;
    private String place_id;
    private int rating;
    private ArrayList<String> types;
    private String vicinity;

    public Geometry getGeometry() {
        return geometry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    @Override
    public String toString() {
        return "PlaceValue{" +
                "geometry=" + geometry +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", place_id='" + place_id + '\'' +
                ", rating=" + rating +
                ", types=" + types +
                ", vicinity='" + vicinity + '\'' +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Geometry{

        Location location;

        public Geometry(){

        }

        public Location getLocation(){
            return location;
        }

        public void setLocation(Location location){
            this.location = location;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public class Location{
            private double lat;
            private double lng;

            public Location(){

            }

            public double getLat(){
                return lat;
            }

            public void setLat(double lat){
                this.lat = lat;
            }

            public double getLng(){
                return lng;
            }

            public void setLng(double lng){
                this.lng = lng;
            }

            @Override
            public String toString() {
                return "Location{" +
                        "lat=" + lat +
                        ", lng=" + lng +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Geometry{" +
                    "location=" + location +
                    '}';
        }
    }
}
