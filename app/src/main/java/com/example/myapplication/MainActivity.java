package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    Fragment_index fragment_index;
    Fragment_search fragment_search;
    Fragment_mypage fragment_mypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment_index = new Fragment_index();
        fragment_search = new Fragment_search();
        fragment_mypage = new Fragment_mypage();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_index).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_index).commit();
                        return true;
                    case R.id.item2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_search).commit();
                        return true;
                    case R.id.item3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_mypage).commit();
                        return true;

                }
                return false;
            }
        });
    }
}