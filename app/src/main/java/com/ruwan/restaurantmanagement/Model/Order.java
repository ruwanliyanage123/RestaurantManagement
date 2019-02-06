package com.ruwan.restaurantmanagement.Model;

import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class Order {
    private String ProductId;
    private String ProductName;
    private String Quantiry;
    private String Price;
    private String Discount;


    public Order(){

    }

    public Order(String productId,String productName, String quantiry, String price, String discount){

        ProductId = productId;
        ProductName = productName;
        Quantiry = quantiry;
        Price =  price;
        Discount = discount;
    }

    public String getDiscount() {
        return Discount;
    }

    public String getPrice() {
        return Price;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getProductId() {
        return ProductId;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getQuantiry() {
        return Quantiry;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public void setQuantiry(String quantiry) {
        Quantiry = quantiry;
    }
}
