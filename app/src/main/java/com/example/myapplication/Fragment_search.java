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

public class Fragment_search extends Fragment {

    View view;
    EditText search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        ImageButton search_btn = (ImageButton) view.findViewById(R.id.search_btn);
        Button korean = view.findViewById(R.id.korean);
        Button chinese = view.findViewById(R.id.chinese);
        Button japanese = view.findViewById(R.id.japanese);
        Button western = view.findViewById(R.id.western);
        Button school = view.findViewById(R.id.school);
        Button asian = view.findViewById(R.id.asian);
        Button snack = view.findViewById(R.id.snack);
        search = view.findViewById(R.id.search);


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = search.getText().toString();
                intent.putExtra("result", (String) result);
                startActivity(intent);
            }
        });

        korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = korean.getText().toString();
                intent.putExtra("cate", (String) result);
                startActivity(intent);
            }
        });

        chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = chinese.getText().toString();
                intent.putExtra("cate", (String) result);
                startActivity(intent);
            }
        });

        japanese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = japanese.getText().toString();
                intent.putExtra("cate", (String) result);
                startActivity(intent);
            }
        });

        western.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = western.getText().toString();
                intent.putExtra("cate", (String) result);
                startActivity(intent);
            }
        });

        school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = school.getText().toString();
                intent.putExtra("cate", (String) result);
                startActivity(intent);
            }
        });

        asian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = asian.getText().toString();
                intent.putExtra("cate", (String) result);
                startActivity(intent);
            }
        });

        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                String result = snack.getText().toString();
                intent.putExtra("cate", (String) result);
                startActivity(intent);
            }
        });
        return view;
    }
}