package com.example.myapplication.Register;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class VoteRequest extends StringRequest {
    final static private String URL = "http://admin0000.dothome.co.kr/vote_upload.php";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public VoteRequest(String rid, String id, String title, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("recipe_id", rid);
        map.put("member_id", id);
        map.put("recipe_title", title);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
