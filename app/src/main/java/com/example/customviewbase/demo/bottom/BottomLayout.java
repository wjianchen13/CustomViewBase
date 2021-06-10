package com.example.customviewbase.demo.bottom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * ScrollView嵌套RelativeLayout，保证RelativeLayout的部分控件贴着底部
 * 但是屏幕太短的手机，贴着底部的那部分控件不能和上部的控件重叠
 */
public class BottomLayout extends RelativeLayout {

    public BottomLayout(Context context) {
        super(context);
    }

    public BottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * ScrollView 传入的heightMeasureSpec mode = MeasureSpec.UNSPECIFIED
     * 使用MeasureSpec.UNSPECIFIED会使嵌套的RelativeLayout高度变小，应该和RelativeLayout和控件相关的measure规则有关系
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mheight = getSuggestedMinimumHeight(); // 获取xml设置的minHeight
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        String str = "";
        if(heightMode == MeasureSpec.EXACTLY) {
            str = "MeasureSpec.EXACTLY";
        } else if(heightMode == MeasureSpec.AT_MOST) {
            str = "MeasureSpec.AT_MOST)";
        } else {
            str = "MeasureSpec.UNSPECIFIED";
        }
        mheight = 2042;
        int heighSpec = MeasureSpec.makeMeasureSpec(mheight, MeasureSpec.EXACTLY); // mode = MeasureSpec.EXACTLY ScrollView 传入的是MeasureSpec.UNSPECIFIED
        
//        1902
//        2042
        int minHeight = getMinimumHeight();
        super.onMeasure(widthMeasureSpec, heighSpec);
        
    }
}
