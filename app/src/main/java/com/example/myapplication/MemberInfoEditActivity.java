package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MemberInfoEditActivity extends AppCompatActivity {
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private EditText postNum;

    String member_id, phone_num, post_num, member_ad = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info_edit);

        member_id = getIntent().getStringExtra("member_id");
        phone_num = getIntent().getStringExtra("phone_num");
        post_num = getIntent().getStringExtra("post_num");
        member_ad = getIntent().getStringExtra("member_ad");

        Intent intent = getIntent();

        String hi_ID = intent.getStringExtra("member_id");
        TextView textview = (TextView)findViewById(R.id.memberID);
        textview.setText(hi_ID);

        String savePhone = intent.getStringExtra("phone_num");
        EditText editText = (EditText)findViewById(R.id.phoneNumber);
        editText.setText(savePhone);

        String savePost = intent.getStringExtra("post_num");
        EditText editText2 = (EditText)findViewById(R.id.postNum);
        editText2.setText(savePost);

        String saveAdress = intent.getStringExtra("member_ad");
        EditText editText3 = (EditText)findViewById(R.id.Adress);
        editText3.setText(saveAdress);



        ImageButton btn_back_mypage = (ImageButton) findViewById(R.id.back_mypage);
        btn_back_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                final String UserPost = correction_post.getText().toString();
//                final String UserAd = correction_ad.getText().toString();
                Intent intent = new Intent(
                        MemberInfoEditActivity.this, Fragment_mypage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        Button btn_info_save = (Button) findViewById(R.id.info_save);
        btn_info_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MemberInfoEditActivity.this, Fragment_mypage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
                    Intent i = new Intent(MemberInfoEditActivity.this, AdressAPI.class);
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
                        postNum.setText(data);
                    }
                }
                break;
        }
    }

}