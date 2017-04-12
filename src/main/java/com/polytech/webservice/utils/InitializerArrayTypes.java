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


    /*
        Condition de temps possibles:
        Ensoleillé, Nuit claire, Ciel voilé, Nuit légèrement voilée, Faible passages nuageux, Nuit bien dégagée
        brouillard, stratus, stratus se dissipant, nuit claire et stratus, éclaircies, nuit nuageuse, Faiblement nuageux, Fortement nuageux,
        Averses de pluie faible, Nuit avec averses, Averses de pluie modérée, Averses de pluie forte, Couvert avec averses, Pluie faible,
        Pluie forte, Pluie modérée, Developpement nuageux, Nuit avec developpement nuageux, Faiblement orageux, Nuit faiblement orageuse,
        Orage modéré, Fortement orageux, Averse de neige faible, Nuit avec averse de neige faible, Neige faible, Neige modérée, Neige forte,
        Pluie et neige mêlée faible, Pluie et neige mêlée modérée, Pluie et neige mêlée forte
     */

    public void initialize_result(int heure, String condition, int temperature){

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
