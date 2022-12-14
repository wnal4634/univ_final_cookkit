package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
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

public class RecipeManageActivity extends AppCompatActivity {  //레시피 관리 페이지
    RecyclerView recyclerView;
    ArrayList<ManageData> list3 = new ArrayList<>();
    ManageAdapter maAdapter;
    String member_id;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_manage);

        member_id = shared_preferences.get_user_email(RecipeManageActivity.this);
        TextView textview = findViewById(R.id.memberID);
        textview.setText(member_id);

        ImageButton btn_back_mypage = findViewById(R.id.back_mypage);
        btn_back_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.Recipemanage_recycler);
        recyclerView.setHasFixedSize(true);
        maAdapter = new ManageAdapter(list3);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(maAdapter);
        recyclerView.bringToFront();
    }

    @Override
    public void onStart() {
        super.onStart();
        String serverUrl = "http://admin0000.dothome.co.kr/write_recipe_my.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                list3.clear();
                maAdapter.notifyDataSetChanged();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String title = jsonObject.getString("recipe_title");
                        String category = jsonObject.getString("recipe_category");
                        String image = jsonObject.getString("image_main");
                        String id = jsonObject.getString("member_id");
                        int r_id = jsonObject.getInt("recipe_id");

                        Bitmap image_bit = StringToBitmap(image);

                        ManageData manageData = new ManageData(title, category, image_bit, id, r_id);
                        if(id.equals(member_id)) {  //유저 확인 후 작성한 레시피 데이터 저장
                            maAdapter.addItem(manageData);
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
        //유저 확인 후 작성한 레시피 불러오기
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public static Bitmap StringToBitmap(String encodedString) {  //String으로 저장된 이미지를 비트맵으로 변환
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
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
}