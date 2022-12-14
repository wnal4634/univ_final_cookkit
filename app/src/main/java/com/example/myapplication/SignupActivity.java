package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Register.RegisterRequest;
import com.example.myapplication.Register.ValidateRequest;
import com.example.myapplication.consent.Consent1Activity;
import com.example.myapplication.consent.Consent2Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {  //회원가입 페이지

    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private EditText join_id, join_password, join_name, join_pwck, join_email, join_phone, join_post,join_ad;
    private Button join_button, check_button;
    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //아이디값 찾아주기
        join_id = findViewById( R.id.join_id );
        join_password = findViewById( R.id.join_password );
        join_name = findViewById( R.id.join_name );
        join_pwck = findViewById(R.id.join_pwck);
        join_email = findViewById(R.id.join_email);
        join_phone = findViewById(R.id.join_phone);
        join_post = findViewById(R.id.join_post);
        join_ad = findViewById(R.id.join_ad);
        CheckBox checkBox = findViewById(R.id.checkBox);

        ImageButton btn_back_login = findViewById(R.id.back_login);
        btn_back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //뒤로가기
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //아이디 중복 체크
        check_button = findViewById(R.id.check_button);
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserId = join_id.getText().toString();
                if (validate) {
                    return; //검증 완료
                }

                if (UserId.equals("")) {  //아이디 입력이 안 된 경우
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    dialog = builder.setMessage("아이디를 입력하세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                                dialog = builder.setMessage("사용할 수 있는 아이디입니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                join_id.setEnabled(false); //아이디값 고정
                                validate = true; //검증 완료
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                                dialog = builder.setMessage("이미 존재하는 아이디입니다.").setNegativeButton("확인", null).create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //서버로 Volley를 이용해서 요청
                ValidateRequest validateRequest = new ValidateRequest(UserId, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                queue.add(validateRequest);
            }
        });

        //회원가입 버튼 클릭 시 수행
        join_button = findViewById( R.id.join_button );
        join_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String UserId = join_id.getText().toString();
                final String UserPwd = join_password.getText().toString();
                final String UserName = join_name.getText().toString();
                final String PassCk = join_pwck.getText().toString();
                final String UserEmail = join_email.getText().toString();
                final String UserPhone = join_phone.getText().toString();
                final String UserPost = join_post.getText().toString();
                final String UserAd = join_ad.getText().toString();

                //아이디 중복체크 했는지 확인
                if (!validate) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    dialog = builder.setMessage("중복된 아이디가 있는지 확인하세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                //한 칸이라도 입력 안 했을 경우
                if (UserId.equals("") || UserPwd.equals("") || UserName.equals("") || UserEmail.equals("") || UserPhone.equals("") ||
                UserPost.equals("") || UserAd.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                if(checkBox.isChecked()){
                } else {  //동의사항 체크 안 된 경우
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    dialog = builder.setMessage("동의사항을 체크해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );
                            //회원가입 성공시
                            if(UserPwd.equals(PassCk)) {
                                if (success) {

                                    Toast.makeText(getApplicationContext(), String.format("가입을 환영합니다."), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                } else {  //회원가입 실패
                                    Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                                dialog = builder.setMessage("비밀번호가 동일하지 않습니다.").setNegativeButton("확인", null).create();
                                dialog.show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //서버로 Volley를 이용해서 요청
                RegisterRequest registerRequest = new RegisterRequest( UserId, UserPwd, UserName, UserEmail, UserPhone,
                        UserPost, UserAd, responseListener);
                RequestQueue queue = Volley.newRequestQueue( SignupActivity.this );
                queue.add( registerRequest );
            }
        });

        ImageButton btn_go_consent1 = findViewById(R.id.go_consent1);
        btn_go_consent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //동의사항1 확인
                Intent intent = new Intent(getApplicationContext(), Consent1Activity.class);
                startActivity(intent);
            }
        });

        ImageButton btn_go_consent2 = findViewById(R.id.go_consent2);
        btn_go_consent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //동의사항2 확인
                Intent intent = new Intent(getApplicationContext(), Consent2Activity.class);
                startActivity(intent);
            }
        });

        ImageButton btn_go_consent3 = findViewById(R.id.go_consent3);
        btn_go_consent3.setOnClickListener(new View.OnClickListener() {  //동의사항3 확인
            final List<String> list = new ArrayList<String>();
            @Override
            public void onClick(View view) {
                final String[] items = new String[]{"SMS 수신 동의 (선택)", "E-Mail 수신 동의 (선택)"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(SignupActivity.this);
                dialog.setTitle("마케팅 활용 동의 및 광고 수신 동의")
                        .setMultiChoiceItems(
                                items
                                , new boolean[]{false, false}
                                , new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                                        if (isChecked) {
                                            Toast.makeText(SignupActivity.this
                                            , items[which]
                                            , Toast.LENGTH_SHORT).show();
                                            list.add(items[which]);
                                        } else {
                                            list.remove(items[which]);
                                        }
                                    }
                                })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                String selectedItem = "";
                                for (String item : list) {
                                    selectedItem += item + ", ";
                                }
                                Toast.makeText(SignupActivity.this
                                , selectedItem
                                , Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                dialog.create();
                dialog.show();
            }
        });

        join_post = findViewById(R.id.join_post);
        Button btn_search = findViewById(R.id.adress_detail);
        if (btn_search != null) {  //주소검색 API 사용
            btn_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(SignupActivity.this, AdressAPI.class);
                    startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
                }
            });
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    if (data != null) {
                        join_post.setText(data);
                    }
                }
                break;
        }
    }
}