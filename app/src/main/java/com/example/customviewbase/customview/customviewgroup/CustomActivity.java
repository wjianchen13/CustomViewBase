package com.example.customviewbase.customview.customviewgroup;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;

import java.text.SimpleDateFormat;

public class CustomActivity extends AppCompatActivity {

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
    }
    
}