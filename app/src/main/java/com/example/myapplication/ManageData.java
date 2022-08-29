package com.example.myapplication;

import android.graphics.Bitmap;

public class ManageData {
    public String title;
    public String category;
    public Bitmap image;
    public String member_id;
    public int recipe_id;

    public ManageData(String title, String category, Bitmap image, String member_id, int recipe_id) {
        this.title = title;
        this.category = category;
        this.setImage(image);
        this.member_id = member_id;
        this.recipe_id = recipe_id;
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

    public String getMember_id(){
        return member_id;
    }
    public void setMember_id(String member_id){
        this.member_id = member_id;
    }

    public int getRecipe_id(){
        return recipe_id;
    }
    public void setRecipe_id(int recipe_id){
        this.recipe_id = recipe_id;
    }
}
