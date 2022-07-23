package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.security.identity.PersonalizationData;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class noticeActivity extends AppCompatActivity {

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

//        addDTO();

        ImageButton btn_back_mypage = (ImageButton) findViewById(R.id.back_mypage);
        btn_back_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        noticeActivity.this, Fragment_mypage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });


//        new BackgroundTask().execute();
//    }
//
//    class BackgroundTask extends AsyncTask<Void, Void, String> {
//        String target;
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            target = "http://admin0000.dothome.co.kr/notice_list.php";
//        }
//
//        @Override
//        protected String doInBackground(Void... voids) {
//            try {
//                URL url = new URL(target);
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                String temp;
//                StringBuilder stringBuilder = new StringBuilder();
//                while((temp = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(temp + "");
//                }
//                bufferedReader.close();
//                inputStream.close();
//                httpURLConnection.disconnect();
//                return stringBuilder.toString().trim();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//        @Override
//        protected void onPostExecute(String result){
//            super.onPostExecute(result);
//            try {
//                JSONObject jsonObject = new JSONObject(result);
//                JSONArray jsonArray = jsonObject.getJSONArray("response");
//                int count = 0;
//                String notice_title, notice_content, notice_date;
//                while (count < jsonArray.length()) {
//                    JSONObject object = jsonArray.getJSONObject(count);
//                    notice_title = object.getString("notice_title");
//                    notice_content = object.getString("notice_content");
//                    notice_date = object.getString("notice_date");
//                    itemDTOS.add(notice_title, notice_date, notice_content);
//                    count++;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
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
        String serverUrl = "http://admin0000.dothome.co.kr/notice_list.php";
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

//                        itemDTOS.add(0,new ItemDTO(title, date, content)); // 첫 번째 매개변수는 몇번째에 추가 될지, 제일 위에 오도록
//                        adapter.notifyItemInserted(0);
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

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

//    private void init() {
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        adapter = new RecyclerVierAdapter();
//        recyclerView.setAdapter(adapter);
//    }

    //임의로 dto 10개 생성해서 adapter에 있는 array에 추가
    private void addDTO() {
        for (int i = 0; i < 3; i++) {
            ItemDTO itemData = new ItemDTO("문의내용", "2022/04/29 14:00", "asdaaaa");
            adapter.addItem(itemData);
        }
    }

}

    