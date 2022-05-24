package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecoAdapter extends RecyclerView.Adapter<RecoAdapter.RecoHolder> {
    private ArrayList<MainData> mDataset2;
    public RecoAdapter(ArrayList<MainData> mDataset2){
        this.mDataset2 = mDataset2;
    }

    public class RecoHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView category;

        public RecoHolder(View view){
            super(view);
            this.title = view.findViewById(R.id.meal_title);
            this.category = view.findViewById(R.id.category);
        }
    }
    @NonNull
    @Override
    public RecoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reco_holder, parent, false);
        return new RecoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecoHolder recoHolder, int position){
        recoHolder.title.setText(mDataset2.get(position).getTitle());
        recoHolder.category.setText(mDataset2.get(position).getCategory());
    }
    @Override
    public int getItemCount(){
        return mDataset2.size();
    }
}
