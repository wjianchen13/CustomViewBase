package com.example.customviewbase.canvas.drawText;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.customviewbase.R;

public class DrawTextActivity extends AppCompatActivity {

    private DrawTextView1 dtvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text1);
        dtvTest = (DrawTextView1)findViewById(R.id.dtv_test);
    }

    public void onTest1(View v) {
        dtvTest.setContent("1123", ContextCompat.getColor(this, R.color.colorPrimary), ContextCompat.getColor(this, R.color.c_ll_ff00bfcb));

    }


}
