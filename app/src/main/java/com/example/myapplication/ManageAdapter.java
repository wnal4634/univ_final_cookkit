package com.example.myapplication;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Register.DeleteRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManageAdapter extends RecyclerView.Adapter<ManageAdapter.ManageViewHolder>  {

    private ArrayList<ManageData> mDataset;
    TextView member_id;

    public class ManageViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        Button modifybtn;
        Button deletebtn;
        public ManageViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.meal_title);
            image = view.findViewById(R.id.recipeView);
            deletebtn = view.findViewById(R.id.deletebtn);
            member_id = view.findViewById(R.id.memberID);
            modifybtn = view.findViewById(R.id.modifybtn);
            modifybtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {  //클릭 시 해당 레시피에 대한 수정페이지로 이동
                    int pos = getAbsoluteAdapterPosition();
                    ManageData manageData = mDataset.get(pos);
                    Intent intent = new Intent(view.getContext(), RecipeEditActivity.class);
                    intent.putExtra("r_id", manageData.getRecipe_id());
                    view.getContext().startActivity(intent);
                }
            });
            deletebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {  //레시피 삭제
                    int pos = getAbsoluteAdapterPosition();
                    ManageData manageData = mDataset.get(pos);
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("삭제하시겠습니까?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        //ok 버튼을 누르면 레시피 아이디 확인 후 해당 레시피 삭제
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Response.Listener<String> responseListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject( response );
                                        boolean success = jsonObject.getBoolean( "success" );
                                        if (success) {
                                            Toast.makeText(v.getContext(), String.format("레시피를 삭제했습니다."), Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(v.getContext(), RecipeManageActivity.class);
                                            intent.putExtra("r_id", manageData.getRecipe_id());
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            mDataset.remove(getAbsoluteAdapterPosition());
                                            notifyDataSetChanged();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            //서버로 Volley를 이용해서 요청
                            DeleteRequest deleteRequest = new DeleteRequest(String.valueOf(manageData.recipe_id), responseListener);
                            RequestQueue queue = Volley.newRequestQueue( v.getContext());
                            queue.add( deleteRequest );
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {  //취소
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(v.getContext(), String.format("취소"), Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });
        }
    }

    public ManageAdapter(ArrayList<ManageData> manageData){
        this.mDataset = manageData;
    }
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
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    void addItem(ManageData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        mDataset.add(data);
    }

    public long getItemId(int position) {
        return position;
    }
}