package com.example.myapplication;

public class MainData {
    public String title;
    public String category;

    public MainData(String title, String category) {
        this.title = title;
        this.category = category;
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
}
