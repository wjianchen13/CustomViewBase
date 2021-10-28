package com.example.customviewbase.canvas.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 二阶塞尔曲线
 * https://www.cnblogs.com/wjtaigwh/p/6647114.html
 */
public class BezierView2 extends View {
    
    private float startX = 800;

    private PointF controlPoint = new PointF((startX + 500) / 2, 115);
    private PointF mStartPoint = new PointF(500, 100);
    private PointF mEndPoint = new PointF(startX, 600);

    public BezierView2(Context context) {
        super(context);
    }

    public BezierView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BezierView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);

        Path path = new Path();
        path.moveTo(mStartPoint.x, mStartPoint.y);
        // 绝对坐标 rQuadTo 相对坐标
        path.quadTo(controlPoint.x, controlPoint.y, mEndPoint.x, mEndPoint.y); 
        // 绘制路径
        canvas.drawPath(path, paint);
        // 绘制辅助点
        canvas.drawPoint(controlPoint.x,controlPoint.y,paint);
        System.out.println("=============> control x: " + controlPoint.x + " y: " + controlPoint.y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                controlPoint.x = (int) event.getX();
                controlPoint.y = (int) event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
