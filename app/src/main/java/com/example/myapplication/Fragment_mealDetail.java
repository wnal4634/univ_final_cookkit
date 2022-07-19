package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class Fragment_mealDetail extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_meal_detail, container, false);

//        Bundle bundle = getArguments();
//        String member_id = bundle.getString("member_id");
//        String phone_num = bundle.getString("phone_num");
//        String post_num = bundle.getString("post_num");
//        String member_ad = bundle.getString("member_ad");

        Button btn_ordergo = (Button) view.findViewById(R.id.ordergo);
        btn_ordergo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MealOrderActivity.class);
//                intent.putExtra("member_id", (String) member_id);
//                intent.putExtra("phone_num", (String) phone_num);
//                intent.putExtra("post_num", (String) post_num);
//                intent.putExtra("member_ad", (String) member_ad);
                startActivity(intent);
            }
        });

//        ImageButton btn_back_index = (ImageButton) view.findViewById(R.id.back_index);
//        btn_back_index.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

}