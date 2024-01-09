package com.example.customviewbase.demo.move;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;
import com.example.customviewbase.demo.changeview.FocusView;

import java.util.ArrayList;
import java.util.List;

/**
 * 点击显示View
 */
public class MoveActivity extends AppCompatActivity{

    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        tvTest = findViewById(R.id.tv_test);
    }
    
    public void onTest(View v) {
//        tvTest.offsetLeftAndRight(100);
        tvTest.setTranslationX(300);
    }

    public void onTest1(View v) {
//        tvTest.offsetLeftAndRight(100);
        tvTest.setTranslationX(300);
    }

    public void onTest2(View v) {
//        tvTest.offsetLeftAndRight(100);
        tvTest.setTranslationX(0);
    }

}




































