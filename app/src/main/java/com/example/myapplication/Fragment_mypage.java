package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Fragment_mypage extends Fragment {

    private View view;
    private Button btn_noti, btn_reco, btn_like, btn_recipemanage, btn_mlorderstatus;
    private Button imgbtn_edit, btn_setting, btn_logout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mypage,container,false);

        Bundle bundle = getArguments();
        String member_id = bundle.getString("member_id");
        String phone_num = bundle.getString("phone_num");
        String post_num = bundle.getString("post_num");
        String member_ad = bundle.getString("member_ad");



        btn_noti = (Button) view.findViewById(R.id.button19);
        btn_noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),noticeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        btn_reco = (Button) view.findViewById(R.id.button16);
        btn_reco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),RecoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        btn_like = (Button) view.findViewById(R.id.button15);
        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),LikeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        btn_recipemanage = (Button) view.findViewById(R.id.button13);
        btn_recipemanage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),RecipeManageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("member_id", (String) member_id);
                startActivity(intent);
            }
        });
        btn_mlorderstatus = (Button) view.findViewById(R.id.button14);
        btn_mlorderstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MealOrderStatusActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        imgbtn_edit = (Button) view.findViewById(R.id.imageButton4);
        imgbtn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MemberInfoEditActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                String member_id = intent.getStringExtra("member_id");
//                intent.putExtra("member_id", member_id);
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
                Intent intent = new Intent(getActivity(),SettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        btn_logout = (Button) view.findViewById(R.id.logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;



    }

}