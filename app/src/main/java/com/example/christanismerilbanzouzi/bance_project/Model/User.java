package com.example.christanismerilbanzouzi.bance_project.Model;

/**
 * Created by christanismerilbanzouzi on 17/03/2018.
 */

public class User {

    private int NombreCmd;
    private String Name;
    private String Password;
    private Adresse Adresse;
    private CarteCB CarteCB;

    public User(){

    }

    public Adresse getAdresse() {
        return Adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.Adresse = adresse;
    }

    public CarteCB getCarteCB() {
        return CarteCB;
    }

    public void setCarteCB(CarteCB carteCB) {
        this.CarteCB = carteCB;
    }

    @Override
    public String toString() {
        return "User{" +
                "NombreCmd=" + NombreCmd +
                ", Name='" + Name + '\'' +
                ", Password='" + Password + '\'' +
                ", adresse=" + Adresse +
                ", carteCB=" + CarteCB +
                '}';
    }

    public User(String name, String password, Adresse adresse, CarteCB carteCB) {

        Name = name;
        Password = password;
        NombreCmd = 0;
        this.Adresse = adresse;
        this.CarteCB= carteCB;
    }

    public void setNombreCmd(int nombreCmd) {
        NombreCmd = nombreCmd;
    }

    public int getNombreCmd() {

        return NombreCmd;
    }


    public void setName(String name) {
        Name = name;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

}
