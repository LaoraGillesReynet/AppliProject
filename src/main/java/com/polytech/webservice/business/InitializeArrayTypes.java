package com.polytech.webservice.business;

import java.util.ArrayList;

/**
 * Created by Cyprien on 03/04/2017.
 */
public class InitializeArrayTypes {
    private ArrayList<String> array;

    public InitializeArrayTypes(){
        array = new ArrayList<>();
    }

    public void InitializeArray(int h, int min, String Condition, int temp, int vitesseVent){
        array.add("food");
        array.add("bar");
    }

    public ArrayList<String> getArray() {
        return array;
    }

    public void setArray(ArrayList<String> array) {
        this.array = array;
    }
}
