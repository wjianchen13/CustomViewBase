package com.example.customviewbase.customview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;
import com.example.customviewbase.customview.customviewgroup.CustomActivity;
import com.example.customviewbase.customview.customviewgroup.DrawActivity;
import com.example.customviewbase.customview.customlayoutparams.CustomLayoutParamsActivity;
import com.example.customviewbase.customview.custommargin.CustomMarginActivity;
import com.example.customviewbase.customview.flowlayout.FlowLayoutActivity;
import com.example.customviewbase.customview.indicator.IndicatorActivity;
import com.example.customviewbase.customview.marquee.MarqueeActivity;
import com.example.customviewbase.customview.test.TestActivity;
import com.example.customviewbase.customview.test1.TestActivity1;
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

    public void onFlowLayout(View v) {
        startActivity(new Intent(this, FlowLayoutActivity.class));
    }

    public void onMeasure(View v) {
        startActivity(new Intent(this, TestActivity1.class));
    }

    /**
     * 自定义Indicator
     * @param v
     */
    public void onIndicator(View v) {
        startActivity(new Intent(this, IndicatorActivity.class));
    }

    /**
     * 自定义跑马灯
     * @param v
     */
    public void onTest1(View v) {
        startActivity(new Intent(this, MarqueeActivity.class));
    }


}