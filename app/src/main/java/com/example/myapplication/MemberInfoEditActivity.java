package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MemberInfoEditActivity extends AppCompatActivity {
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private EditText correction_post,correction_ad;
    private Button correction_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info_edit);



        ImageButton btn_back_mypage = (ImageButton) findViewById(R.id.back_mypage);
        btn_back_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String UserPost = correction_post.getText().toString();
                final String UserAd = correction_ad.getText().toString();

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
        correction_post = (EditText) findViewById(R.id.editTextTextPersonName145);
        Button btn_search1 = (Button) findViewById(R.id.btnaddresssearch);
        if (btn_search1 != null) {
            btn_search1.setOnClickListener(new View.OnClickListener() {
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
                        correction_post.setText(data);
                    }
                }
                break;
        }

    }
}