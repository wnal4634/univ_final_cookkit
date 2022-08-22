package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Register.ClickRequest;
import com.example.myapplication.Register.RecipewriteRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{
    private Context context;
    private Intent intent;
    private ArrayList<MainData> mDataset;
    int click_count;

    public class MainViewHolder extends RecyclerView.ViewHolder{
        public TextView title, category, click;
        public ImageView image;
        private  int count = 0;
        public MainViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.meal_title);
            category = (TextView) view.findViewById(R.id.category);
            image = (ImageView) view.findViewById(R.id.recipeView);
            click = (TextView) view.findViewById(R.id.click);
            click.setText(count+"");

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAbsoluteAdapterPosition();
                    MainData mainData = mDataset.get(pos);
                    Intent intent = new Intent(v.getContext(), RecipeexplanationActivity.class);
                    intent.putExtra("title", mainData.getTitle());
                    intent.putExtra("count", mainData.getClick());
                    v.getContext().startActivity(intent);

                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject( response );
                                    boolean success = jsonObject.getBoolean( "success" );

                                    if (success) {
                                        click_count = count;
                                    } else {
                                        return;
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        //서버로 Volley를 이용해서 요청
                        ClickRequest clickRequest = new ClickRequest(mainData.title, click_count, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(v.getContext());
                        queue.add( clickRequest );
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
        holder.image.setImageBitmap(mDataset.get(position).getImage());
        holder.click.setText(Integer.toString(mDataset.get(position).click));
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