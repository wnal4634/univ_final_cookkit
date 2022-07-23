package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MealOrderStatusAdapter extends RecyclerView.Adapter<MealOrderStatusAdapter.MealOrderStatusHolder>{
    private ArrayList<MealOrderData> oDataset;
    public MealOrderStatusAdapter(ArrayList<MealOrderData> oDataset){
        this.oDataset = oDataset;
    }

    public class MealOrderStatusHolder extends RecyclerView.ViewHolder{
        TextView kittitle;
        TextView price;
        TextView set;
        public ImageView image;

        public MealOrderStatusHolder(View view){
            super(view);
            this.kittitle = view.findViewById(R.id.mealkit_title);
            this.price = view.findViewById(R.id.mealkit_price);
            this.set = view.findViewById(R.id.mealkit_set);
            this.image = (ImageView) view.findViewById(R.id.recipeView);
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
        mealOrderStatusHolder.kittitle.setText(oDataset.get(position).kittitle);
        mealOrderStatusHolder.price.setText(oDataset.get(position).price);
        mealOrderStatusHolder.set.setText(oDataset.get(position).set);
        mealOrderStatusHolder.image.setImageResource(oDataset.get(position).resId);
    }
    @Override
    public int getItemCount(){
        return oDataset.size();
    }
}