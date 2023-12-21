package com.example.customviewbase.demo.last;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customviewbase.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LastListActivity2 extends AppCompatActivity {

    private List<String> datas = new ArrayList<>();
    private LastListAdapter2 adapter;
    private List<String> datas1 = new ArrayList<>();

    private LinearLayoutManager layoutManager;

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_list2);
        setData();
        initVertical();
    }

    private String s10;

    private void setData() {
        String s1 = "add1";
        datas1.add(s1);
        String s2 = "add2";
        datas1.add(s2);
        String s3 = "add3";
        datas1.add(s3);
        String s4 = "add4";
        datas1.add(s4);
        String s5 = "add5";
        datas1.add(s5);
        String s6 = "add6";
        datas1.add(s6);
        String s7 = "add7";
        datas1.add(s7);
        String s8 = "add8";
        datas1.add(s8);
        String s9 = "add9";
        datas1.add(s9);
        String s10 = "add10";
        datas1.add(s10);
    }

    private RecyclerView recyclerView;

    public void initVertical(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_vertical);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        String s1 = "helloasfkdaskfdkjaskdfjkasdfkjaskdadfasdfasdfadsfasdfadsfasdasdfasdfasdfasdfasdfasdfasdfasdfasdffasdhello";
        datas.add(s1);
        String s2 = "helloasfkdaskfdkjaskdfjkasdfkjaskdadfasdfasdfadsfasdfadsfasdasdfasdfasdfasdfasdfasdfasdfasdfashello";
        datas.add(s2);
        String s3 = "helloasfkdaskfdkjaskdfjkasdfkjaskdadfasdfasdfadsfasdfadsfasdasdfasdfasdfasdfa";
        datas.add(s3);
        String s4 = "helloasfkdaskfdkjaskdfjkasdfkjask";
        datas.add(s4);
        String s5 = "helloasfkdaskfdkjaskdfjkasdfkjaskdadfasdfasdfadsfasdfado";
        datas.add(s5);
        String s6 = "helloasfkdaskfdkjaskdfjkasdfkjaskdadfasdfasdfadsfasdfadsfasdasdfasdfasdfasdfasdfasdfas";
        datas.add(s6);
        String s7 = "helloa";
        datas.add(s7);
        String s8 = "helloasfkdaskfdkjas";
        datas.add(s8);
        String s9 = "helloasfkdaskfdkjaskdfjkasdfkjaskdadfasdfasdfadsfasdfadsfasdasdfasdfasdo";
        datas.add(s9);
        String s10 = "helloasfkdaskfdkjaskdfjkasdfkjaskdadfasdfasdfa";
        datas.add(s10);
//        String s3 = "test3";
//        datas.add(s3);
//
//        String s4 = "test4";
//        datas.add(s4);
//
//        String s5 = "test5";
//        datas.add(s5);
//
//        String s6 = "test6";
//        datas.add(s6);
//        String s7 = "test7";
//        datas.add(s7);
//        String s8 = "test8";
//        datas.add(s8);
//        String s9 = "test9";
//        datas.add(s9);
//        s10 = "test10";
//        datas.add(s10);
//        s10 = "test10";
//        datas.add(s10);
//        s10 = "test10";
//        datas.add(s10);
//        s10 = "test10";
//        datas.add(s10);
//        s10 = "test10";
//        datas.add(s10);

        // 创建Adapter，并指定数据集
        adapter = new LastListAdapter2(datas);
        // 设置Adapter
        recyclerView.setAdapter(adapter);
    }
}
