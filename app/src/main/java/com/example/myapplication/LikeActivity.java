package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class LikeActivity extends AppCompatActivity {
    ArrayList<MainData> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);

        Toolbar toolbar = findViewById(R.id.like_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("좋아요한 레시피");
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