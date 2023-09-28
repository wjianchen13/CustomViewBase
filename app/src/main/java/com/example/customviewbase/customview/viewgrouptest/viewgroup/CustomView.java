package com.example.customviewbase.customview.viewgrouptest.viewgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2018/9/29.
 * a.当控件的layout_width或layout_height指定为wrap_content时，为AT_MOST
 b.当控件的layout_width或layout_height指定为match_parent或具体数值时，为EXACTLY
 */

public class CustomView extends View {



    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    // 1.创建一个画笔
    private Paint mPaint = new Paint();

    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(20);         //设置画笔宽度为10px
        mPaint.setTextSize(100);              // 设置字体大小
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("TAG", "====================================================================================> ");
        onMeasure1(widthMeasureSpec, heightMeasureSpec);
        printMode(widthMeasureSpec, heightMeasureSpec);
    }

    private void onMeasure1(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("TAG", "===========================> getSuggestedMinimumWidth() = " + getSuggestedMinimumWidth() + " , getSuggestedMinimumHeight() = " + getSuggestedMinimumHeight());
        Log.e("TAG", "===========================> default width: " + getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec) + " , default width: " + getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
//        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
//                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
        //宽度
        int width = 0;
        int height = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = sizeWidth;
                break;
            case MeasureSpec.AT_MOST:
                width = 450;
                break;
            case MeasureSpec.UNSPECIFIED:
                width = 100000;
                break;
        }
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = sizeHeight;
                break;
            case MeasureSpec.AT_MOST:
                height = 200;
                break;
            case MeasureSpec.UNSPECIFIED:
                height = 2000000;
                break;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 文本(要绘制的内容)
        String str = "ABCDEFG";

        // 参数分别为 (文本 基线x 基线y 画笔)
        canvas.drawText(str, 0, 80, mPaint);

    }

    private void printMode(int widthMeasureSpec, int heightMeasureSpec) {
        //宽度
        String specMode_width = "";
        int specModeWidth = MeasureSpec.getMode(widthMeasureSpec);
        switch (specModeWidth) {
            case MeasureSpec.EXACTLY:
                specMode_width = "EXACTLY";
                break;
            case MeasureSpec.AT_MOST:
                specMode_width = "AT_MOST";
                break;
            case MeasureSpec.UNSPECIFIED:
                specMode_width = "UNSPECIFIED";
                break;
        }
        //高度度
        String specMode_height = "";
        int specModeHeight = MeasureSpec.getMode(heightMeasureSpec);
        switch (specModeHeight) {
            case MeasureSpec.UNSPECIFIED:
                specMode_height = "UNSPECIFIED";
                break;
            case MeasureSpec.AT_MOST:
                specMode_height = "AT_MOST";
                break;
            case MeasureSpec.EXACTLY:
                specMode_height = "EXACTLY";
                break;
        }
//        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        Log.e("TAG", "===========================> specMode_width = " + specMode_width + " , specMode_height = " + specMode_height);
        Log.e("TAG", "===========================> specSize_width = " + MeasureSpec.getSize(widthMeasureSpec) + ", specSize_height = " + MeasureSpec.getSize(heightMeasureSpec));
    }

}
