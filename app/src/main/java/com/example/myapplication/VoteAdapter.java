package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.VoteViewHolder> {
    private ArrayList<voteItem> mDataset;
    int selectedPosition = -1;
    ItemClickListener itemClickListener;

    public class VoteViewHolder extends RecyclerView.ViewHolder {
        public TextView title, category, m_id;
        public ImageView image;
        public RadioButton radioButton;

        public VoteViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.meal_title);
            category = view.findViewById(R.id.category);
            m_id = view.findViewById(R.id.writer);
            image = view.findViewById(R.id.recipeView);
            radioButton = view.findViewById(R.id.radio);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {  //클릭 시 해당 레시피로 이동
                    int pos = getAbsoluteAdapterPosition();
                    voteItem voteItem  = mDataset.get(pos);
                    Intent intent = new Intent(v.getContext(), RecipeexplanationActivity.class);
                    intent.putExtra("r_id", voteItem.getRecipe_id());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public VoteAdapter(ArrayList<voteItem> voteItem, ItemClickListener itemClickListener) {
        this.mDataset = voteItem;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public VoteAdapter.VoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vote_item, parent, false);
        return new VoteAdapter.VoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoteAdapter.VoteViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //리사이클러뷰로 타이틀 및 카테고리 등 보여줌
        holder.title.setText(mDataset.get(position).title);
        holder.category.setText(mDataset.get(position).category);
        holder.m_id.setText(mDataset.get(position).member_id);
        holder.image.setImageBitmap(mDataset.get(position).getImage());

        //라디오 체크 설정
        holder.radioButton.setChecked(position == selectedPosition);
        holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //선택한 값 변수에 담기
                    selectedPosition = holder.getAbsoluteAdapterPosition();
                    //클릭 이벤트
                    itemClickListener.onclick(holder.radioButton.getText().toString(),position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    void addItem(voteItem data) {
        // 외부에서 item을 추가시킬 함수입니다.
        mDataset.add(data);
    }
}