package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class RecipeManageActivity extends AppCompatActivity {

    View view;
    RecyclerView recyclerView;
    ArrayList<MainData> list3 = new ArrayList<>();
    ManageAdapter maAdapter;
    ImageButton close;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Data();
        setContentView(R.layout.activity_recipe_manage);
        Toolbar toolbar = findViewById(R.id.recipemanage_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("작성한 레시피");

        recyclerView = findViewById(R.id.Recipemanage_recycler);
        ManageAdapter adapter3= new ManageAdapter(list3);
        recyclerView.setAdapter(adapter3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
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
    private void Data(){
        for(int i=0;i<3;i++) {
            list3.add(new MainData("파스타", "양식"));

        }
    }

}