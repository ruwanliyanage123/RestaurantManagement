package com.ruwan.restaurantmanagement.Model;


public class Food {
    private String Description,Discount, Image,MenuId,Name,Price;


    public  Food(){

    }

    public  Food(String name, String image, String description, String price,String discount, String menuId){

        Name = name;
        Image= image;
        Description = description;
        Price = price;
        Discount = discount;
        MenuId   = menuId;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImage() {
        return Image;
    }

    public String getName() {
        return Name;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMenuId() {
        return MenuId;
    }

    public String getPrice() {
        return Price;
    }

    public String getDiscount() {
        return Discount;
    }

    public String getDescription() {
        return Description;
    }

}



