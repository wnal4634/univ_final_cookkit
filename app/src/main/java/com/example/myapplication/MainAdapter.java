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

import java.util.ArrayList;
import java.util.Random;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{
    private Context context;
    private Intent intent;
    private ArrayList<MainData> mDataset;

    public class MainViewHolder extends RecyclerView.ViewHolder{
        public TextView title, category;
        public ImageView image;

        public MainViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.meal_title);
            category = (TextView) view.findViewById(R.id.category);
            image = (ImageView) view.findViewById(R.id.recipeView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), RecipeexplanationActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
    public MainAdapter(ArrayList<MainData> mainData){
        this.mDataset = mainData;
    }
//
//    @Override
//    public int getItemViewType(final int position) {
//        return R.layout.main_holder;
//    }
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_holder, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.title.setText(mDataset.get(position).title);
        holder.category.setText(mDataset.get(position).category);
        holder.image.setImageResource(mDataset.get(position).resId);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
