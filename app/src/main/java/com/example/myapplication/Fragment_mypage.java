package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fragment_mypage extends Fragment {

    private View view;
    private Button btn_noti, btn_like, btn_recipemanage, btn_mlorderstatus;
    private Button imgbtn_edit, btn_setting, btn_logout, vote_go;
    AlertDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mypage,container,false);

        Bundle bundle = getArguments();
        String member_id = bundle.getString("member_id");
        String phone_num = bundle.getString("phone_num");
        String post_num = bundle.getString("post_num");
        String member_ad = bundle.getString("member_ad");


        vote_go = view.findViewById(R.id.vote_go);
        vote_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serverUrl7 = "http://admin0000.dothome.co.kr/vote_recipe_count.php";
                JsonArrayRequest jsonArrayRequest7 = new JsonArrayRequest(Request.Method.POST, serverUrl7, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String vote_count = jsonObject.getString("num");
                                if (vote_count.equals("0")) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    dialog = builder.setMessage("투표기간이 아닙니다.").setNegativeButton("확인", null).create();
                                    dialog.show();
                                } else {
                                    Intent intent = new Intent(getActivity(), VoteActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intent);
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
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(jsonArrayRequest7);
            }
        });

        btn_noti = (Button) view.findViewById(R.id.button19);
        btn_noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), noticeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        btn_like = (Button) view.findViewById(R.id.button15);
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LikeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        btn_recipemanage = (Button) view.findViewById(R.id.button13);
        btn_recipemanage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RecipeManageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("member_id", (String) member_id);
                startActivity(intent);
            }
        });
        btn_mlorderstatus = (Button) view.findViewById(R.id.button14);
        btn_mlorderstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MealOrderStatusActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        imgbtn_edit = (Button) view.findViewById(R.id.imageButton4);
        imgbtn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MemberInfoEditActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("member_id", (String) member_id);
                intent.putExtra("phone_num", (String) phone_num);
                intent.putExtra("post_num", (String) post_num);
                intent.putExtra("member_ad", (String) member_ad);
                startActivity(intent);
            }
        });
        btn_setting = (Button) view.findViewById(R.id.button17);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        btn_logout = (Button) view.findViewById(R.id.logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shared_preferences.clear_user(getActivity());
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;

    }

}