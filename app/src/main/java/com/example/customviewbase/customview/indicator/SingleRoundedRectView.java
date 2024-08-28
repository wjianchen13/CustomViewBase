package com.example.customviewbase.customview.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class SingleRoundedRectView extends View {

    private Paint paint;
    private Path path;

    public SingleRoundedRectView(Context context) {
        super(context);
        init();
    }

    public SingleRoundedRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SingleRoundedRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(android.R.color.holo_blue_light));

        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int radius = 50;

        path.moveTo(radius, 0);
        path.lineTo(width, 0);
        path.lineTo(width, height);
        path.lineTo(0, height);
        path.lineTo(0, radius);

        RectF rect = new RectF(0, 0, 2 * radius, 2 * radius);
        path.arcTo(rect, 90, 90);

        canvas.drawPath(path, paint);
    }
}