package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Register.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {  //로그인 페이지

    private EditText login_email, login_password;
    private Button login_button;
    private CheckBox autoLogin;
    String id;
    private Boolean saveLoginData;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        load();
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        autoLogin = findViewById(R.id.checkBox);
        login_button = findViewById(R.id.login_button);

        //전에 자동 로그인을 눌렀는지 확인
        if (shared_preferences.get_user_email(LoginActivity.this).length() != 0) {  //로그인 고유데이터(현재는 이메일) 길이 0 아닐시
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);//액티비티 스택제거
            Toast.makeText(getApplicationContext(), "자동 로그인 되었습니다", Toast.LENGTH_SHORT).show();
            finish();
        }

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserId = login_email.getText().toString();
                String UserPwd = login_password.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) {  //로그인 성공시
                                String UserId = jsonObject.getString("member_id");
                                String UserPwd = jsonObject.getString("member_pw");
                                String UserName = jsonObject.getString("name");
                                String UserNum = jsonObject.getString("phone_num");
                                String UserPost = jsonObject.getString("post_num");
                                String UserAd = jsonObject.getString("member_ad");

                                Toast.makeText(getApplicationContext(), String.format(UserId + "님 환영합니다."), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("member_id", UserId);
                                intent.putExtra("member_pw", UserPwd);
                                intent.putExtra("name", UserName);
                                intent.putExtra("phone_num", UserNum);
                                intent.putExtra("post_num", UserPost);
                                intent.putExtra("member_ad", UserAd);
                                save();
                                startActivity(intent);
                                finish();

                            } else {  //로그인 실패시
                                Toast.makeText(getApplicationContext(), "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //서버로 Volley를 이용해서 요청
                LoginRequest loginRequest = new LoginRequest(UserId, UserPwd, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

            }
        });

        id = pref.getString("member_id",null);

        //자동 로그인 클릭하면 로그인 정보 저장
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shared_preferences.set_user_email(LoginActivity.this, id);
            }
        });

        TextView mEmailSignUp = findViewById(R.id.join_button);// sign up button

        mEmailSignUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void save(){
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("save", autoLogin.isChecked());
        editor.putString("member_id",login_email.getText().toString().trim());
        editor.apply();
    }

    private void load(){
        saveLoginData = pref.getBoolean("save", false);
        id = pref.getString("member_id","");
    }
}

