package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MealOrderStatusAdapter extends RecyclerView.Adapter<MealOrderStatusAdapter.MealOrderStatusHolder>{
    private ArrayList<MealOrderData> oDataset;

    public MealOrderStatusAdapter(ArrayList<MealOrderData> oDataset){
        this.oDataset = oDataset;
    }

    public class MealOrderStatusHolder extends RecyclerView.ViewHolder{
        public TextView set, meal_date, kittitle, price;
        public ImageView image;

        public MealOrderStatusHolder(View view){
            super(view);
            this.kittitle = view.findViewById(R.id.mealkit_title);
            this.price = view.findViewById(R.id.mealkit_price);
            this.set = view.findViewById(R.id.mealkit_set);
            this.image = view.findViewById(R.id.recipeView);
            this.meal_date = view.findViewById(R.id.meal_date);
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
        //리사이클러뷰로 타이틀 및 가격 등 보여줌
        mealOrderStatusHolder.kittitle.setText(oDataset.get(position).title);
        mealOrderStatusHolder.price.setText(oDataset.get(position).price);
        mealOrderStatusHolder.set.setText(oDataset.get(position).count);
        mealOrderStatusHolder.meal_date.setText(oDataset.get(position).date);
        String imageURL = oDataset.get(position).getImage();
        Glide.with(mealOrderStatusHolder.image.getContext()).load(imageURL).into(mealOrderStatusHolder.image);
    }
    @Override
    public int getItemCount(){
        return oDataset.size();
    }

    void addItem(MealOrderData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        oDataset.add(data);
    }
}