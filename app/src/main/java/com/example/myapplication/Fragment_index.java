package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class Fragment_index extends Fragment {
    private View view;
//    private Button btn_mlcheck;
//    private ImageButton imgbtn_mealgo;
    private RecyclerView recyclerView;
    private ArrayList<MainData> list = new ArrayList<>();
    private MainAdapter mAdapter;
    private Spinner indexSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_index, container, false);

        String [] values1 = {"추천순", "최신순"};
        indexSpinner = (Spinner) view.findViewById(R.id.reco);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        indexSpinner.setPrompt("추천순");
        indexSpinner.setAdapter(adapterSpinner);
        adapterSpinner.addAll(values1);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        mAdapter = new MainAdapter (list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
//        btn_mlcheck = (Button) view.findViewById(R.id.mlcheck);
//        btn_mlcheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),MealDetailActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);
//            }
//        });

//        imgbtn_mealgo = (ImageButton) view.findViewById(R.id.mealgo);
//        imgbtn_mealgo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),MealDetailActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);
//            }
//        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Data();
    }
    private void Data(){
        for(int i=0;i<10;i++) {
            list.add(new MainData("돈가스", "양식", R.drawable.porkcutlet));
        }
    }
}