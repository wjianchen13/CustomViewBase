package com.example.customviewbase.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;
import com.example.customviewbase.customview.customlayoutparams.CustomLayoutParamsActivity;
import com.example.customviewbase.customview.custommargin.CustomMarginActivity;
import com.example.customviewbase.customview.customviewgroup.CustomActivity;
import com.example.customviewbase.customview.customviewgroup.DrawActivity;
import com.example.customviewbase.customview.test.TestActivity;
import com.example.customviewbase.customview.viewgrouptest.TestViewGroupActivity;
import com.example.customviewbase.demo.pan.PanActivity;
import com.example.customviewbase.demo.stroke.StrokeActivity;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    public void onPan(View v) {
        startActivity(new Intent(this, PanActivity.class));
    }

    public void onCustomParams(View v) {
        startActivity(new Intent(this, StrokeActivity.class));
    }

    public void onTest(View v) {
        startActivity(new Intent(this, StrokeActivity.class));
    }

    public void onDraw(View v) {
        startActivity(new Intent(this, DrawActivity.class));
    }

    public void onViewGroupTest(View v) {
        startActivity(new Intent(this, TestViewGroupActivity.class));
    }


    public void onMargin(View v) {
        startActivity(new Intent(this, CustomMarginActivity.class));
    }
}