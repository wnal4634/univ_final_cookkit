package com.example.myapplication.consent;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.myapplication.R;

public class Consent1Activity extends Activity {  //동의약관 1번째(타이틀 없이)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_consent1);
    }
}