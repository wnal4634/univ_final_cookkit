package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

public class noticeActivity extends AppCompatActivity {  //공지사항 페이지
    RecyclerVierAdapter adapter;
    ArrayList<ItemDTO> itemDTOS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerVierAdapter();
        recyclerView.setAdapter(adapter);

        ImageButton btn_back_mypage = findViewById(R.id.back_mypage);
        btn_back_mypage.setOnClickListener(new View.OnClickListener() { //뒤로가기
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String serverUrl = "http://admin0000.dothome.co.kr/notice_list.php";  //공지사항 불러오기
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                itemDTOS.clear();
                adapter.notifyDataSetChanged();
                try {
                    for(int i=0; i< response.length(); i++){
                        JSONObject jsonObject= response.getJSONObject(i);

                        String title = jsonObject.getString("notice_title");
                        String date = jsonObject.getString("notice_date");
                        String content = jsonObject.getString("notice_content");

                        ItemDTO itemData = new ItemDTO(title, date, content);
                        adapter.addItem(itemData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(noticeActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        //서버로 Volley를 이용해서 요청
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}

    