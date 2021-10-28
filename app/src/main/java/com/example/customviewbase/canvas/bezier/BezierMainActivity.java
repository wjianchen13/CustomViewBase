package com.example.customviewbase.canvas.bezier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;

public class BezierMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_main);
    }

    /**
     * 贝塞尔曲线
     * @param
     * @return
     */
    public void onBezier1(View v) {
        Intent it = new Intent();
        it.setClass(BezierMainActivity.this, BezierActivity1.class);
        startActivity(it);
    }

    /**
     * 贝塞尔曲线
     * @param
     * @return
     */
    public void onBezier2(View v) {
        Intent it = new Intent();
        it.setClass(BezierMainActivity.this, BezierActivity2.class);
        startActivity(it);
    }

    /**
     * 贝塞尔曲线
     * @param
     * @return
     */
    public void onBezier3(View v) {
        Intent it = new Intent();
        it.setClass(BezierMainActivity.this, BezierActivity3.class);
        startActivity(it);
    }
}
