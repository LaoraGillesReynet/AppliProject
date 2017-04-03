package com.polytech.webservice.business;

import java.util.ArrayList;

/**
 * Created by Cyprien on 03/04/2017.
 */
public class InitializeArrayTypes {

    public InitializeArrayTypes(){

    }

    public ArrayList<String> InitializeArray(int h, int min, String Condition, int temp, int vitesseVent){
        ArrayList<String> array = new ArrayList<>();
        array.add("food");
        array.add("bar");
        return array;
    }
}
