package com.example.customviewbase.demo.changeview;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 点击显示View
 */
public class ChangeViewActivity extends AppCompatActivity{
    
    private ChangeView cv;
    
    private List<String> mList = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_view);
        cv = findViewById(R.id.cv);
    }
    
    public void onTest(View v) {
        cv.anim(0.5f);
    }
    
    private void changeView(float x, float y) {
        
    }
}




































