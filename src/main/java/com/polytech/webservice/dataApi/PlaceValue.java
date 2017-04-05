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
    private String icon;
    private String id;
    private String place_id;
    private int rating;
    private ArrayList<String> types;
    private String vicinity;
    private OpeningHours opening_hours;

    public Geometry getGeometry() {
        return geometry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(OpeningHours opening_hours) {
        this.opening_hours = opening_hours;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class OpeningHours{
        private boolean open_now;
        private ArrayList<HorairesJour> periods;
        private ArrayList<String> weekday_text;


        public OpeningHours(){

        }

        public boolean isOpen_now() {
            return open_now;
        }

        public void setOpen_now(boolean open_now) {
            this.open_now = open_now;
        }

        public class HorairesJour{
            private Hours open;
            private Hours close;

            public HorairesJour(){

            }

            public Hours getOpen() {
                return open;
            }

            public void setOpen(Hours open) {
                this.open = open;
            }

            public Hours getClose() {
                return close;
            }

            public void setClose(Hours close) {
                this.close = close;
            }

            public class Hours{
                int day;
                String time;

                public Hours(){

                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }
            }
        }
    }
}
