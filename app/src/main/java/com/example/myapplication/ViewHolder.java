package com.example.myapplication;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
//공지사항 뷰홀더
    TextView title;
    TextView date;
    TextView content;
    LinearLayout linearLayout;
    LinearLayout detailLayout;

    OnViewHolderItemClickListener onViewHolderItemClickListener;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.noti_title);
        date = itemView.findViewById(R.id.noti_insert_date);
        content = itemView.findViewById(R.id.noti_content);
        linearLayout = itemView.findViewById(R.id.linearlayout);
        detailLayout = itemView.findViewById(R.id.detail_layout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewHolderItemClickListener.onViewHolderItemClick();
            }
        });
    }
    public void onBind(ItemDTO data, int position, SparseBooleanArray selectedItems){
        title.setText(data.getTitle());
        date.setText(data.getDate());
        content.setText(data.getContent());
        changeVisibility(selectedItems.get(position));
    }

    private void changeVisibility(final boolean isExpanded) {
        // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
        ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, 600) : ValueAnimator.ofInt(600, 0);
        // Animation이 실행되는 시간, n/1000초
        va.setDuration(300);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // imageView의 높이 변경
                detailLayout.getLayoutParams().height = (int) animation.getAnimatedValue();
                detailLayout.requestLayout();
                // imageView가 실제로 사라지게하는 부분
                detailLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            }
        });
        // Animation start
        va.start();
    }

    public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener) {
        this.onViewHolderItemClickListener = onViewHolderItemClickListener;
    }
}

