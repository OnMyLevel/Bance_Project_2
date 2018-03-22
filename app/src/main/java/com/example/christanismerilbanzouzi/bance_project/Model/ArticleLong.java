package com.example.christanismerilbanzouzi.bance_project.Model;

/**
 * Created by christanismerilbanzouzi on 19/03/2018.
 */

public class ArticleLong {

    private String Name;
    private String Image;
    private String Price;
    private String Description;
    private String CategorieId;
    private String Discount;


    public ArticleLong(String name, String image, String price, String description, String categorieId,String discount) {
        Name = name;
        Image = image;
        Price = price;
        Description = description;
        CategorieId = categorieId;
        Discount = discount;
    }

    public ArticleLong(String name, String image, String price) {
        this.Name = name;
        this.Image = image;
        this.Price = price;
    }

    public ArticleLong(String name, String imageUrl, String price, String description, Integer integer, Integer discount) {

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

    @Override
    public String toString() {
        return "Article{" +
                "Name='" + Name + '\'' +
                ", Image='" + Image + '\'' +
                ", Price=" + Price +
                ", Description='" + Description + '\'' +
                ", CategorieId=" + CategorieId +
                ", Discount=" + Discount +
                '}';
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCategorieId() {
        return CategorieId;
    }

    public void setCategorieId(String categorieId) {
        CategorieId = categorieId;
    }

    public String getDisount() {
        return Discount;
    }

    public void setDisount(String discount) {
        Discount = discount;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

}
