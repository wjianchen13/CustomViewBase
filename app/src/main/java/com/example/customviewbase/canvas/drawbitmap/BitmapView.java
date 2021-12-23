package com.example.customviewbase.canvas.drawbitmap;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.customviewbase.R;

/**
 * name: BitmapView
 * desc: canvas绘制Bitmap
 * author:
 * date: 2018-06-26 16:00
 * remark:
 */
public class BitmapView extends View {

    Bitmap bitmap;

    private float mWidth;
    private float mHeight;
    
    public BitmapView(Context context) {
        super(context);
    }

    // 1.创建一个画笔
    private Paint mPaint = new Paint();
    
    private Matrix matrix = new Matrix();
    
    private Paint paint = new Paint();
    
    private int mBitmapWidth;
    
    // 3.在构造函数中初始化
    public BitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_coin);
        mBitmapWidth = bitmap.getWidth();
        initPaint();
    }

    // 2.初始化画笔
    private void initPaint() {
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    public BitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();
        mHeight = getHeight();
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
//        canvas.drawLine(0,mHeight / 2, mWidth,mHeight / 2, mPaint);
//        canvas.drawLine(mWidth / 2 ,0,mWidth / 2,mHeight, mPaint);   
//        canvas.translate(mWidth / 2 - mBitmapWidth / 2, mHeight / 2);\
//        matrix.postTranslate(100, 100);
        matrix.postRotate(45, bitmap.getWidth()*0.5f,bitmap.getHeight()*0.5f);
        canvas.drawBitmap(bitmap, matrix, paint);
//        matrix.reset();
//        matrix.postTranslate(100, 100);
//        canvas.drawBitmap(bitmap, matrix, paint);
//        canvas.drawBit
        
//        canvas.rotate(90,mBitmapWidth / 2,0);               // 旋转180度 <-- 旋转中心向右偏移200个单位
//        canvas.drawBitmap(bitmap, matrix, paint);
//        canvas.rotate(90,mBitmapWidth / 2,0);               // 旋转180度 <-- 旋转中心向右偏移200个单位
//        canvas.drawBitmap(bitmap, matrix, paint);
//        canvas.rotate(90,mBitmapWidth / 2,0);               // 旋转180度 <-- 旋转中心向右偏移200个单位
//        canvas.drawBitmap(bitmap, matrix, paint);
//        canvas.restore();
        
//        canvas.save();
//        canvas.rotate(90);
//        canvas.drawBitmap(bitmap, matrix, paint);
//        canvas.rotate(90);
//        canvas.drawBitmap(bitmap, matrix, paint);
//        canvas.rotate(90);
//        canvas.drawBitmap(bitmap, matrix, paint);
//        canvas.restore();
    }
}
