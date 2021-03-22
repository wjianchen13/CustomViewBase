package com.example.customviewbase.customview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;
import com.example.customviewbase.customview.custom.CustomActivity;
import com.example.customviewbase.customview.custom.DrawActivity;
import com.example.customviewbase.customview.customlayoutparams.CustomLayoutParamsActivity;
import com.example.customviewbase.customview.custommargin.CustomMarginActivity;
import com.example.customviewbase.customview.test.TestActivity;
import com.example.customviewbase.customview.viewgrouptest.TestViewGroupActivity;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }

    public void onCustom(View v) {
        startActivity(new Intent(this, CustomActivity.class));
    }

    public void onTest(View v) {
        startActivity(new Intent(this, TestActivity.class));
    }

    public void onDraw(View v) {
        startActivity(new Intent(this, DrawActivity.class));
    }

    public void onViewGroupTest(View v) {
        startActivity(new Intent(this, TestViewGroupActivity.class));
    }

    public void onCustomParams(View v) {
        startActivity(new Intent(this, CustomLayoutParamsActivity.class));
    }

    public void onMargin(View v) {
        startActivity(new Intent(this, CustomMarginActivity.class));
    }
}