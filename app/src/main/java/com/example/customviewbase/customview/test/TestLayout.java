package com.example.customviewbase.customview.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import static com.example.customviewbase.dispatchevent.MyLog.log;

/**
 * 自定义布局管理器的示例。 这个用来测试getChildMeasureSpec方法
 */
public class TestLayout extends ViewGroup {
    private static final String TAG = "CustomLayout";

    public TestLayout(Context context) {
        super(context);
    }

    public TestLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 要求所有的孩子测量自己的大小，然后根据这些孩子的大小完成自己的尺寸测量
     */
    @SuppressLint("NewApi") @Override
    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec) {
//        // 计算出所有的childView的宽和高 
//        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //先度量孩子
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        println("childCount: " + childCount);

        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);  //ViewGroup解析的父亲给我的宽度
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec); // ViewGroup解析的父亲给我的高度

//        println("selfWidth: " + selfWidth + "  selfHeight: " + selfHeight);
        
        View childView = getChildAt(0);
        
        LayoutParams childLP = childView.getLayoutParams();
        int width = childLP.width;
        int height = childLP.height;
        if (childView.getVisibility() != View.GONE) {
            //将layoutParams转变成为 measureSpec
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight,
                    childLP.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom,
                    childLP.height);

            int widthMode = MeasureSpec.getMode(widthMeasureSpec);   // 获取宽的模式
            int heightMode = MeasureSpec.getMode(heightMeasureSpec); // 获取高的模式
            String wMode = widthMode == MeasureSpec.AT_MOST ? "AT_MOST" : widthMode == MeasureSpec.EXACTLY ? "EXACTLY" : "UNSPECIFIED";
            String hMode = heightMode == MeasureSpec.AT_MOST ? "AT_MOST" : heightMode == MeasureSpec.EXACTLY ? "EXACTLY" : "UNSPECIFIED";
            TestView.log("============================================================================================== ViewGroup");
//        log(getStack());
            TestView.log("宽的模式: " + wMode);
            TestView.log("高的模式: " + hMode);
//            
            
//            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(320, MeasureSpec.EXACTLY);
//            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(100, MeasureSpec.EXACTLY);
            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);

        }




        //测量并保存layout的宽高(使用getDefaultSize时，wrap_content和match_perent都是填充屏幕)
        //稍后会重新写这个方法，能达到wrap_content的效果
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    /**
     * 为所有的子控件摆放位置.
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();
        for(int i = 0; i < count; i ++) {
            View child = getChildAt(i);
//            left = 10;
            right = left + child.getMeasuredWidth();
//            top = 10;
            bottom = top + child.getMeasuredHeight();
            child.layout(left, top, right, bottom);
        }
    }
    
    public static void println(String str) {
        System.out.println("========================> " + str);
    }
}
