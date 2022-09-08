package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Register.UpdateRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MemberInfoEditActivity extends AppCompatActivity {
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private EditText postNum, phoneNumber, Address;
    private TextView textview;
    private AlertDialog dialog;

    String member_id, phone_num, post_num, member_ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info_edit);

        member_id = shared_preferences.get_user_email(MemberInfoEditActivity.this);
        phone_num = getIntent().getStringExtra("phone_num");
        post_num = getIntent().getStringExtra("post_num");
        member_ad = getIntent().getStringExtra("member_ad");

        textview = findViewById(R.id.memberID);
        textview.setText(member_id);

        postNum = findViewById( R.id.postNum );
        phoneNumber = findViewById( R.id.phoneNumber );
        Address = findViewById(R.id.Address);

        String serverUrl = "http://admin0000.dothome.co.kr/mem_info.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String id = jsonObject.getString("member_id");
                        String phone = jsonObject.getString("phone_num");
                        String post = jsonObject.getString("post_num");
                        String add = jsonObject.getString("member_ad");

                        if(member_id.equals(id)){
                            phoneNumber.setText(phone);
                            postNum.setText(post);
                            Address.setText(add);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);



        ImageButton btn_back_mypage = (ImageButton) findViewById(R.id.back_mypage);
        btn_back_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btn_info_save = (Button) findViewById(R.id.info_save);
        btn_info_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String UserPhone = phoneNumber.getText().toString();
                final String UserPost = postNum.getText().toString();
                final String UserAd = Address.getText().toString();
                final String UserId = (String) textview.getText();

                //한 칸이라도 입력 안했을 경우
                if (UserPhone.equals("") || UserPost.equals("") || UserAd.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MemberInfoEditActivity.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            if (success) {

                                Toast.makeText(getApplicationContext(), String.format("수정되었습니다."), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MemberInfoEditActivity.this, Fragment_mypage.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                finish();

                                //회원가입 실패시
                            } else {
                                Toast.makeText(getApplicationContext(), "실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                //서버로 Volley를 이용해서 요청
                UpdateRequest updateRequest = new UpdateRequest( UserPhone, UserPost, UserAd, UserId, responseListener);
                RequestQueue queue = Volley.newRequestQueue( MemberInfoEditActivity.this );
                queue.add( updateRequest );
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