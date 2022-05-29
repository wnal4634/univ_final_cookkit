package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class LikeActivity extends AppCompatActivity {
    ArrayList<MainData> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);

        ImageButton btn_back_mypage = (ImageButton) findViewById(R.id.back_mypage);
        btn_back_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        LikeActivity.this, Fragment_mypage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.Like_recycler) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;
        LikeAdapter adapter = new LikeAdapter(list);
        recyclerView.setAdapter(adapter) ;
        Data1();

    }
    private void Data1(){
        for(int i=0;i<3;i++) {
            list.add(new MainData("돈가스", "양식",R.drawable.porkcutlet));
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