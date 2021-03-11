package com.example.customviewbase.canvas.drawcolor;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.customviewbase.R;

/**
 * name: ColorView
 * desc: canvas绘制颜色
 * author:
 * date: 2018-06-26 16:00
 * remark:
 */
public class ColorView extends View {

    private int type;

    public ColorView(Context context) {
        super(context);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ColorView);
        type = a.getInt(R.styleable.ColorView_type, 0);
        a.recycle();
    }

    public ColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(type == 0) {
            canvas.drawColor(Color.argb(255, 255, 0, 0));
        } else if(type == 1) {
            canvas.drawRGB(0, 255, 0);
        } else if(type == 2) {
            canvas.drawARGB(255, 255, 0, 0); // 第一个参数是透明度，0-255取值，0表示全透明，注意控件不要设置background相关的属性
        }
    }
}
