package com.example.myapplication;

import android.graphics.Bitmap;

public class MainData {  //메인 이미지 등에서 사용하는 데이터(DB에서 불러온 데이터 저장)
    public String title;
    public String category;
    public Bitmap image;
    public int click;
    public int recipe_id;

    public MainData(String title, String category, int click, Bitmap image, int recipe_id) {
        this.title = title;
        this.category = category;
        this.click = click;
        this.setImage(image);
        this.recipe_id = recipe_id;
    }

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

    public Bitmap getImage() {
        return image;
    }
    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getClick() {
        return click;
    }
    public void setClick(int click){
        this.click = click;
    }

    public int getRecipe_id(){
        return recipe_id;
    }
    public void setRecipe_id(int recipe_id){
        this.recipe_id = recipe_id;
    }
}