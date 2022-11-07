package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

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

public class MealOrderStatusActivity extends AppCompatActivity {  //밀키트 주문 내역 페이지
    RecyclerView recyclerView;
    ArrayList<MealOrderData> list4 = new ArrayList<>();
    MealOrderStatusAdapter adapter4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_order_status);

        ImageButton btn_back_mypage = findViewById(R.id.back_mypage);
        btn_back_mypage.setOnClickListener(new View.OnClickListener() {  //뒤로가기
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.order_recycler);
        recyclerView.setHasFixedSize(true);
        adapter4 = new MealOrderStatusAdapter(list4);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter4);

        recyclerView.bringToFront();
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
        String member_id = shared_preferences.get_user_email(MealOrderStatusActivity.this);
        String serverUrl = "http://admin0000.dothome.co.kr/meal_order_list.php";  //작성한 레시피 아이디 비교 후 DB에서 불러오기
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                list4.clear();
                adapter4.notifyDataSetChanged();
                try {
                    for(int i=0; i< response.length(); i++){
                        JSONObject jsonObject= response.getJSONObject(i);

                        String m_id = jsonObject.getString("member_id");
                        String title = jsonObject.getString("meal_title");
                        String date = jsonObject.getString("buy_date");
                        String count = jsonObject.getString("meal_set_count");
                        String price = jsonObject.getString("meal_price");
                        String image = jsonObject.getString("meal_image");

                        MealOrderData mealOrderData = new MealOrderData(title, count, price, date, image);
                        if (m_id.equals(member_id))
                            adapter4.addItem(mealOrderData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MealOrderStatusActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        //서버로 Volley를 이용해서 요청
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}