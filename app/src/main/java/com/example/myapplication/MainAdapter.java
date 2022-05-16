package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{

    private ArrayList<MainData> mDataset;

    public class MainViewHolder extends RecyclerView.ViewHolder{
        public TextView title, category;

        public MainViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.meal_title);
            category = (TextView) view.findViewById(R.id.category);
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
        holder.title.setText(mDataset.get(position).getTitle());
        holder.category.setText(mDataset.get(position).getCategory());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
