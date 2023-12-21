package com.example.customviewbase.demo.last;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;

/**
 * 垂直居中裁剪TextView显示
 */
public class LastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
    }

    public void onList(View v) {
        startActivity(new Intent(this, LastListActivity.class));
    }

    public void onList2(View v) {
        startActivity(new Intent(this, LastListActivity2.class));
    }
}
