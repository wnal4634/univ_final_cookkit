package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private ArrayList<MainData> mDataset;

    public class SearchViewHolder extends RecyclerView.ViewHolder{
        public TextView title, category;
        public ImageView image;

        public SearchViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.meal_title);
            category = (TextView) view.findViewById(R.id.category);
            image = (ImageView) view.findViewById(R.id.recipeView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAbsoluteAdapterPosition();
                    MainData mainData = mDataset.get(pos);
                    Intent intent = new Intent(v.getContext(), RecipeexplanationActivity.class);
                    intent.putExtra("title", mainData.getTitle());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
    public SearchAdapter(ArrayList<MainData> mainData){this.mDataset = mainData;}

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_holder, parent, false);
        return new SearchViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        holder.title.setText(mDataset.get(position).title);
        holder.category.setText(mDataset.get(position).category);
        holder.image.setImageBitmap(mDataset.get(position).getImage());
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    void addItem(MainData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        mDataset.add(data);
    }
}
