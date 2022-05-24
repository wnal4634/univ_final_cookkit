package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MemberInfoEditActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info_edit);


        ImageButton btn_back_mypage = (ImageButton) findViewById(R.id.back_mypage);
        btn_back_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MemberInfoEditActivity.this, Fragment_mypage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });
    }
}