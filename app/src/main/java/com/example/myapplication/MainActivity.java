package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    final static int FRAGMENT_FIRST = 1001;
    final static int FRAGMENT_SECOND = 1002;
    final static int FRAGMENT_THIRD = 1003;
    final static int FRAGMENT_FOURTH = 1004;

    public static Fragment_index fragment_index;
    public static Fragment_search fragment_search;
    public static Fragment_mypage fragment_mypage;
    public static Fragment_mealDetail fragment_mealDetail;
    public static FragmentManager manager;

    public static Stack<Fragment> fragmentStack;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment_index = new Fragment_index();
        fragment_search = new Fragment_search();
        fragment_mealDetail = new Fragment_mealDetail();
        fragment_mypage = new Fragment_mypage();

        fragmentStack = new Stack<>();
        fragmentStack.push(fragment_index);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container, fragment_index).commit();
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
                    case R.id.item4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_mealDetail).commit();
                        fab.hide();
                        return true;
                }
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RecipewriteActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

    }
    public static void changeFragment(int index) {
        switch (index) {
            case FRAGMENT_FIRST:
                manager.beginTransaction().replace(R.id.container, fragment_index).commit();
                break;
            case FRAGMENT_SECOND:
                manager.beginTransaction().replace(R.id.container, fragment_search).commit();
                break;
            case FRAGMENT_THIRD:
                manager.beginTransaction().add(R.id.container, fragment_mypage).commit();
                break;
            case FRAGMENT_FOURTH:
                manager.beginTransaction().add(R.id.container, fragment_mealDetail).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (!fragmentStack.isEmpty()) {
            Fragment nextFragment = fragmentStack.pop();
            manager.beginTransaction().replace(R.id.container, nextFragment).commit();
            System.out.println("[TESTING >>] " + fragmentStack.size());
        } else {
            super.onBackPressed();
        }
    }}