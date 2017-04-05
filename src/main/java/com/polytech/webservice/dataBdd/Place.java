package com.polytech.webservice.dataBdd;


import org.springframework.data.annotation.Id;

import java.util.ArrayList;

/**
 * Created by Cyprien on 05/04/2017.
 */
public class Place {

    @Id
    private String id;

    private String name;
    private String icon;
    private int rating;
    private ArrayList<String> types;
    private String vicinity;

}
