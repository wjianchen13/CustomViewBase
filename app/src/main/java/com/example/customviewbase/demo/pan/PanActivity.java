package com.example.customviewbase.demo.pan;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;

import java.util.ArrayList;
import java.util.List;

/**
 * public View inflate (int resource, ViewGroup root, boolean attachToRoot)
 * 1、如果root不为null,且attachToRoot为TRUE，则会在加载的布局文件的最外层再嵌套一层root布局，这时候xml根元素的布局参数当然会起作用。
 * 2、如果root不为null,且attachToRoot为false，则不会在加载的布局文件的最外层再嵌套一层root布局，这个root只会用于为要加载的xml的根view生成布局参数
 * （ 官方原话：If false, root is only used to create the correct subclass of LayoutParams for the root view in the XML.），
 * 这时候xml根元素的布局参数也会起作用了！！！
 * 3、如果root为null,则attachToRoot无论为true还是false都没意义！即xml根元素的布局参数依然不会起作用！
 * 
 * 问题的原因是这样的：任何使用 addView(...) 的方法，无论你所实例化的 View 本身的 xml 的 width 和 height 设置了什么，都是没效果的，
 * 请看清楚，是 width height 失效，上面的 scaleType 是有效的， 问题 java 代码中调用 addView 的时候并没有传入 LayoutParam 布局参数
 * 
 * 
 */
public class PanActivity extends AppCompatActivity {
    
    private PanParamsLayout plTest;
    private PanAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pan);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        plTest = findViewById(R.id.pl_test);
    }
    
    public void onTest(View v) {
        final LayoutInflater inflater = LayoutInflater.from(this);
        
        // 布局参数有效
//        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.item_pan, plTest, false);
//        plTest.addView(layout);
        
//        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.item_pan, null);
//        PanLayoutParams lp = new PanLayoutParams(dip2px(60),dip2px(150));
//        lp.position = PanLayoutParams.POSITION_CENTERHORIZONTAL;
//        layout.setLayoutParams(lp); 
//        plTest.addView(layout);

        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test1", ""));
        data.add(new PanItem("test2", ""));
        data.add(new PanItem("test3", ""));
        data.add(new PanItem("test4", ""));
        data.add(new PanItem("test5", ""));
        data.add(new PanItem("test6", ""));
        data.add(new PanItem("test7", ""));
        data.add(new PanItem("test8", ""));
        data.add(new PanItem("test9", ""));
        plTest.setAdapter(mAdapter = new PanAdapter(this, data));
    }

    public void onAdd2(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test21", ""));
        data.add(new PanItem("test22", ""));
        plTest.setAdapter(mAdapter = new PanAdapter(this, data));
    }
    
    public void onAdd3(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test31", ""));
        data.add(new PanItem("test32", ""));
        data.add(new PanItem("test33", ""));
        plTest.setAdapter(mAdapter = new PanAdapter(this, data));
    }

    public void onAdd4(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test41", ""));
        data.add(new PanItem("test42", ""));
        data.add(new PanItem("test43", ""));
        data.add(new PanItem("test44", ""));
        plTest.setAdapter(mAdapter = new PanAdapter(this, data));
    }

    public void onAdd5(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test51", ""));
        data.add(new PanItem("test52", ""));
        data.add(new PanItem("test53", ""));
        data.add(new PanItem("test54", ""));
        data.add(new PanItem("test55", ""));
        plTest.setAdapter(mAdapter = new PanAdapter(this, data));
    }

    public void onAdd6(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test61", ""));
        data.add(new PanItem("test62", ""));
        data.add(new PanItem("test63", ""));
        data.add(new PanItem("test64", ""));
        data.add(new PanItem("test65", ""));
        data.add(new PanItem("test66", ""));
        plTest.setAdapter(mAdapter = new PanAdapter(this, data));
    }

    public void onAdd7(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test71", ""));
        data.add(new PanItem("test72", ""));
        data.add(new PanItem("test73", ""));
        data.add(new PanItem("test74", ""));
        data.add(new PanItem("test75", ""));
        data.add(new PanItem("test76", ""));
        data.add(new PanItem("test77", ""));
        plTest.setAdapter(mAdapter = new PanAdapter(this, data));
    }

    public void onAdd8(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test81", ""));
        data.add(new PanItem("test82", ""));
        data.add(new PanItem("test83", ""));
        data.add(new PanItem("test84", ""));
        data.add(new PanItem("test85", ""));
        data.add(new PanItem("test86", ""));
        data.add(new PanItem("test87", ""));
        data.add(new PanItem("test88", ""));
        plTest.setAdapter(mAdapter = new PanAdapter(this, data));
    }

    public void onAdd9(View v) {
        List<PanItem> data = new ArrayList<>();
        data.add(new PanItem("test91", ""));
        data.add(new PanItem("test92", ""));
        data.add(new PanItem("test93", ""));
        data.add(new PanItem("test94", ""));
        data.add(new PanItem("test95", ""));
        data.add(new PanItem("test96", ""));
        data.add(new PanItem("test97", ""));
        data.add(new PanItem("test98", ""));
        data.add(new PanItem("test99", ""));
        plTest.setAdapter(mAdapter = new PanAdapter(this, data));
    }

    /**
     * 启动旋转
     * @param v
     */
    public void onStart(View v) {
        plTest.setPivotX(plTest.getWidth() / 2);
        plTest.setPivotY(plTest.getWidth() / 2);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                plTest, "rotation", 0, 720);
        objectAnimator.setDuration(3000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
    }
    
    /**
     * 启动旋转
     * @param v
     */
    public void onSet(View v) {
        mAdapter.set();
    }
    

    public int dip2px(float dpValue) {
        return dip2px(this,dpValue);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        return (int) (dpValue * getDensity(context) + 0.5f);
    }

    /**
     * 返回屏幕密度
     */
    public float getDensity(Context context) {
        try {
            return context.getResources().getDisplayMetrics().density;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2.0f;
    }
    
    
}