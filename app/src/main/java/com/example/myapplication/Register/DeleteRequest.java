package com.example.myapplication.Register;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteRequest  extends StringRequest {  //레시피 삭제 php와 연동

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://admin0000.dothome.co.kr/delete_recipe_my.php";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public DeleteRequest(String r_id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("recipe_id", r_id);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}