package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment_mypage extends Fragment{
    private Button button15;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState){
        final ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_mypage,container,false);

        Button button15=rootView.findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getActivity(),LikeActivity.class);
                startActivity(intent);

            }
        });
        Button button16=rootView.findViewById(R.id.button16);
        button16.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getActivity(),Recoactivity.class);
                startActivity(intent);

            }
        });
        return rootView;
    }
}
