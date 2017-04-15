package com.polytech.webservice.utils;

import java.util.ArrayList;

/**
 * Created by Cyprien on 07/04/2017.
 */
public class InitializerArrayTypes {
    private ArrayList<String> arrayTypes;

    private ArrayList<String> arrayConditionSoleil;
    private ArrayList<String> arrayConditionNuage;
    private ArrayList<String> arrayConditionPluie;
    private ArrayList<String> arrayConditionOrage;
    private ArrayList<String> arrayConditionNeige;

    public InitializerArrayTypes() {
        arrayTypes = new ArrayList<>();
    }

    public void initialize(int heure, String condition, int temperature){
        arrayConditionSoleil = new ArrayList<>();
        arrayConditionSoleil.add("Ensoleillé");
        arrayConditionSoleil.add("Nuit claire");
        arrayConditionSoleil.add("Nuit bien dégagée");
        arrayConditionSoleil.add("Nuit claire et stratus");
        arrayConditionSoleil.add("Eclaircies");
        arrayConditionSoleil.add("Nuit légèrement voilée");
        arrayConditionSoleil.add("Faible passage nuageux");

        arrayConditionNuage = new ArrayList<>();
        arrayConditionNuage.add("Ciel voilé");
        arrayConditionNuage.add("Brouillard");
        arrayConditionNuage.add("Stratus");
        arrayConditionNuage.add("Stratus se dissipant");
        arrayConditionNuage.add("Nuit nuageuse");
        arrayConditionNuage.add("Faiblement nuageux");
        arrayConditionNuage.add("Fortement nuageux");
        arrayConditionNuage.add("Developpement nuageux");
        arrayConditionNuage.add("Nuit avec developpement nuageux");

        arrayConditionPluie = new ArrayList<>();
        arrayConditionPluie.add("Averses de pluie faible");
        arrayConditionPluie.add("Nuit avec averses");
        arrayConditionPluie.add("Averses de pluie modérée");
        arrayConditionPluie.add("Averses de pluie forte");
        arrayConditionPluie.add("Couvert avec averses");
        arrayConditionPluie.add("Pluie faible");
        arrayConditionPluie.add("Pluie forte");
        arrayConditionPluie.add("Pluie modérée");

        arrayConditionOrage = new ArrayList<>();
        arrayConditionOrage.add("Faiblement orageux");
        arrayConditionOrage.add("Nuit faiblement orageuse");
        arrayConditionOrage.add("Orage modéré");
        arrayConditionOrage.add("Fortement orageux");

        arrayConditionNeige = new ArrayList<>();
        arrayConditionNeige.add("Averse de neige faible");
        arrayConditionNeige.add("Nuit avec averse de neige faible");
        arrayConditionNeige.add("Neige faible");
        arrayConditionNeige.add("Neige modérée");
        arrayConditionNeige.add("Neige forte");
        arrayConditionNeige.add("Pluie et neige mêlée faible");
        arrayConditionNeige.add("Pluie et neige mêlée modérée");
        arrayConditionNeige.add("Pluie et neige mêlée forte");

        if (heure >= 22 && heure < 4){
            arrayTypes.add("casino");
            arrayTypes.add("night_club");
        }
        else if(heure >= 6 && heure < 22)
        {
            if (heure < 10){
                arrayTypes.add("bakery");
            }
            if (heure < 11 || heure >= 13)
            {
                if (heure < 16){
                   arrayTypes.add("cafe");
                }
            }
            if (heure >= 8 && heure < 22){
                if (heure < 11 || heure >= 16 && heure < 20){
                    arrayTypes.add("gym");
                }
                if (heure >= 9)
                {
                    if (heure < 18)
                    {
                        if (arrayConditionSoleil.contains(condition)){
                            arrayTypes.add("park");
                            arrayTypes.add("amusement_park");
                            arrayTypes.add("zoo");
                        }
                        else if (arrayConditionNuage.contains(condition) || arrayConditionPluie.contains(condition) || arrayConditionOrage.contains(condition))
                        {
                            arrayTypes.add("aquarium");
                            arrayTypes.add("art_gallery");
                            arrayTypes.add("store");
                            arrayTypes.add("shopping_mall");
                            arrayTypes.add("church");
                        }
                        else{

                        }

                        if (heure < 12){
                            arrayTypes.add("spa");
                            arrayTypes.add("hair_care");
                            arrayTypes.add("beauty_salon");

                            if (arrayConditionSoleil.contains(condition) || arrayConditionNuage.contains(condition)){
                                arrayTypes.add("stadium");
                            }
                            else if (arrayConditionNuage.contains(condition) || arrayConditionPluie.contains(condition) || arrayConditionOrage.contains(condition)){
                                arrayTypes.add("library");
                            }
                        }
                    }
                    if (heure >= 12 && heure < 14 || heure >= 19){
                        arrayTypes.add("food");
                        arrayTypes.add("restaurant");
                        arrayTypes.add("meal_takeaway");
                        arrayTypes.add("meal_delivery");
                    }
                    if (heure >= 14 && heure < 22){
                        if (heure < 18)
                        {
                            arrayTypes.add("spa");
                            arrayTypes.add("hair_care");
                            arrayTypes.add("beauty_salon");
                            if (arrayConditionNuage.contains(condition) || arrayConditionNuage.contains(condition) || arrayConditionPluie.contains(condition)){
                                arrayTypes.add("movie_theater");
                                arrayTypes.add("library");
                                arrayTypes.add("bowling_alley");
                                arrayTypes.add("movie_rental");
                            }
                            else if (arrayConditionSoleil.contains(condition)){
                                arrayTypes.add("stadium");
                            }
                        }
                        else{
                            arrayTypes.add("movie_theater");
                            arrayTypes.add("bowling");
                            arrayTypes.add("movie_rental");
                        }
                    }
                }
            }
            if (heure >= 17 && heure < 23)
            {
                arrayTypes.add("bar");
            }
        }
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

    public void initialize_pref(String type_categorie){
        switch(type_categorie)
        {
            case "Culture":
                arrayTypes.add("museum");
                arrayTypes.add("art_gallery");
                arrayTypes.add("library");
                arrayTypes.add("university");
                arrayTypes.add("book_store");
                arrayTypes.add("school");
                arrayTypes.add("church");
                arrayTypes.add("hindu_temple");
                break;

            case "DAB":
                arrayTypes.add("bank");
                arrayTypes.add("atm");
                break;

            case "transport":
                arrayTypes.add("airport");
                arrayTypes.add("bus_station");
                arrayTypes.add("parking");
                arrayTypes.add("subway_station");
                arrayTypes.add("taxi_stand");
                arrayTypes.add("train_station");
                arrayTypes.add("gas_station");
                break;

            case "Sport":
                arrayTypes.add("gym");
                arrayTypes.add("stadium");
                break;

            case "Restaurant/Bar":
                arrayTypes.add("restaurant");
                arrayTypes.add("bakery");
                arrayTypes.add("bar");
                arrayTypes.add("cafe");
                arrayTypes.add("food");
                break;

            case "Loisir":
                arrayTypes.add("amusement_park");
                arrayTypes.add("park");
                arrayTypes.add("casino");
                arrayTypes.add("aquarium");
                arrayTypes.add("movie_theater");
                arrayTypes.add("zoo");
                arrayTypes.add("bowling_alley");
                arrayTypes.add("night_club");
                break;

            case "Magasin":
                arrayTypes.add("store");
                arrayTypes.add("florist");
                arrayTypes.add("shoe_store");
                arrayTypes.add("electronics_store");
                arrayTypes.add("convenience_store");
                arrayTypes.add("grocery_or_supermarket");
                arrayTypes.add("home_goods_store");
                arrayTypes.add("clothing_store");
                break;

            case "Santé":
                arrayTypes.add("spa");
                arrayTypes.add("hair_care");
                arrayTypes.add("beauty_salon");
                arrayTypes.add("health");
                arrayTypes.add("dentist");
                arrayTypes.add("doctor");
                arrayTypes.add("hospital");
                arrayTypes.add("pharmacy");
                arrayTypes.add("veterinary_care");
                break;

            case "null":
                break;
        }
    }

    public ArrayList<String> getArrayTypes() {
        return arrayTypes;
    }

    public void setArrayTypes(ArrayList<String> arrayTypes) {
        this.arrayTypes = arrayTypes;
    }

    public ArrayList<String> getArrayConditionSoleil() {
        return arrayConditionSoleil;
    }

    public void setArrayConditionSoleil(ArrayList<String> arrayConditionSoleil) {
        this.arrayConditionSoleil = arrayConditionSoleil;
    }

    public ArrayList<String> getArrayConditionNuage() {
        return arrayConditionNuage;
    }

    public void setArrayConditionNuage(ArrayList<String> arrayConditionNuage) {
        this.arrayConditionNuage = arrayConditionNuage;
    }

    public ArrayList<String> getArrayConditionPluie() {
        return arrayConditionPluie;
    }

    public void setArrayConditionPluie(ArrayList<String> arrayConditionPluie) {
        this.arrayConditionPluie = arrayConditionPluie;
    }

    public ArrayList<String> getArrayConditionOrage() {
        return arrayConditionOrage;
    }

    public void setArrayConditionOrage(ArrayList<String> arrayConditionOrage) {
        this.arrayConditionOrage = arrayConditionOrage;
    }

    public ArrayList<String> getArrayConditionNeige() {
        return arrayConditionNeige;
    }

    public void setArrayConditionNeige(ArrayList<String> arrayConditionNeige) {
        this.arrayConditionNeige = arrayConditionNeige;
    }
}
