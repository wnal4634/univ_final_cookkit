package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MealOrderStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_order_status);

        ImageButton btn_back_mypage = (ImageButton) findViewById(R.id.back_mypage);
        btn_back_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MealOrderStatusActivity.this, Fragment_mypage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        ArrayList<MealOrderData> list4 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list4.add(new MealOrderData("1세트", "14,000원","치즈 돈가스 밀키트", R.drawable.porkcutlet));
        }
        RecyclerView recyclerView = findViewById(R.id.order_recycler) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;
        MealOrderStatusAdapter adapter4 = new MealOrderStatusAdapter(list4);
        recyclerView.setAdapter(adapter4) ;
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