package com.example.customviewbase.canvas.path;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * name: PathView
 * desc: Path操作
 * author:
 * date: 2025-01-21 15:00
 * remark:
 */
public class PathView extends View {

    private Paint paint;
    private float cornerRadius;

    // 1.创建一个画笔
    private Paint mPaint = new Paint();

    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    public PathView(Context context) {
        super(context);
        init();
    }

    // 3.在构造函数中初始化
    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        init();
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
//        paint.setStrokeWidth(10);
//        paint.setStyle(Paint.Style.STROKE);
        cornerRadius = 50; // 设置圆角半径
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        // 创建一个路径
//        Path path = new Path();
//        path.addRoundRect(
//                new RectF(0, 0, getWidth(), getHeight()),
//                new float[]{cornerRadius, cornerRadius, cornerRadius, cornerRadius, 0, 0, 0, 0},
//                Path.Direction.CW
//        );
//
//        // 使用路径绘制
//        canvas.drawPath(path, paint);
        paint.setColor(Color.GREEN);
        canvas.drawRect(new Rect(50, 50, getWidth()- 0, getHeight() - 0), paint);
        canvas.save();
        // 创建一个圆角矩形的 Path，只设置左上角为圆角
        Path path = new Path();
        RectF rect = new RectF(20, 20, getWidth() - 20, getHeight() - 20);
        float[] radii = {cornerRadius, cornerRadius, 0, 0, 0, 0, 0, 0}; // 左上角为圆角，其余角为直角
        path.addRoundRect(rect, radii, Path.Direction.CW);

        // 设置 Path 为 Canvas 的剪裁区域
        canvas.clipPath(path);
//        canvas.drawPath(path, paint);
        paint.setColor(Color.BLUE);
        canvas.drawRect(new Rect(0, 0, getWidth()- 50, getHeight() - 50), paint);
        canvas.restore();
    }
}
