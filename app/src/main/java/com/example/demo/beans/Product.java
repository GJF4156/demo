package com.example.demo.beans;

import android.graphics.drawable.Drawable;

public class Product {

    private String imgUrl;
    private String description;
    private String price;
    private String sell;

    public Product() {
    }

    public Product(String imgUrl, String description, String price, String sell) {
        this.imgUrl = imgUrl;
        this.description = description;
        this.price = price;
        this.sell = sell;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    @Override
    public String toString() {
        return "Product{" +
                "imgUrl='" + imgUrl + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", sell='" + sell + '\'' +
                '}';
    }
}
