package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    Fragment_index fragment_index;
    Fragment_search fragment_search;
    Fragment_mypage fragment_mypage;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment_index = new Fragment_index();
        fragment_search = new Fragment_search();
        fragment_mypage = new Fragment_mypage();
        fab = (FloatingActionButton)findViewById(R.id.fab);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_index).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_index).commit();
                        fab.show();
                        return true;
                    case R.id.item2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_search).commit();
                        fab.hide();
                        return true;
                    case R.id.item3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_mypage).commit();
                        fab.hide();
                        return true;
                }
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}