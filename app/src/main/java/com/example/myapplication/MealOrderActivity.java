package com.example.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapplication.Register.MealOrderRequest;
import com.example.myapplication.Register.RemainRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kr.co.bootpay.Bootpay;
import kr.co.bootpay.BootpayAnalytics;
import kr.co.bootpay.enums.Method;
import kr.co.bootpay.enums.PG;
import kr.co.bootpay.enums.UX;
import kr.co.bootpay.listener.CancelListener;
import kr.co.bootpay.listener.CloseListener;
import kr.co.bootpay.listener.ConfirmListener;
import kr.co.bootpay.listener.DoneListener;
import kr.co.bootpay.listener.ErrorListener;
import kr.co.bootpay.listener.ReadyListener;
import kr.co.bootpay.model.BootExtra;
import kr.co.bootpay.model.BootUser;

public class MealOrderActivity extends AppCompatActivity {  //밀키트 주문 페이지
    private TextView meal_count, meal_total_price, meal_name, meal_price_fix,
            meal_id, saving_image, remain_num;
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private EditText postNum, phoneNumber, Address;
    private ImageButton meal_minus, meal_plus;
    private int count=1;
    String member_id = "";
    Button payment, existingDeli;
    static final int SMS_SEND_PERMISSION = 1;
    static final int SMS_RECIVE_PERMISSION = 1;
    int sum = 0;
    ImageView meal_detail_img;
    private int stuck = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_order);

        BootpayAnalytics.init(this, "63562be4d01c7e002096a5e4");

        meal_count = findViewById(R.id.meal_count);
        meal_count.setText(count+"");
        meal_plus = findViewById(R.id.meal_plus);
        meal_minus = findViewById(R.id.meal_minus);
        meal_price_fix = findViewById(R.id.meal_price_fix);
        meal_total_price = findViewById(R.id.meal_total_price);
        meal_detail_img = findViewById(R.id.meal_detail_img);
        postNum = findViewById(R.id.postNum);
        phoneNumber = findViewById(R.id.phoneNumber);
        Address = findViewById(R.id.Address);
        meal_name = findViewById(R.id.meal_name);
        meal_id = findViewById(R.id.meal_id);
        saving_image = findViewById(R.id.saving_image);
        remain_num = findViewById(R.id.remain_num);

        String serverUrl = "http://admin0000.dothome.co.kr/meal_ex2.php";  //DB에 저장된 레시피 설명 불러오기
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0; i< response.length(); i++){
                        JSONObject jsonObject= response.getJSONObject(i);

                        int id = jsonObject.getInt("meal_id");
                        String title = jsonObject.getString("meal_title");
                        String price = jsonObject.getString("meal_price");
                        String remain = jsonObject.getString("remain_num");
                        String image = jsonObject.getString("meal_image_sub");
                        Glide.with(getApplicationContext()).load(image).into(meal_detail_img);

                        saving_image.setText(image);
                        meal_id.setText(id+"");
                        meal_name.setText(title);
                        remain_num.setText(remain);
                        meal_total_price.setText(price);
                        meal_price_fix.setText(price);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MealOrderActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

        existingDeli = findViewById(R.id.existingDeli);
        member_id = shared_preferences.get_user_email(MealOrderActivity.this);
        existingDeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serverUrl2 = "http://admin0000.dothome.co.kr/mem_info.php";  //회원정보 불러오기
                JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(Request.Method.POST, serverUrl2, null, new Response.Listener<JSONArray>() {
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
                //서버로 Volley를 이용해서 요청
                RequestQueue requestQueue2 = Volley.newRequestQueue(MealOrderActivity.this);
                requestQueue2.add(jsonArrayRequest2);
            }
        });

        meal_plus.setOnClickListener(new View.OnClickListener() {  //플러스 버튼 누르면 숫자 증가
            @Override
            public void onClick(View v) {
                count++;
                sum = Integer.parseInt(meal_total_price.getText().toString());
                int price = Integer.parseInt(meal_total_price.getText().toString());
                int fix_price = Integer.parseInt(meal_price_fix.getText().toString());
                meal_count.setText(count+"");
                sum += price;
                meal_total_price.setText(fix_price * count +"");
            }
        });

        meal_minus.setOnClickListener(new View.OnClickListener() {  //마이너스 버튼 누르면 숫자 감소
            @Override
            public void onClick(View v) {
                sum = Integer.parseInt(meal_total_price.getText().toString());
                int price = Integer.parseInt(meal_total_price.getText().toString());
                int fix_price = Integer.parseInt(meal_price_fix.getText().toString());
                if (count == 1) {
                    meal_total_price.setText(price+"");
                } else {
                    if (count > 1) {
                        count--;
                        meal_count.setText(count+"");
                    }
                    sum -= price;
                    meal_total_price.setText(fix_price * count+"");
                }
            }
        });

        ImageButton btn_back_index = findViewById(R.id.back_index);
        btn_back_index.setOnClickListener(new View.OnClickListener() {  //뒤로가기
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MealOrderActivity.this, Fragment_mealDetail.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        Button btn_search = findViewById(R.id.adress_detail);
        if (btn_search != null) {
            btn_search.setOnClickListener(new View.OnClickListener() {  //주소검색 API
                @Override
                public void onClick(View v)
                {
                    Intent i = new Intent(MealOrderActivity.this, AdressAPI.class);
                    startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
                }
            });
        }

        //발신권환 받기
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

        //수신권한 받기
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

        payment = findViewById(R.id.payment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MealOrderActivity.this);
                builder.setMessage("주문하시겠습니까?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String phoneNo = phoneNumber.getText().toString();
                        String add = Address.getText().toString();
                        String postNo = postNum.getText().toString();
                        String price = (String) meal_total_price.getText();
                        String name = (String) meal_name.getText();
                        String count = (String) meal_count.getText();
                        String re_num = (String) remain_num.getText();

                        int f_num = Integer.parseInt(re_num) - Integer.parseInt(count);

                        String image = (String) saving_image.getText();
                        String m_id2 = shared_preferences.get_user_email(MealOrderActivity.this);
                        String m_id1 = (String) meal_id.getText();

                        String txt = "CookKit 밀키트 구매 안내\n\n" + name + "\n" + count + "세트, "
                                + price + "원";

                        //하나라도 입력 안 됐을 때
                        if (phoneNo.equals("") || add.equals("") || postNo.equals("")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MealOrderActivity.this);
                            builder.setMessage("배송정보를 모두 입력해주세요.").setNegativeButton("확인", null).create().show();
                            return;
                        }

                        BootUser bootUser = new BootUser();  //결제시스템 연동
                        BootExtra bootExtra = new BootExtra().setQuotas(new int[] {0,2,3});

                        Bootpay.init(getFragmentManager())
                                .setApplicationId("63562be4d01c7e002096a5e4") // 해당 프로젝트(안드로이드)의 application id 값
                .setPG(PG.INICIS) // 결제할 PG 사
                                .setMethod(Method.PHONE) // 결제수단
                                .setContext(MealOrderActivity.this)
                                .setBootUser(bootUser)
                                .setBootExtra(bootExtra)
                                .setUX(UX.PG_DIALOG)
                                .setName(name) // 결제할 상품명
                                .setOrderId("1234") // 결제 고유번호expire_month
                                .setPrice(Integer.parseInt(price)) // 결제할 금액
                                .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                                    @Override
                                    public void onConfirm(@Nullable String message) {

                                        if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                                        else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                                        Log.d("confirm", message);
                                    }
                                })
                                .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                                    @Override
                                    public void onDone(@Nullable String message) {
                                        Log.d("done", message);
                                        try {
                                            SmsManager smsManager = SmsManager.getDefault();  //밀키트 구매안내 메세지 전송
                                            smsManager.sendTextMessage(phoneNo, null, txt, null, null);
                                            Toast.makeText(getApplicationContext(), "밀키트를 구매했습니다", Toast.LENGTH_LONG).show();
                                            Response.Listener<String> responseListener = new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    try {
                                                        JSONObject jsonObject = new JSONObject( response );
                                                        boolean success = jsonObject.getBoolean( "success" );
                                                        if (success) {
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
                                            MealOrderRequest mealOrderRequest = new MealOrderRequest( m_id1, m_id2, name, count, price, phoneNo, postNo, add, image, responseListener);
                                            RequestQueue queue = Volley.newRequestQueue( MealOrderActivity.this );
                                            queue.add( mealOrderRequest );

                                            Response.Listener<String> responseListener2 = new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {  //밀키트 남은 수량 변경
                                                    try {
                                                        JSONObject jsonObject2 = new JSONObject( response );
                                                        boolean success2 = jsonObject2.getBoolean( "success" );
                                                        if (success2) {
                                                            Intent intent = new Intent(MealOrderActivity.this, Fragment_mealDetail.class);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            finish();
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
                                            RemainRequest remainRequest = new RemainRequest( Integer.toString(f_num), responseListener2);
                                            RequestQueue queue2 = Volley.newRequestQueue( MealOrderActivity.this );
                                            queue2.add( remainRequest );

                                        } catch (Exception e) {
                                            Toast.makeText(getApplicationContext(), "전송 오류!", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();//오류 원인이 찍힌다.
                                            e.printStackTrace();
                                        }
                                    }
                                })
                                .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                                    @Override
                                    public void onReady(@Nullable String message) {
                                        Log.d("ready", message);
                                    }
                                })
                                .onCancel(new CancelListener() { // 결제 취소시 호출
                                    @Override
                                    public void onCancel(@Nullable String message) {
                                        Log.d("cancel", message);
                                    }
                                })
                                .onError(new ErrorListener() { // 에러가 났을때 호출되는 부분
                                    @Override
                                    public void onError(@Nullable String message) {
                                        Log.d("error", message);
                                    }
                                })
                                .onClose(
                                        new CloseListener() { //결제창이 닫힐때 실행되는 부분
                                            @Override
                                            public void onClose(String message) {
                                                Log.d("close", "close");
                                            }
                                        })
                                .request();


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MealOrderActivity.this, String.format("취소"), Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {  //주소검색 후 입력해줌
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