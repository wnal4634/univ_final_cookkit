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
    private EditText postNum;
    private ImageButton meal_minus, meal_plus;
    private int count=0;
    String member_id, phone_num, post_num, member_ad = "";

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

        ImageButton btn_back_index = (ImageButton) findViewById(R.id.back_index);
        btn_back_index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(
//                        MealOrderActivity.this, Fragment_mealDetail.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        postNum = (EditText) findViewById(R.id.postNum);
        Button btn_search = (Button) findViewById(R.id.adress_detail);
        if (btn_search != null) {
            btn_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent i = new Intent(MealOrderActivity.this, AdressAPI.class);
                    startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
                }
            });
        }


//        member_id = getIntent().getStringExtra("member_id");
//        phone_num = getIntent().getStringExtra("phone_num");
//        post_num = getIntent().getStringExtra("post_num");
//        member_ad = getIntent().getStringExtra("member_ad");
//        Intent intent = getIntent();
//        String hi_ID = intent.getStringExtra("member_id");
//        TextView textview = (TextView)findViewById(R.id.memberID);
//        textview.setText(hi_ID);
//        String savePhone = intent.getStringExtra("phone_num");
//        EditText editText = (EditText)findViewById(R.id.phoneNumber);
//        editText.setText(savePhone);
//        String savePost = intent.getStringExtra("post_num");
//        EditText editText2 = (EditText)findViewById(R.id.postNum);
//        editText2.setText(savePost);
//        String saveAdress = intent.getStringExtra("member_ad");
//        EditText editText3 = (EditText)findViewById(R.id.Adress);
//        editText3.setText(saveAdress);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    if (data != null) {
                        postNum.setText(data);
                    }
                }
                break;
        }
    }

}

