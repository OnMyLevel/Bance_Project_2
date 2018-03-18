package com.example.christanismerilbanzouzi.bance_project.Model;

/**
 * Created by christanismerilbanzouzi on 18/03/2018.
 */

public class Article {

    private String Name;
    private String Image;
    private String Price;



    public Article(String name, String image, String price) {
        this.Name = name;
        this.Image = image;
        this.Price = price;
    }

    public Article() {

    }

    @Override
    public String toString() {
        return "Article{" +
                "Iame='" + Name + '\'' +
                ", Image='" + Image + '\'' +
                ", Price=" + Price +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String dame) {
        this.Name = dame;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String dmageUrl) {
        this.Image = dmageUrl;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

}
