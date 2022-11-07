package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Register.ClickRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{
    private ArrayList<MainData> mDataset;
    int click_count;

    public class MainViewHolder extends RecyclerView.ViewHolder{
        public TextView title, category, click;
        public ImageView image;
        private  int count = 0;
        public MainViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.meal_title);
            category = view.findViewById(R.id.category);
            image = view.findViewById(R.id.recipeView);
            click = view.findViewById(R.id.click);
            click.setText(count+"");

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {  //클릭 시 해당 레시피로 이동
                    int pos = getAbsoluteAdapterPosition();
                    MainData mainData = mDataset.get(pos);
                    Intent intent = new Intent(v.getContext(), RecipeexplanationActivity.class);
                    intent.putExtra("r_id", mainData.getRecipe_id());
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
                        ClickRequest clickRequest = new ClickRequest(String.valueOf(mainData.recipe_id), click_count, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(v.getContext());
                        queue.add( clickRequest );
                    }
            });
        }
    }

    public MainAdapter(ArrayList<MainData> mainData){
        this.mDataset = mainData;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_holder, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        //리사이클러뷰로 타이틀 및 카테고리 등 보여줌
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