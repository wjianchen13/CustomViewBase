package com.example.customviewbase.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;
import com.example.customviewbase.demo.bottom.BottomActivity;
import com.example.customviewbase.demo.center.CenterActivity;
import com.example.customviewbase.demo.changeview.ChangeViewActivity;
import com.example.customviewbase.demo.pan.PanActivity;
import com.example.customviewbase.demo.poker.PokerActivity;
import com.example.customviewbase.demo.pp.PpActivity;
import com.example.customviewbase.demo.stroke.StrokeActivity;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    public void onPoker1(View v) {
        startActivity(new Intent(this, PokerActivity.class));
    }

    public void onPoker(View v) {
        startActivity(new Intent(this, PpActivity.class));
    }

    public void onPan(View v) {
        startActivity(new Intent(this, PanActivity.class));
    }

    public void onCustomParams(View v) {
        startActivity(new Intent(this, StrokeActivity.class));
    }

    public void onBottom(View v) {
        startActivity(new Intent(this, BottomActivity.class));
    }

    public void onDraw(View v) {
        startActivity(new Intent(this, ChangeViewActivity.class));
    }

    public void onViewGroupTest(View v) {

    }

    public void onMargin(View v) {

    }

    /**
     * 垂直居中裁剪TextView显示
     */
    public void onCenter(View v) {
        startActivity(new Intent(this, CenterActivity.class));
    }

}