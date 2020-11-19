package com.example.exincidencies;

public class Incidencia {
    protected String tittle;
    protected String prioritat;

    public Incidencia(String nom, String prioritat){
        this.tittle = nom;
        this.prioritat = prioritat;
    }

    public String getTittle(){
        return tittle;
    }

    public String getPrioritat(){
        return prioritat;
    }

    public void setTittle(String newNom){
        this.tittle = newNom;
    }

    public void setPrioritat(String newPrioritat){
        this.prioritat = newPrioritat;
        
    }

}
