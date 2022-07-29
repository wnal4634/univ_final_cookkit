package com.example.myapplication;

import android.graphics.Bitmap;

public class MainData {
    public String title;
    public String category;
    public Bitmap image;
    //    public int resId;

    public MainData(String title, String category, Bitmap image) {
        this.title = title;
        this.category = category;
        this.setImage(image);
    }
//
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }
//
    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}