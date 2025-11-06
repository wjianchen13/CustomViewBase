package com.example.customviewbase.customview.indicator;

import android.graphics.Color;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

public class RoundedRectView extends View {

    private Paint paint;
    private float cornerRadius;

    public RoundedRectView(Context context) {
        super(context);
        init();
    }

    public RoundedRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        cornerRadius = 50; // 设置圆角半径
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 创建一个路径
        Path path = new Path();
        path.addRoundRect(
                new RectF(0, 0, getWidth(), getHeight()),
                new float[]{cornerRadius, cornerRadius, cornerRadius, cornerRadius, 0, 0, 0, 0},
                Path.Direction.CW
        );

        // 使用路径绘制
        canvas.drawPath(path, paint);
    }
}