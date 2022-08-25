package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    View view;
    RecyclerView recyclerView;
    ArrayList<MainData> list5 = new ArrayList<>();
    CateAdapter caAdapter;
    TextView result;
    String result2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ImageButton btn_back = (ImageButton) findViewById(R.id.back_btn);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

         result2 = getIntent().getStringExtra("cate");
        result = findViewById(R.id.result);
        result.setText(result2);

        recyclerView = findViewById(R.id.Cate_recycler);
        recyclerView.setHasFixedSize(true);
        caAdapter = new CateAdapter(list5);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(caAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        String serverUrl = "http://admin0000.dothome.co.kr/Search.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                list5.clear();
                caAdapter.notifyDataSetChanged();

                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String title = jsonObject.getString("recipe_title");
                        String category = jsonObject.getString("recipe_category");
                        String image = jsonObject.getString("image_main");
                        int click = jsonObject.getInt("click_count");

                        Bitmap image_bit = StringToBitmap(image);

                        MainData mainData = new MainData(title, category,click, image_bit);
                        if(category.equals(result2)) {
                            caAdapter.addItem(mainData);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(CategoryActivity.this);
        requestQueue.add(jsonArrayRequest);

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