package com.example.customviewbase.canvas.drawarc;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * name: ArcView1
 * desc: canvas绘制圆弧
 * author:
 * date: 2021-09-07 10:00
 * remark:
 */
public class ArcView1 extends View {

    // 1.创建一个画笔
    private Paint mPaint = new Paint();
    
    private Paint mCirclePaint = new Paint();

    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px

        mCirclePaint.setColor(Color.YELLOW);
        mCirclePaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
    }

    public ArcView1(Context context) {
        super(context);
    }

    // 3.在构造函数中初始化
    public ArcView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public ArcView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        RectF rectF = new RectF(0,0,width,height);
        // 绘制背景矩形
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF,mPaint);
        canvas.drawCircle(width / 2,height / 2, width / 2, mCirclePaint);  // 绘制一个圆心坐标在(300,300)，半径为200 的圆。
        
        // 绘制圆弧
        canvas.save();
//        canvas.translate(width / 2, height / 2);
//        canvas.rotate(90.0f);
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF,-90,30,true, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawArc(rectF,-60,30,true, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawArc(rectF,-30,30,true, mPaint);
//        canvas.restore();
//        //-------------------------------------
//        RectF rectF2 = new RectF(100,600,800,900);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF2,mPaint);
//
//        // 绘制圆弧
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF2,0,90,true,mPaint);

    }
}
