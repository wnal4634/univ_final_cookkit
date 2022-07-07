package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.myapplication.consent.Consent1Activity;
import com.example.myapplication.consent.Consent2Activity;
import com.example.myapplication.consent.Consent3Activity;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ImageButton btn_back_login = (ImageButton) findViewById(R.id.back_login);
        btn_back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        ImageButton btn_go_consent1 = (ImageButton) findViewById(R.id.go_consent1);
        btn_go_consent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Consent1Activity.class);
                startActivity(intent);
            }
        });

        ImageButton btn_go_consent2 = (ImageButton) findViewById(R.id.go_consent2);
        btn_go_consent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Consent2Activity.class);
                startActivity(intent);
            }
        });

        ImageButton btn_go_consent3 = (ImageButton) findViewById(R.id.go_consent3);
        btn_go_consent3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Consent3Activity.class);
                startActivity(intent);
            }
        });
    }
}