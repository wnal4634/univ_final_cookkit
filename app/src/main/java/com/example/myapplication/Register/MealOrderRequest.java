package com.example.myapplication.Register;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MealOrderRequest  extends StringRequest { //레시피 주문 php와 연동

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://admin0000.dothome.co.kr/meal_order.php";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public MealOrderRequest(String meal_id, String member_id, String title,
                              String count, String price, String phone,
                            String post, String add, String image, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("meal_id", meal_id);
        map.put("member_id", member_id);
        map.put("meal_title", title);
        map.put("meal_set_count", count);
        map.put("meal_price", price);
        map.put("phone_num", phone);
        map.put("post_num", post);
        map.put("member_ad", add);
        map.put("meal_image", image);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}