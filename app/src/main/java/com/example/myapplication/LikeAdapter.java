package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.LikeViewHolder> {

    private ArrayList<MainData> like;

    public LikeAdapter(String[] title, String[] category) {
    }

    public static class LikeViewHolder extends RecyclerView.ViewHolder{
        public TextView title, category;
        public ImageView image;

        public LikeViewHolder(View view){
            super(view);
            this.title = view.findViewById(R.id.meal_title);
            this.category = view.findViewById(R.id.category);
            this.image = (ImageView) view.findViewById(R.id.recipeView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), RecipeexplanationActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
    public LikeAdapter(ArrayList<MainData> list) {
        like = list ;
    }

    @NonNull
    @Override
    public LikeAdapter.LikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.like_holder, parent, false);
        return new LikeAdapter.LikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikeViewHolder likeViewHolder, int position){
        likeViewHolder.title.setText(like.get(position).title);
        likeViewHolder.category.setText(like.get(position).category);
        likeViewHolder.image.setImageResource(like.get(position).resId);
    }

    @Override
    public int getItemCount() {
        return like.size();
    }
}
