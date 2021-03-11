package com.example.customviewbase.canvas.paint;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * name: PaintView
 * desc: 画笔设置
 * author:
 * date: 2018-07-05 16:00
 * remark:
 */
public class PaintView extends View {

    // 1.创建一个画笔
    private Paint mPaint = new Paint();

    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(40);     //为了实验效果明显，特地设置描边宽度非常大
    }

    public PaintView(Context context) {
        super(context);
    }

    // 3.在构造函数中初始化
    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 描边
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200,200,100,mPaint);

        // 填充
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200,500,100,mPaint);

        // 描边加填充
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200, 800, 100, mPaint);
    }
}
