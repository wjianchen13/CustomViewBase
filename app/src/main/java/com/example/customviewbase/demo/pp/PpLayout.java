package com.example.customviewbase.demo.pp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.customviewbase.R;
import com.example.customviewbase.demo.pan.PanLayoutParams;

/**
 * 
 * 
 */
public class PpLayout extends ViewGroup {
    
    private static final String TAG = "PokerLayout";
    
    private float mWidth;
    private float mHeight;
    private Bitmap bitmap;
    private int mBitmapWidth;

    private Paint mPaint = new Paint();

    private Matrix matrix = new Matrix();

    /**
     * 绘制背景
     */
    private Paint mBackgroundPaint = new Paint();

    public PpLayout(Context context) {
        super(context);
        initBackgroundPaint();
        init();
    }

    public PpLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initBackgroundPaint();
        init();
    }
    
    public PpLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initBackgroundPaint();
        init();
    }

    /**
     * 绘制背景画笔
     */
    private void initBackgroundPaint() {
        if(mBackgroundPaint == null)
            return ;
        mBackgroundPaint.setColor(Color.argb(255, 255, 0, 0));       //设置画笔颜色
        mBackgroundPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mBackgroundPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    /**
     * 绘制背景画笔
     */
    private void initLinesPaint() {
        if(mBackgroundPaint == null)
            return ;
        mBackgroundPaint.setColor(Color.argb(255, 0, 0, 255));       //设置画笔颜色
        mBackgroundPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mBackgroundPaint.setStrokeWidth(5.0f);         //设置画笔宽度
    }
    
    private void init() {
        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_count_down_2);
        mBitmapWidth = bitmap.getWidth();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new PanLayoutParams(getContext(), attrs);
    }
    
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new PanLayoutParams (p);
    }
    
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new PanLayoutParams (LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT);
    }
    
    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof PanLayoutParams;
    }

    /**
     * 要求所有的孩子测量自己的大小，然后根据这些孩子的大小完成自己的尺寸测量
     */
    @Override
    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec. getMode(widthMeasureSpec);
        int heightMode = MeasureSpec. getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec. getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec. getSize(heightMeasureSpec);
        int layoutWidth = 0;
        int layoutHeight = 0;
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int cWidth = 0;
        int cHeight = 0;
        int count = getChildCount();

        if(widthMode == MeasureSpec. EXACTLY){
            // 如果布局容器的宽度模式是确定的（具体的size或者match_parent），直接使用父窗体建议的宽度
            layoutWidth = sizeWidth;
        } else {
            // 如果是未指定或者wrap_content，我们都按照包裹内容做，宽度方向上只需要拿到所有子控件中宽度做大的作为布局宽度
            for ( int i = 0; i < count; i++)  {
                View child = getChildAt(i);
                cWidth = child.getMeasuredWidth();
                //获取子控件最大宽度
                layoutWidth = cWidth > layoutWidth ? cWidth : layoutWidth;
            }
        }
        // 高度很宽度处理思想一样
        if(heightMode == MeasureSpec. EXACTLY){
            layoutHeight = sizeHeight;
        } else{
            for ( int i = 0; i < count; i++)  {
                View child = getChildAt(i);
                cHeight = child.getMeasuredHeight();
                layoutHeight = cHeight > layoutHeight ? cHeight : layoutHeight;
            }
        }

        // 测量并保存layout的宽高
        setMeasuredDimension(layoutWidth, layoutHeight);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

//        super.dispatchDraw(canvas);

        long drawingTime = getDrawingTime();
        for (int i = 0; i < getChildCount(); i++) {
            drawChild(canvas, getChildAt(i), drawingTime);
        }
    }

    @Override
    protected void onLayout( boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();
        int childMeasureWidth = 0;
        int childMeasureHeight = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            childMeasureWidth = child.getMeasuredWidth();
            childMeasureHeight = child.getMeasuredHeight();
            child.layout(0, 0,  childMeasureWidth,  childMeasureHeight);
        }
        mWidth = getWidth();
        mHeight = getHeight();
    }

    /**
     * 绘制背景
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        if(mBackgroundPaint != null && canvas != null) {
            initBackgroundPaint();
            int center = getWidth() / 2;
            canvas.drawCircle(center,center,center, mBackgroundPaint);  // 绘制一个圆心坐标在(500,500)，半径为400 的圆。
        }
    }

    
    

}





















































