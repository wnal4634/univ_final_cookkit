package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;

import java.util.ArrayList;

public class MealOrderStatusAdapter extends RecyclerView.Adapter<MealOrderStatusAdapter.MealOrderStatusHolder>{
    private ArrayList<MainData> mDataset3;
    public MealOrderStatusAdapter(ArrayList<MainData> mDataset3){
        this.mDataset3 = mDataset3;
    }

    public class MealOrderStatusHolder extends RecyclerView.ViewHolder{
        TextView title;
        public ImageView image;

        public MealOrderStatusHolder(View view){
            super(view);
            this.title = view.findViewById(R.id.meal_title);
            this.image = (ImageView) view.findViewById(R.id.recipeView);
            this.title=view.findViewById(R.id.meal_price);
        }
    }
    @NonNull
    @Override
    public MealOrderStatusAdapter.MealOrderStatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mealorderstatus_holder, parent, false);
        return new MealOrderStatusAdapter.MealOrderStatusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealOrderStatusAdapter.MealOrderStatusHolder mealOrderStatusHolder, int position){
        mealOrderStatusHolder.title.setText(mDataset3.get(position).title);
        mealOrderStatusHolder.image.setImageResource(mDataset3.get(position).resId);
    }
    @Override
    public int getItemCount(){
        return mDataset3.size();
    }
}