package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeexplanationActivity extends AppCompatActivity {

    private View view;
    private ImageButton btn_back_index;
    private Button btn_like, btn_thumbs;
    private TextView thumsup_count, favorite_count;
    boolean selected = false;
    boolean selected2 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeexplanation);


        ImageButton btn_back_index = findViewById(R.id.back_index);
        btn_back_index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        RecipeexplanationActivity.this, Fragment_index.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });
        Button btn_thumbs = findViewById(R.id.thumbsup);
        btn_thumbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected){
                    btn_thumbs.setSelected(false);
                    selected = false;
                }else {
                    btn_thumbs.setSelected(true);
                    selected =true;

                }
            }
        });
        Button btn_like = findViewById(R.id.favorite);
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected2){
                    btn_like.setSelected(false);
                    selected2 = false;
                }else {
                    btn_like.setSelected(true);
                    selected2 = true;
                }
            }
        });
    }
}