package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class RecipewriteActivity extends AppCompatActivity {

    private static final int REQUST_CODE=0;

    ImageView main, image1, image2, image3, image4, image5, image6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipewrite);
        Button close;
        close = (Button) findViewById(R.id.closebutton);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.categorysellect);
        ArrayAdapter<CharSequence> spinner_adapter = ArrayAdapter.createFromResource(
                this, R.array.spinnerArray, android.R.layout.simple_spinner_item);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinner_adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String a = spinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        main = findViewById(R.id.recipegallery_main);
        image1 = findViewById(R.id.recipegallery1);
        image2 = findViewById(R.id.recipegallery2);
        image3 = findViewById(R.id.recipegallery3);
        image4 = findViewById(R.id.recipegallery4);
        image5 = findViewById(R.id.recipegallery5);
        image6 = findViewById(R.id.recipegallery6);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUST_CODE);
            }
        });



        Button button_upload = findViewById(R.id.upload);
        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecipewriteActivity.this, RecipeexplanationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    main.setImageBitmap(img);
                } catch (Exception e) {

                }
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
        }
    }

}