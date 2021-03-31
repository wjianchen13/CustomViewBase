package com.example.customviewbase.demo.stroke;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;

/**
 * TextView描边
 */
public class StrokeActivity extends AppCompatActivity {
    
    private StrokeTextView tvTest1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroke);
        tvTest1 = findViewById(R.id.tv_test);
    }
    
    public void onTest1(View v) {
        tvTest1.setText("123");
    }

    public void onTest2(View v) {
        tvTest1.setText("你好");
    }

    public void onTest3(View v) {
        tvTest1.setText("00:23:69");
    }



}