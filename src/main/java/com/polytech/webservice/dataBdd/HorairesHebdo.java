package com.polytech.webservice.dataBdd;

import java.util.ArrayList;

/**
 * Created by Cyprien on 05/04/2017.
 */
public class HorairesHebdo{
    private ArrayList<HorairesJour> horaires_jour ;
    private String  horairesHebdo;

    public HorairesHebdo(){

    }

    public HorairesHebdo(ArrayList<HorairesJour> horaires_jour, String horairesHebdo) {
        this.horaires_jour = horaires_jour;
        this.horairesHebdo = horairesHebdo;
    }

    public ArrayList<HorairesJour> getHoraires_jour() {
        return horaires_jour;
    }

    public void setHoraires_jour(ArrayList<HorairesJour> horaires_jour) {
        this.horaires_jour = horaires_jour;
    }

    public String getHorairesHebdo() {
        return horairesHebdo;
    }

    public void setHorairesHebdo(String horairesHebdo) {
        this.horairesHebdo = horairesHebdo;
    }

    public class HorairesJour{
        private String ouverture;
        private String fermeture;

        public HorairesJour(){

        }

        public String getOuverture() {
            return ouverture;
        }

        public void setOuverture(String ouverture) {
            this.ouverture = ouverture;
        }

        public String getFermeture() {
            return fermeture;
        }

        public void setFermeture(String fermeture) {
            this.fermeture = fermeture;
        }
    }
}