package com.example.customviewbase.demo.pp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.customviewbase.R;

public class TestFrameLayout extends RelativeLayout {
    
    public static final int TYPE_LEFT = 1;
    public static final int TYPE_MID = 2;
    public static final int TYPE_RIGHT = 3;
    
    private TestManager mManager;
    
    private Rect mRect = new Rect();
    
    private Context mContex;
    
    private int mCoinSize;

    /**
     * 绘制背景
     */
    private Paint mBackgroundPaint = new Paint();
    
    public TestFrameLayout(@NonNull Context context) {
        super(context);
    }

    public TestFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TestFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    
    private void init(Context context) {
        this.mContex = context;
        initBackgroundPaint();
        mRect.set(90, 30, 990, 930);
        mManager = new TestManager();
        mCoinSize = mContex.getResources().getDimensionPixelOffset(R.dimen.pp_coin_size);
        setCoinSize();
    }

    /**
     * 直接添加
     * @param type
     */
    public void addCoins(int type) {
        addCoins(false, type);
    }

    /**
     * 添加，有动画
     * @param type
     */
    public void addCoinsAnim(int type) {
        addCoins(true, type);
    }

    /**
     * 左侧中奖
     */
    public void openLeft() {
        secondAnim(TYPE_MID);
        secondAnim(TYPE_RIGHT);
    }

    /**
     * 中间中奖
     */
    public void openMid() {
        secondAnim(TYPE_LEFT);
        secondAnim(TYPE_RIGHT);
    }

    /**
     * 右边中奖
     */
    public void openRight() {
        secondAnim(TYPE_LEFT);
        secondAnim(TYPE_MID);
    }
    
    public void secondAnim(int type) {
        if(mManager != null) {
            mManager.secondAnim(type);
        }
    }

    public void thirdAnim(int type) {
        if(mManager != null) {
            mManager.thirdAnim(type);
        }
    }

    /**
     * 添加item
     * @param isAnim 是否动画
     * @param type 添加在哪个区域
     */
    private void addCoins(boolean isAnim, int type) {
        TestImageView v = addCoinView(type);
        if(v != null) {
            v.firstTrans(isAnim, type);
            if(mManager != null)
                mManager.addView(v, type);
        }
    }

    /**
     * 添加View
     */
    private TestImageView addCoinView(int type) {
        if(mManager != null) {
            TestImageView img = mManager.getCache();
            if(img == null) {
                img = new TestImageView(mContex);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mCoinSize, mCoinSize);
                params.setMargins(0, 0, 30, 30);
                img.setBackgroundResource(R.drawable.ic_coin);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                addView(img, params);
                Point p = new Point();
//                if (type == TYPE_LEFT) {
                    mManager.createLeft(p);
//                } else if (type == TYPE_MID) {
//                    mManager.createMid(p);
//                } else if (type == TYPE_RIGHT) {
//                    mManager.createRight(p);
//                }
                Point tp = mManager.getTopPoint();
                img.init(mManager.getOffset(), 0, 0, p.x, p.y, tp.x, tp.y, 0, 0);
            }
            img.setType(type);
            return img;
        }
        return null;
    }
    
    public void setCoinSize() {
        if(mManager != null)
            mManager.init(mCoinSize);
    }

    public void setStartRect(int left, int top, int right, int bottom) {
        if(mManager != null) {
            mManager.setStartRect(left, top, right, bottom);
        }
    }
    
    public void setLeftRect(int left, int top, int right, int bottom) {
        if(mManager != null) {
            mManager.setLeftRect(left, top, right, bottom);
        }
    }

    public void setMidRect(int left, int top, int right, int bottom) {
        if(mManager != null) {
            mManager.setMidRect(left, top, right, bottom);
        }
    }

    public void setRightRect(int left, int top, int right, int bottom) {
        if(mManager != null) {
            mManager.setRightRect(left, top, right, bottom);
        }
    }

    public void setTopRect(int left, int top, int right, int bottom) {
        if(mManager != null) {
            mManager.setTopRect(left, top, right, bottom);
        }
    }
    
    /**
     * 绘制背景画笔
     */
    private void initBackgroundPaint() {
        if(mBackgroundPaint == null)
            return ;
        mBackgroundPaint.setColor(Color.argb(100, 255, 0, 0));       //设置画笔颜色
        mBackgroundPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mBackgroundPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        drawRect(canvas);
        super.dispatchDraw(canvas);
    }
    
    private void drawRect(Canvas canvas) {
        if (mBackgroundPaint != null && canvas != null) {
            canvas.drawRect(mRect, mBackgroundPaint);  
        }
    }
    
    public static void log(String str) {
        System.out.println("==========================> " + str);
    }
}