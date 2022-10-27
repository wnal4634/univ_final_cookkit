package com.example.myapplication.Register;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RemainRequest extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://admin0000.dothome.co.kr/meal_remain_num.php";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public RemainRequest(String remain_num, String count, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("remain_num", remain_num);
        map.put("count", count);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}