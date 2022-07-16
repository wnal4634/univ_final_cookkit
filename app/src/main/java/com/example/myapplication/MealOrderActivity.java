package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.TextView;
import android.util.Log;
public class MealOrderActivity extends AppCompatActivity {
    private TextView meal_count;
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private EditText meal_post;
    private ImageButton meal_minus, meal_plus;
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_order);
        meal_count=findViewById(R.id.meal_count);
        meal_count.setText(count+"");
        meal_plus=findViewById(R.id.meal_plus);
        meal_minus=findViewById(R.id.meal_minus);

        meal_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;
                meal_count.setText(count+"");
            }
        });

        meal_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>0) {
                    count--;
                    meal_count.setText(count + "");}
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
        });
        meal_post = (EditText) findViewById(R.id.editTextTextPersonName145);
        Button btn_search1 = (Button) findViewById(R.id.button4);
        if (btn_search1 != null) {
            btn_search1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent i = new Intent(MealOrderActivity.this, AdressAPI.class);
                    startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
                }
            });
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    if (data != null) {
                        meal_post.setText(data);
                    }
                }
                break;
        }
    }
}