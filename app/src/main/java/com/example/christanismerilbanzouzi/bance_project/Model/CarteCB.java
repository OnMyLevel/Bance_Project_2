package com.example.christanismerilbanzouzi.bance_project.Model;

/**
 * Created by christanismerilbanzouzi on 09/04/2018.
 */
public class CarteCB {

    private String Numero;
    private String Code;
    private String Date;

    public CarteCB(){

    }

    @Override
    public String toString() {
        return " Référence de votre carte Bancaire :" + "\n"+
                " Votre Numéro='" + Numero + '\'' +"\n"+
                " Votre code secret='" + Code + '\'' +"\n"+
                " Date d'invalidité='" + Date + '\'' +"\n";
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        this.Numero = numero;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public CarteCB(String numero, String code, String date) {

        this.Numero = numero;
        this.Code = code;
        this.Date = date;
    }
}
