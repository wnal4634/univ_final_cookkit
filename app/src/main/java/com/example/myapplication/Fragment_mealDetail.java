package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fragment_mealDetail extends Fragment {
    private View view;
    ImageView meal_detail_img;
    TextView meal_name, meal_explain, meal_sale_date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_meal_detail, container, false);

//        Bundle bundle = getArguments();
//        String member_id = bundle.getString("member_id");
//        String phone_num = bundle.getString("phone_num");
//        String post_num = bundle.getString("post_num");
//        String member_ad = bundle.getString("member_ad");

        meal_detail_img = view.findViewById(R.id.meal_detail_img);
        meal_name = view.findViewById(R.id.meal_name);
        meal_explain = view.findViewById(R.id.meal_explain);
        meal_sale_date = view.findViewById(R.id.meal_sale_date);

        Button btn_ordergo = (Button) view.findViewById(R.id.ordergo);
        btn_ordergo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MealOrderActivity.class);
//                intent.putExtra("member_id", (String) member_id);
//                intent.putExtra("phone_num", (String) phone_num);
//                intent.putExtra("post_num", (String) post_num);
//                intent.putExtra("member_ad", (String) member_ad);
                startActivity(intent);
            }
        });

//        ImageButton btn_back_index = (ImageButton) view.findViewById(R.id.back_index);
//        btn_back_index.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
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
//                        String image = jsonObject.getString("meal_image");
//
//                        Bitmap image_bit = StringToBitmap(image);

                        meal_name.setText(title);
                        meal_explain.setText(ex);
                        meal_sale_date.setText(sale);
//                        meal_detail_img.setImageBitmap(image_bit);

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

    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}