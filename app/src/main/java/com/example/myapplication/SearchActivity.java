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

public class SearchActivity extends AppCompatActivity {  //검색 결과 페이지
    RecyclerView recyclerView;
    ArrayList<MainData> list4 = new ArrayList<>();
    SearchAdapter saAdapter;
    TextView result;
    String result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ImageButton btn_back = findViewById(R.id.back_btn);
        btn_back.setOnClickListener(new View.OnClickListener() {  //뒤로가기
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        result2 = getIntent().getStringExtra("result");
        result = findViewById(R.id.result);
        result.setText(result2);

        recyclerView = findViewById(R.id.Search_recycler);
        recyclerView.setHasFixedSize(true);
        saAdapter = new SearchAdapter(list4);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(saAdapter);

        String serverUrl = "http://admin0000.dothome.co.kr/Search.php";  //겸색 결과에 해당하는 결과 불러옴
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                list4.clear();
                saAdapter.notifyDataSetChanged();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String title = jsonObject.getString("recipe_title");
                        String category = jsonObject.getString("recipe_category");
                        String image = jsonObject.getString("image_main");
                        int click = jsonObject.getInt("click_count");
                        int r_id = jsonObject.getInt("recipe_id");

                        Bitmap image_bit = StringToBitmap(image);

                        MainData mainData = new MainData(title, category, click, image_bit, r_id);
                        if(title.contains(result2)) {
                            saAdapter.addItem(mainData);
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
        //서버로 Volley를 이용해서 요청
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onStart() {
        super.onStart();
        String serverUrl = "http://admin0000.dothome.co.kr/Search.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                list4.clear();
                saAdapter.notifyDataSetChanged();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String title = jsonObject.getString("recipe_title");
                        String category = jsonObject.getString("recipe_category");
                        String image = jsonObject.getString("image_main");
                        int click = jsonObject.getInt("click_count");
                        int r_id = jsonObject.getInt("recipe_id");

                        Bitmap image_bit = StringToBitmap(image);

                        MainData mainData = new MainData(title, category,click, image_bit, r_id);
                        if(title.contains(result2)) {
                            saAdapter.addItem(mainData);
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
        //서버로 Volley를 이용해서 요청
        RequestQueue requestQueue = Volley.newRequestQueue(SearchActivity.this);
        requestQueue.add(jsonArrayRequest);
    }

    public static Bitmap StringToBitmap(String encodedString) {
        try {  //String으로 저장된 이미지를 비트맵으로 변환
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}