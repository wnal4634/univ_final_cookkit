package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MealOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_order);

        ImageButton btn_back_index = (ImageButton) findViewById(R.id.back_index);
        btn_back_index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MealOrderActivity.this, Fragment_index.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });
    }
}
