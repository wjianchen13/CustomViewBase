package com.example.customviewbase.demo.changeview;


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
public class ChangeView extends View {
    
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

    public ChangeView(Context context) {
        super(context);
        initPaint();
    }

    public ChangeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        this.mRatio = 0;
    }

    public ChangeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        
        canvas.drawLine(mWidth / 2,mHeight / 2 - mHeight / mCenterNum,mWidth / 2,mHeight / 2 + mHeight / mCenterNum, mPaint); 
        canvas.drawLine(mHeight / 2 - mWidth / mCenterNum,mHeight / 2,mHeight / 2 + mWidth / mCenterNum ,mHeight / 2, mPaint); 
        
        canvas.drawLine(mWidth / 2 * mRatio,mHeight / 2 * mRatio,mWidth / 2 * mRatio + lWidth ,mWidth / 2 * mRatio, mPaint); // 1
        canvas.drawLine(mWidth - mWidth / 2 * mRatio,mHeight / 2 * mRatio,mWidth - mWidth / 2 * mRatio - lWidth , mHeight / 2 * mRatio, mPaint); // 2
        canvas.drawLine(mWidth - mWidth / 2 * mRatio,mHeight / 2 * mRatio,mWidth - mWidth / 2 * mRatio , mHeight / 2 * mRatio + lHeight, mPaint); // 3
        canvas.drawLine(mWidth - mWidth / 2 * mRatio,mHeight - mHeight / 2 * mRatio,mWidth - mWidth / 2 * mRatio - lWidth, mHeight - mHeight / 2 * mRatio, mPaint); // 4
        canvas.drawLine(mWidth - mWidth / 2 * mRatio,mHeight - mHeight / 2 * mRatio,mWidth - mWidth / 2 * mRatio, mHeight - mHeight / 2 * mRatio - lHeight, mPaint); // 5
        canvas.drawLine(mWidth / 2 * mRatio,mHeight - mHeight / 2 * mRatio,mWidth / 2 * mRatio + lWidth, mHeight - mHeight / 2 * mRatio, mPaint); // 6
        canvas.drawLine(mWidth / 2 * mRatio,mHeight - mHeight / 2 * mRatio,mWidth / 2 * mRatio , mHeight - mHeight / 2 * mRatio - lHeight, mPaint); // 7
        canvas.drawLine(mWidth / 2 * mRatio,mHeight / 2 * mRatio,mWidth / 2 * mRatio ,mHeight / 2 * mRatio + lHeight , mPaint); // 8
    }

    public void anim(float ratio) {
        if (null != this.mAnimator) {
            this.mAnimator.cancel();
        }
        this.mAnimator = ValueAnimator.ofFloat(0f, ratio);
        this.mAnimator.setDuration(300);
        this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRatio = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        this.mAnimator.start();
    }
    
}
