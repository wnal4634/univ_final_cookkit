package com.example.myapplication;


public class voteItem {
    public String title;
    public String category;
    public String member_id;
    public int recipe_id;

    public voteItem( String member_id, int recipe_id, String title, String category) {
        this.title = title;
        this.category = category;
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

