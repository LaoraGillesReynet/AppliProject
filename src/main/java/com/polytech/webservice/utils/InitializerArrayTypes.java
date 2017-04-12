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

    public void initialize_result(int heure, String condition, int temperature, ArrayList<String> TypesPlace){
        if (heure >= 0 && heure < 4){
            arrayTypes.add("casino");
            arrayTypes.add("night_club");
        }
        else if ((heure >= 6 && heure < 11) || (heure >= 14 && heure < 17)){
            if (heure < 11){
                arrayTypes.add("bakery");
                arrayTypes.add("cafe");
            }
            if (heure >= 8 ){
                arrayTypes.add("amusement_park");
                arrayTypes.add("aquarium");
                arrayTypes.add("art_gallery");
                arrayTypes.add("gym");
                arrayTypes.add("store");
                arrayTypes.add("shopping_mall");
                arrayTypes.add("zoo");
            }
        }
        else if(heure >= 11 && heure < 14 || heure >= 19 && heure < 21){
            if (condition.equals("Ensoleillé")){
                arrayTypes.add("food");
                arrayTypes.add("restaurant");
            }
            else{
                arrayTypes.add("meal_takeaway");
                arrayTypes.add("meal_delivery");
            }
            if (heure>= 19){
                arrayTypes.add("bowling_alley");
            }
        }
        else if(heure >= 17 && heure < 19){
            arrayTypes.add("bar");
            arrayTypes.add("gym");
            arrayTypes.add("movie_theater");
        }
        else{
            arrayTypes.add("bar");
            arrayTypes.add("casino");
            arrayTypes.add("movie_rental");
            arrayTypes.add("movie_theater");
        }
    }

    public ArrayList<String> getArrayTypes() {
        return arrayTypes;
    }

    public void setArrayTypes(ArrayList<String> arrayTypes) {
        this.arrayTypes = arrayTypes;
    }
}
