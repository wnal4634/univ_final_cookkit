package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class noticeActivity extends AppCompatActivity {

    RecyclerVierAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        init();
        addDTO();
    }
    private void init(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerVierAdapter();
        recyclerView.setAdapter(adapter);
    }

    //임의로 dto 10개 생성해서 adapter에 있는 array에 추가
    private void addDTO(){
        for(int i=0;i<10;i++){
            ItemDTO itemData = new ItemDTO("문의내용", "2022/04/29 14:00","asdaaaa");
            adapter.addItem(itemData);
        }
    }
}