package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.TextView;
import android.util.Log;
public class MealOrderActivity extends AppCompatActivity {
    private TextView meal_count, meal_total_price, meal_name;
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private EditText postNum, phoneNumber, Address;
    private ImageButton meal_minus, meal_plus;
    private int count=0;
    String member_id, phone_num, post_num, member_ad = "";
    Button payment;
    static final int SMS_SEND_PERMISSION = 1;
    static final int SMS_RECIVE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_order);
        meal_count = findViewById(R.id.meal_count);
        meal_count.setText(count+"");
        meal_plus = findViewById(R.id.meal_plus);
        meal_minus = findViewById(R.id.meal_minus);

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

        int permissonCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permissonCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(), "SMS 발신권한 있음", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "SMS 발신권한 없음", Toast.LENGTH_SHORT).show();
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)){
                Toast.makeText(getApplicationContext(), "SMS 권한이 필요합니다", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.SEND_SMS}, SMS_SEND_PERMISSION);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.SEND_SMS}, SMS_SEND_PERMISSION);
            }
        }

        int permissonCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if(permissonCheck2 == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(), "SMS 수신권한 있음", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "SMS 수신권한 없음", Toast.LENGTH_SHORT).show();
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)){
                Toast.makeText(getApplicationContext(), "SMS 권한이 필요합니다", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.RECEIVE_SMS}, SMS_RECIVE_PERMISSION);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.RECEIVE_SMS}, SMS_RECIVE_PERMISSION);
            }
        }

        phoneNumber = findViewById(R.id.phoneNumber);
        Address = findViewById(R.id.Address);
        meal_total_price = findViewById(R.id.meal_total_price);
        meal_name = findViewById(R.id.meal_name);

        payment = findViewById(R.id.payment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneNo = phoneNumber.getText().toString();
                String add = Address.getText().toString();
                String postNo = postNum.getText().toString();
                String price = (String) meal_total_price.getText();
                String name = (String) meal_name.getText();
                String count = (String) meal_count.getText();

                String txt = "CookKit 밀키트 구매 안내\n\n" + name + "\n" + count + "세트, "
                        + price + "원\n" + "주소: " + postNo + ", " + add;

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, txt, null, null);
                    Toast.makeText(getApplicationContext(), "전송 완료!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "전송 오류!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();//오류 원인이 찍힌다.
                    e.printStackTrace();
                }
            }
        });

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