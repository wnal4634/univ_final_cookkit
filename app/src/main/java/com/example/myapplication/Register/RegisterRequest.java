package com.example.myapplication.Register;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://admin0000.dothome.co.kr/Register.php";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public RegisterRequest(String UserId, String UserPwd, String UserName, String UserEmail, String UserPhone,
                           String UserPost, String UserAd, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("member_id", UserId);
        map.put("member_pw", UserPwd);
        map.put("name", UserName);
        map.put("email", UserEmail);
        map.put("phone_num", UserPhone);
        map.put("post_num", UserPost);
        map.put("member_ad", UserAd);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}