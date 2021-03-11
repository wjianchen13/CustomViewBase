package com.example.customviewbase.customway;

import android.os.Bundle;

import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;

import java.util.ArrayList;
import java.util.List;

/**
 * name: ExtendActivity
 * desc:继承控件
 * author:
 * date: 2018-06-25 11:00
 * remark:
 */
public class ExtendActivity extends AppCompatActivity {

    // 自定义Lv
    private CustomListView mCustomLv;
    // 自定义适配器
    private CustomListViewAdapter mAdapter;
    // 内容列表
    private List<String> contentList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_extend);

        initContentList();

        mCustomLv = (CustomListView) findViewById(R.id.custom_lv);
        mCustomLv.setOnDeleteListener(new CustomListView.OnDeleteListener() {

            @Override
            public void onDelete(int index) {
                contentList.remove(index);
                mAdapter.notifyDataSetChanged();
            }
        });

        mAdapter = new CustomListViewAdapter(this, 0, contentList);
        mCustomLv.setAdapter(mAdapter);
    }


    // 初始化内容列表
    private void initContentList() {
        for (int i = 0; i < 20; i++) {
            contentList.add("内容项" + i);
        }
    }

    @Override
    public void onBackPressed() {
        if (mCustomLv.isDeleteShown()) {
            mCustomLv.hideDelete();
            return;
        }
        super.onBackPressed();
    }

}
