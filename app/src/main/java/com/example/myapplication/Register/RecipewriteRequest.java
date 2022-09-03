package com.example.myapplication.Register;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RecipewriteRequest extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://admin0000.dothome.co.kr/upload.php";
    private Map<String, String> map;
    //private Map<String, String>parameters;

    public RecipewriteRequest(String id, String title, String mat,
                              String cate, String text1, String text2,
                              String text3, String text4, String text5,
                              String text6, String image_main,
                              String image1, String image2, String image3,
                              String image4, String image5, String image6,
                              Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("member_id", id);
        map.put("recipe_title", title);
        map.put("recipe_material", mat);
        map.put("recipe_category", cate);
        map.put("recipe_text1", text1);
        map.put("recipe_text2", text2);
        map.put("recipe_text3", text3);
        map.put("recipe_text4", text4);
        map.put("recipe_text5", text5);
        map.put("recipe_text6", text6);
        map.put("image_main", image_main);
        map.put("image1", image1);
        map.put("image2", image2);
        map.put("image3", image3);
        map.put("image4", image4);
        map.put("image5", image5);
        map.put("image6", image6);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}