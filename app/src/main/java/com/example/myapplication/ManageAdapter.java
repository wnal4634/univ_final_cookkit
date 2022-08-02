package com.example.myapplication;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class ManageAdapter extends RecyclerView.Adapter<ManageAdapter.ManageViewHolder>  {

    //    private ArrayList<MainData> mDataset;
    private ArrayList<ManageData> mDataset;
    Button deletebtn;


    public class ManageViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;
        public Button modifybtn;

        public ManageViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.meal_title);
            image = (ImageView) view.findViewById(R.id.recipeView);
            deletebtn = (Button) view.findViewById(R.id.deletebtn);
            modifybtn = view.findViewById(R.id.modifybtn);
            modifybtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAbsoluteAdapterPosition();
                    ManageData manageData = mDataset.get(pos);
                    Intent intent = new Intent(view.getContext(), RecipeEditActivity.class);
                    intent.putExtra("title", manageData.getTitle());
                    intent.putExtra("id", manageData.getMember_id());
                    view.getContext().startActivity(intent);
                }
            });
        }

    }



    public ManageAdapter(ArrayList<ManageData> manageData){
        this.mDataset = manageData;
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
    public void onBindViewHolder(@NonNull ManageViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(mDataset.get(position).title);
        holder.image.setImageBitmap(mDataset.get(position).image);
        holder.title.setTag(position);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataset.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mDataset.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    void addItem(ManageData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        mDataset.add(data);
    }

    public String getItem(int position) {
        return mDataset.get(position).getTitle();
    }

    public long getItemId(int position) {
        return position;
    }

}