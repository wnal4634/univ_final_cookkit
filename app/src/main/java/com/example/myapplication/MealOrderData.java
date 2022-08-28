package com.example.myapplication;

public class MealOrderData {
    public String count;
    public String price;
    public String title;
    public String date;
    public String image;

    public MealOrderData(String title, String count, String price, String date, String image) {
        this.title = title;
        this.count = count;
        this.price = price;
        this.date = date;
        this.image = image;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getCount(){
        return count;
    }
    public void setCount(String count){
        this.count = count;
    }

    public String getPrice(){
        return price;
    }
    public void setPrice(String price){
        this.price = price;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public String getImage(){
        return image;
    }
    public void setImage(String image){
        this.image = image;
    }

}