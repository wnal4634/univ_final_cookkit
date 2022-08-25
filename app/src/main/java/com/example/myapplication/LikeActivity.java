package com.example.myapplication;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

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

public class LikeActivity extends AppCompatActivity {
    ArrayList<MainData> list = new ArrayList<>();
    RecyclerView recyclerView;
    LikeAdapter adapter;
    MyDatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);

        ImageButton btn_back_mypage = (ImageButton) findViewById(R.id.back_mypage);
        btn_back_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.Like_recycler);
        recyclerView.setHasFixedSize(true);
        adapter = new LikeAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onStart() {
        super.onStart();
        myDb = new MyDatabaseHelper(this);
        myDb.getWritableDatabase();

        String serverUrl = "http://admin0000.dothome.co.kr/like_list.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                list.clear();
                adapter.notifyDataSetChanged();

                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String id = jsonObject.getString("member_id");
                        String title = jsonObject.getString("recipe_title");
                        String category = jsonObject.getString("recipe_category");
                        String image = jsonObject.getString("image_main");
                        int click = jsonObject.getInt("click_count");

                        Bitmap image_bit = StringToBitmap(image);

                        MainData mainData = new MainData(title, category,click, image_bit);

                        Cursor cursor = myDb.AllView();
                        while (cursor.moveToNext()) {
                            String sql_mid = cursor.getString(1);
                            String sql_title = cursor.getString(2);
                            if(sql_mid.equals(id) && sql_title.equals(title))
                                adapter.addItem(mainData);
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
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