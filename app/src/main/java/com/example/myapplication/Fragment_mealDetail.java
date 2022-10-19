package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fragment_mealDetail extends Fragment {
    private View view;
    ImageView meal_detail_img;
    TextView meal_name, meal_explain, meal_sale_date;
    AlertDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_meal_detail, container, false);

        meal_detail_img = view.findViewById(R.id.meal_detail_img);
        meal_name = view.findViewById(R.id.meal_name);
        meal_explain = view.findViewById(R.id.meal_explain);
        meal_sale_date = view.findViewById(R.id.meal_sale_date);

        Button btn_ordergo = (Button) view.findViewById(R.id.ordergo);
        btn_ordergo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (meal_sale_date.getText().equals("밀키트 판매기간이 아닙니다")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    dialog = builder.setMessage("밀키트 판매기간이 아닙니다.\n다음 밀키트를 기다려주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                } else {
                    Intent intent = new Intent(getActivity(), MealOrderActivity.class);
                    startActivity(intent);
                }
            }
        });

        String serverUrl = "http://admin0000.dothome.co.kr/meal_ex.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for(int i=0; i< response.length(); i++){
                        JSONObject jsonObject= response.getJSONObject(i);

                        String title = jsonObject.getString("meal_title");
                        String ex = jsonObject.getString("meal_text");
                        String sale = jsonObject.getString("meal_sale_period");
                        String price = jsonObject.getString("meal_price");
                        String image = jsonObject.getString("meal_image");
                        Glide.with(getActivity()).load(image).into(meal_detail_img);

                        meal_name.setText(title);
                        meal_explain.setText(ex);
                        meal_sale_date.setText(sale);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);

        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

}