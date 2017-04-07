package com.polytech.webservice.dataBdd;

import java.util.ArrayList;

/**
 * Created by Cyprien on 05/04/2017.
 */
public class Comment{
    private String auteur;
    private String language;
    private float rating;
    private String commentaire;
    private int time;
    private ArrayList<Aspect> aspectArrayList;

    public Comment(){

    }

    public Comment(String auteur, String language, float rating, String commentaire, int time, ArrayList<Aspect> aspectArrayList) {
        this.auteur = auteur;
        this.language = language;
        this.rating = rating;
        this.commentaire = commentaire;
        this.time = time;
        this.aspectArrayList = aspectArrayList;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public ArrayList<Aspect> getAspectArrayList() {
        return aspectArrayList;
    }

    public void setAspectArrayList(ArrayList<Aspect> aspectArrayList) {
        this.aspectArrayList = aspectArrayList;
    }

    public static class Aspect{
        private int rating;
        private String type;

        public Aspect(){

        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}