package com.example.myapplication.consent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Consent2Activity extends AppCompatActivity {
    private ImageButton btn_back_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consent2);

        ImageButton btn_back_index = findViewById(R.id.back_index);
        btn_back_index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}