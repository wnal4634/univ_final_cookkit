package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class Fragment_search extends Fragment {  //검색페이지 프래그먼트
    View view;
    EditText search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        ImageButton search_btn = view.findViewById(R.id.search_btn);
        Button korean = view.findViewById(R.id.korean);
        Button chinese = view.findViewById(R.id.chinese);
        Button japanese = view.findViewById(R.id.japanese);
        Button western = view.findViewById(R.id.western);
        Button school = view.findViewById(R.id.school);
        Button asian = view.findViewById(R.id.asian);
        Button snack = view.findViewById(R.id.snack);
        search = view.findViewById(R.id.search);

        search_btn.setOnClickListener(new View.OnClickListener() {  //검색 이미지 누르면 값 넘기며 검색 결과로 이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = search.getText().toString();
                intent.putExtra("result", result);
                startActivity(intent);
            }
        });

        //카테고리 누르면 카테고리값을 넘기며 해당 카테고리인 레시피만 결과로 받음
        korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = korean.getText().toString();
                intent.putExtra("cate", result);
                startActivity(intent);
            }
        });
        chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = chinese.getText().toString();
                intent.putExtra("cate", result);
                startActivity(intent);
            }
        });
        japanese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = japanese.getText().toString();
                intent.putExtra("cate", result);
                startActivity(intent);
            }
        });
        western.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = western.getText().toString();
                intent.putExtra("cate", result);
                startActivity(intent);
            }
        });
        school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = school.getText().toString();
                intent.putExtra("cate", result);
                startActivity(intent);
            }
        });
        asian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = asian.getText().toString();
                intent.putExtra("cate", result);
                startActivity(intent);
            }
        });
        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = snack.getText().toString();
                intent.putExtra("cate", result);
                startActivity(intent);
            }
        });
        return view;
    }
}