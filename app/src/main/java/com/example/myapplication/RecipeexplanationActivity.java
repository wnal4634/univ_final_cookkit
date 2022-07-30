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
import android.util.Log;
public class RecipeexplanationActivity extends AppCompatActivity {

    private View view;
    private ImageButton btn_back_index;
    private Button btn_like, btn_thumbs;
    private TextView thumsup_count, favorite_count;
    private int count=0;
    boolean selected = false;
    boolean selected2 = false;
    private Button share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeexplanation);

        thumsup_count=findViewById(R.id.thumsup_count);
        thumsup_count.setText(count+"");
        favorite_count=findViewById(R.id.favorite_count);
        favorite_count.setText(count+"");
        btn_like=findViewById(R.id.thumbsup);
        ImageButton btn_back_index = findViewById(R.id.back_index);
        btn_back_index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(
//                        RecipeexplanationActivity.this, Fragment_index.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
                    count--;
                    thumsup_count.setText(count+"");
                }else {
                    count++;
                    thumsup_count.setText(count+"");
                    btn_thumbs.setSelected(true);
                    selected =true;

                }}

        });
        Button btn_like = findViewById(R.id.favorite);
        btn_like.setOnClickListener(new View.OnClickListener() {
            private int count=0;
            @Override
            public void onClick(View v) {
                if (selected2){
                    btn_like.setSelected(false);
                    selected2 = false;
                    count--;
                    favorite_count.setText(count+"");

                }else {
                    count++;
                    favorite_count.setText(count+"");
                    btn_like.setSelected(true);
                    selected2 = true;
                }
            }
        });
        Button share=findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent Sharing_intent=new Intent(Intent.ACTION_SEND);
                Sharing_intent.setType("text/plain");

                String Test_Message = "공유할 text";

                Sharing_intent.putExtra(Intent.EXTRA_TEXT, Test_Message);

                Intent Sharing = Intent.createChooser(Sharing_intent, "공유하기");
                startActivity(Sharing);
            }

        });


    }
}