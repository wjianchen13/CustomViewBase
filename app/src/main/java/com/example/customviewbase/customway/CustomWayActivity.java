package com.example.customviewbase.customway;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;

public class CustomWayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_way);
    }

    /**
     * 组合控件
     * @param
     * @return
     */
    public void onGroup(View v) {
        Intent it = new Intent();
        it.setClass(CustomWayActivity.this, GroupActivity.class);
        startActivity(it);
    }

    /**
     * 自定义View
     * @param
     * @return
     */
    public void onView(View v) {
        Intent it = new Intent();
        it.setClass(CustomWayActivity.this, ViewActivity.class);
        startActivity(it);
    }

    /**
     * 继承控件
     * @param
     * @return
     */
    public void onExtend(View v) {
        Intent it = new Intent();
        it.setClass(CustomWayActivity.this, ExtendActivity.class);
        startActivity(it);
    }
}
