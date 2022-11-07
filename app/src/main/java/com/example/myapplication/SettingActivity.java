package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {  //설정 페이지

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ImageButton btn_back_mypage = findViewById(R.id.back_mypage);
        btn_back_mypage.setOnClickListener(new View.OnClickListener() {  //뒤로가기
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}