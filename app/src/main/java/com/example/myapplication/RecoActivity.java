package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class RecoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reco);

        ImageButton btn_back_mypage = (ImageButton) findViewById(R.id.back_mypage);
        btn_back_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(
//                        RecoActivity.this, Fragment_mypage.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        ArrayList<MainData> list2 = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            list2.add(new MainData("돈가스", "양식",R.drawable.porkcutlet));
//        }
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