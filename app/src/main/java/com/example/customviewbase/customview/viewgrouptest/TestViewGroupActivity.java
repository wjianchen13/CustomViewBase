package com.example.customviewbase.customview.viewgrouptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;
import com.example.customviewbase.customview.viewgrouptest.base.BaseActivity;
import com.example.customviewbase.customview.viewgrouptest.test.TestActivity;
import com.example.customviewbase.customview.viewgrouptest.viewgroup.ViewGroupActivity;


public class TestViewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_group);
    }

    /**
     * ViewGroup基础
     * @param
     * @return
     */
    public void onTest1(View v) {
        Intent it = new Intent();
        it.setClass(TestViewGroupActivity.this, BaseActivity.class);
        startActivity(it);
    }

    /**
     * ViewGroup配合自定义View测试
     * @param
     * @return
     */
    public void onTest2(View v) {
        Intent it = new Intent();
        it.setClass(TestViewGroupActivity.this, ViewGroupActivity.class);
        startActivity(it);
    }

    /**
     * ViewGroup配合自定义View测试
     * @param
     * @return
     */
    public void onTest3(View v) {
        Intent it = new Intent();
        it.setClass(TestViewGroupActivity.this, TestActivity.class);
        startActivity(it);
    }
}
