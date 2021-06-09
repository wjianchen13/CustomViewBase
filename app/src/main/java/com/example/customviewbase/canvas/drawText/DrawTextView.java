package com.example.customviewbase.canvas.drawText;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * name: Draw Text
 * desc: canvas绘制文字
 * author:
 * date: 2018-06-26 16:00
 * remark:
 */
public class DrawTextView extends View {


    // 1.创建一个画笔
    private Paint mPaint = new Paint();

    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10);         //设置画笔宽度为10px
        mPaint.setTextSize(50);              // 设置字体大小
    }

    public DrawTextView(Context context) {
        super(context);
    }

    // 3.在构造函数中初始化
    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 文本(要绘制的内容)
        String str = "ABCDEFG";

        // 参数分别为 (文本 基线x 基线y 画笔)
        canvas.drawText(str, 10, 10, mPaint);

    }
}
