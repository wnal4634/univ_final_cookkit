package com.example.myapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class ManageAdapter extends RecyclerView.Adapter<ManageAdapter.ManageViewHolder> {

    private ArrayList<MainData> mDataset;

    public class ManageViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;

        public ManageViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.meal_title);
            image = (ImageView) view.findViewById(R.id.recipeView);
        }

    }



    public ManageAdapter(ArrayList<MainData> mainData){
        this.mDataset = mainData;
    }
    //
//    @Override
//    public int getItemViewType(final int position) {
//        return R.layout.main_holder;
//    }
    @NonNull
    @Override
    public ManageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipemanage_holder, parent, false);
        return new ManageViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ManageViewHolder holder, int position) {
        holder.title.setText(mDataset.get(position).title);
        holder.image.setImageResource(mDataset.get(position).resId);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}