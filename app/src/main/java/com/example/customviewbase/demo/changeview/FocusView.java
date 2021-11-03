package com.example.customviewbase.demo.changeview;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * name: LineView
 * desc: canvas绘制直线
 * author:
 * date: 2018-06-26 16:00
 * remark:
 */
public class FocusView extends View {
    
    private Paint mPaint;
    
    private ValueAnimator mAnimator;

    /**
     * 宽
     */
    private int mWidth;

    /**
     * 高
     */
    private int mHeight;

    /**
     * 变化因子
     */
    private float mRatio;

    /**
     * 空白距离
     */
    private int wLength;

    /**
     * 空白距离
     */
    private int hLength;

    /**
     * 中间加号长度控制
     * 控件尺寸 / mCenterNum * 2
     */
    private int mCenterNum = 8;

    /**
     * 变长尺寸控制
     * 控件尺寸 / mNum
     */
    private int mNum = 4;
    
    private int padding = 2;

    public FocusView(Context context) {
        super(context);
        initPaint();
    }

    public FocusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        this.mRatio = 0;
    }

    public FocusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        System.out.println("====================> w: " + w + "  h: " + h + "  oldw: " + oldw + "   oldh: " + oldh);
        this.mWidth = w;
        this.mHeight = h;
        this.wLength = w / 2;
        this.hLength = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int lWidth = (mWidth - wLength) / mNum;
        int lHeight = (mHeight - hLength) / mNum;
        
        canvas.drawLine(mWidth / 2,mHeight / 2 - mHeight / 8,mWidth / 2,mHeight / 2 + mHeight / 8, mPaint);
        canvas.drawLine(mWidth / 2 - mWidth / 8,mHeight / 2,mWidth / 2 + mWidth / 8,mHeight / 2, mPaint); 
        
        
        canvas.drawLine(0 + padding,0 + padding, lWidth + padding,0 + padding, mPaint); // 1
        canvas.drawLine(mWidth - padding,0 + padding,mWidth - lWidth - padding, 0 + padding, mPaint); // 2
        canvas.drawLine(mWidth - padding,0 + padding, mWidth - padding, lHeight + padding, mPaint); // 3
        canvas.drawLine(mWidth - padding, mHeight - padding, mWidth - padding, mHeight - lHeight - padding, mPaint); // 4
        canvas.drawLine(mWidth - padding, mHeight - padding,mWidth - lWidth - padding, mHeight - padding, mPaint); // 5
        canvas.drawLine(0 + padding, mHeight - padding, lWidth + padding, mHeight - padding, mPaint); // 6
        canvas.drawLine(0 + padding, mHeight - padding, 0 + padding, mHeight - lHeight - padding, mPaint); // 7
        canvas.drawLine(0 + padding,0 + padding,0 + padding, lHeight + padding, mPaint); // 8
    }

    public void anim(float ratio) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(this, "scaleX", 1.5f, 1f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(this, "scaleY", 1.5f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animator1).with(animator2);
        animSet.setDuration(200);
        animSet.start();
    }
    
}
