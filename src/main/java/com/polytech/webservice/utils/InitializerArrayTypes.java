package com.polytech.webservice.utils;

import java.util.ArrayList;

/**
 * Created by Cyprien on 07/04/2017.
 */
public class InitializerArrayTypes {
    private ArrayList<String> arrayTypes;

    private ArrayList<String> arrayCulture;
    private ArrayList<String> arrayDab;
    private ArrayList<String> arrayTransport;
    private ArrayList<String> arraySport;
    private ArrayList<String> arrayResto;
    private ArrayList<String> arrayLoisirs;
    private ArrayList<String> arrayShop;
    private ArrayList<String> arraySante;

    public InitializerArrayTypes() {

        arrayTypes = new ArrayList<>();

        //"museum|art_gallery|library|university|book_store|school|"
        arrayCulture = new ArrayList<>();
        arrayCulture.add("museum");
        arrayCulture.add("art_gallery");
        arrayCulture.add("library");
        arrayCulture.add("university");
        arrayCulture.add("book_store");
        arrayCulture.add("school");

        arrayDab = new ArrayList<>();
        arrayDab.add("bank");
        arrayDab.add("atm");

        //"airport|bus_station|parking|subway_station|taxi_stand|train_station|gas_station|"
        arrayTransport = new ArrayList<>();
        arrayTransport.add("airport");
        arrayTransport.add("bus_station");
        arrayTransport.add("parking");
        arrayTransport.add("subway_station");
        arrayTransport.add("taxi_stand");
        arrayTransport.add("train_station");
        arrayTransport.add("gas_station");

        //"|gym|stadium|"
        arraySport = new ArrayList<>();
        arraySport.add("gym");
        arraySport.add("stadium");

        //"|restaurant|bakery|bar|cafe|food|"
        arrayResto= new ArrayList<>();
        arrayResto.add("restaurant");
        arrayResto.add("bakery");
        arrayResto.add("bar");
        arrayResto.add("cafe");
        arrayResto.add("food");

        //"amusement_park|casino|aquarium|movie_theater|zoo|bowling_alley|night_club|"
        arrayLoisirs = new ArrayList<>();
        arrayLoisirs.add("amusement_park");
        arrayLoisirs.add("casino");
        arrayLoisirs.add("aquarium");
        arrayLoisirs.add("movie_theater");
        arrayLoisirs.add("zoo");
        arrayLoisirs.add("bowling_alley");
        arrayLoisirs.add("night_club");

        //"|store|shoe_store|electronics_store|convenience_store|grocery_or_supermarket|home_goods_store|clothing_store|"
        arrayShop = new ArrayList<>();
        arrayShop.add("");

        //"|spa|hair_care|beauty_salon|health|dentist|doctor|hospital|pharmacy|veterinary_care|"
        arraySante = new ArrayList<>();
        arraySante.add("");


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
