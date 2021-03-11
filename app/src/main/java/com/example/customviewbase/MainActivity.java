package com.example.customviewbase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.customviewbase.canvas.CanvasActivity;
import com.example.customviewbase.customview.CustomViewActivity;
import com.example.customviewbase.customway.CustomWayActivity;
import com.example.customviewbase.specmode.SpecModeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCustomView(View v) {
        startActivity(new Intent(this, CustomViewActivity.class));
    }

    public void onCanvas(View v) {
        startActivity(new Intent(this, CanvasActivity.class));
    }

    public void onSpecMode(View v) {
        startActivity(new Intent(this, SpecModeActivity.class));
    }

    public void onCustomWay(View v) {
        startActivity(new Intent(this, CustomWayActivity.class));
    }
}