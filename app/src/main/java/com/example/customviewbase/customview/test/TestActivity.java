package com.example.customviewbase.customview.test;

import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;

/**
 * 自己用的测试用例，用来测试一些实际情况，加深认识
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        
    }
}