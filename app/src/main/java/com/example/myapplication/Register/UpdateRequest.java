package com.example.myapplication.Register;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdateRequest extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://admin0000.dothome.co.kr/update_info.php";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public UpdateRequest(String UserPhone, String UserPost, String UserAd, String UserId, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("phone_num", UserPhone);
        map.put("post_num", UserPost);
        map.put("member_ad", UserAd);
        map.put("member_id", UserId);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
