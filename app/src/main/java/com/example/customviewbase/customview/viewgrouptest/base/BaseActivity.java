package com.example.customviewbase.customview.viewgrouptest.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewgroup_test_base);
    }

}
