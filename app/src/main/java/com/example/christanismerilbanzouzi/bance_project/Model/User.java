package com.example.christanismerilbanzouzi.bance_project.Model;

/**
 * Created by christanismerilbanzouzi on 17/03/2018.
 */

public class User {

    private String Name;
    private String Password;

    public User(){

    }

    public User(String name, String password) {

        Name = name;
        Password = password;
    }

    public  User(int IdClient,String name,String password){


        this.Name= name;
        this.Password=password;
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

    @Override
    public String toString() {
        return "User{" +
                ", Name='" + Name + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }


}
