package com.polytech.webservice.utils;

import java.util.ArrayList;

/**
 * Created by Cyprien on 07/04/2017.
 */
public class InitializerArrayTypes {
    private ArrayList<String> arrayTypes;

    public InitializerArrayTypes() {
        arrayTypes = new ArrayList<>();
    }

    public void initialize(){
        arrayTypes.add("aquarium");
        arrayTypes.add("bakery");
        arrayTypes.add("bowling_alley");
        arrayTypes.add("art_gallery");
        arrayTypes.add("bicycle_store");
        arrayTypes.add("amusement_park");
        arrayTypes.add("bar");
        arrayTypes.add("cafe");
        arrayTypes.add("casino");
        arrayTypes.add("beauty_salon");
        arrayTypes.add("car_wash");
        arrayTypes.add("casino");
        arrayTypes.add("food");
        arrayTypes.add("gym");
        arrayTypes.add("meal_takeaway");
        arrayTypes.add("movie_theater");
        arrayTypes.add("night_club");
        arrayTypes.add("meal_delivery");
        arrayTypes.add("movie_rental");
        arrayTypes.add("museum");
        arrayTypes.add("park");
        arrayTypes.add("restaurant");
        arrayTypes.add("shopping_mall");
        arrayTypes.add("stadium");
        arrayTypes.add("spa");
        arrayTypes.add("store");
        arrayTypes.add("zoo");
    }

    public ArrayList<String> getArrayTypes() {
        return arrayTypes;
    }

    public void setArrayTypes(ArrayList<String> arrayTypes) {
        this.arrayTypes = arrayTypes;
    }
}
