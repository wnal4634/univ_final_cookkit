package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class RecoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reco);

        Toolbar toolbar = findViewById(R.id.reco_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("추천한 레시피");

        ArrayList<MainData> list2 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list2.add(new MainData("파스타", "양식"));
        }
        RecyclerView recyclerView = findViewById(R.id.Reco_recycler) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;
        RecoAdapter adapter2 = new RecoAdapter(list2);
        recyclerView.setAdapter(adapter2) ;
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