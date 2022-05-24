package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class RecipeManageActivity extends AppCompatActivity {

    View view;
    RecyclerView recyclerView;
    ArrayList<MainData> list = new ArrayList<>();
    ManageAdapter maAdapter;
    ImageButton close;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Data();
        setContentView(R.layout.activity_recipe_manage);
        close = (ImageButton) findViewById(R.id.mypagego);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView = findViewById(R.id.recyclerview);
        maAdapter = new ManageAdapter(list);
        recyclerView.setAdapter(maAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }


    private void Data(){
        for(int i=0;i<3;i++) {
            list.add(new MainData("파스타", "양식"));

        }
    }

}