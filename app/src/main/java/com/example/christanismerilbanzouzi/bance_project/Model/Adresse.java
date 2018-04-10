package com.example.christanismerilbanzouzi.bance_project.Model;

/**
 * Created by christanismerilbanzouzi on 09/04/2018.
 */

public class Adresse {

    private String Voie;
    private String Ville;
    private String Code;

    public Adresse(){

    }

    @Override
    public String toString() {
        return " Votre Adresse Personel:" +"\n"+
                " N°='" + Voie + '\'' +"\n"+
                " La Ville='" + Ville + '\'' +"\n"+
                " Code Postale ='" + Code + '\'' +"\n";
    }

    public String getVoie() {
        return Voie;
    }

    public void setVoie(String voie) {
        this.Voie = voie;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        this.Ville = ville;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    public Adresse(String voie, String ville, String code) {

        this.Voie = voie;
        this.Ville = ville;
        this.Code = code;
    }
}
