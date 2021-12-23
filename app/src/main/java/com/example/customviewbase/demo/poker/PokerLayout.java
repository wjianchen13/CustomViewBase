package com.example.customviewbase.demo.poker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.customviewbase.R;

public class PokerLayout extends RelativeLayout implements PokerItemView.CallBack {

    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_LEFT = 1;
    public static final int TYPE_MID = 2;
    public static final int TYPE_RIGHT = 3;
    
    private PokerManager mManager;
    
    private Rect mRect = new Rect();
    
    private Context mContex;
    
    private int mCoinSize;

    private Bitmap tBitmap;

    private Bitmap ttBitmap;
    
    private Canvas mCanvas;

    private Canvas mttCanvas;

    private Matrix matrix = new Matrix();

    private Matrix ttmatrix = new Matrix();

    private Bitmap bitmap;

    private Paint paint = new Paint();
    
    private Paint testPaint = new Paint();
    
    /**
     * 绘制背景
     */
    private Paint mBackgroundPaint = new Paint();
    
    public PokerLayout(@NonNull Context context) {
        super(context);
    }

    public PokerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PokerLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    
    private void initTestPaint() {
        testPaint.setColor(Color.argb(100, 255, 0, 0));       //设置画笔颜色
        testPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        testPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
        
    }
    
    private void init(Context context) {
        this.mContex = context;
        initTestPaint();
        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_dino_poker_bet_10);
        initBackgroundPaint();
//        mRect.set(90, 30, 990, 930);
        mManager = new PokerManager();
        mCoinSize = mContex.getResources().getDimensionPixelOffset(R.dimen.pp_coin_size);
        if(mManager != null)
            mManager.init(mCoinSize);
    }

    public void setInfo(Rect all, Rect start, Rect left, Rect mid, Rect right) {
        if(mRect != null && all != null) {
            mRect.set(all.left, all.top, all.right, all.bottom);
        }
        if(mManager != null && start != null && left != null && mid != null && right != null) {
            mManager.setStartRect(start.left, start.top, start.right, start.bottom);
            mManager.setLeftRect(left.left, left.top, left.right, left.bottom);
            mManager.setMidRect(mid.left, mid.top, mid.right, mid.bottom);
            mManager.setRightRect(right.left, right.top, right.right, right.bottom);
        } else {
            Toast.makeText(mContex, "数据异常", Toast.LENGTH_SHORT).show();
        }
        tBitmap = Bitmap.createBitmap(mRect.right - mRect.left, mRect.bottom - mRect.top, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(this.tBitmap);
        ttBitmap = Bitmap.createBitmap(mRect.right - mRect.left, mRect.bottom - mRect.top, Bitmap.Config.ARGB_8888);
        mttCanvas = new Canvas(this.ttBitmap);
    }

    
    
    public void createBitmap1() {
        if (ttmatrix != null){
            ttmatrix.reset();
            ttmatrix.postRotate(45, bitmap.getWidth() * 0.5f, bitmap.getHeight() * 0.5f);
            ttmatrix.postTranslate(150, 150);
            int lightCount = mttCanvas.save();
            mttCanvas.drawRect(0, 0, mRect.right, mRect.bottom, testPaint);
            mttCanvas.drawBitmap(bitmap, ttmatrix, paint);
            mttCanvas.restoreToCount(lightCount);
        }
    }


    public void createBitmap() {
        if (matrix != null){
            matrix.reset();
            matrix.postRotate(45, bitmap.getWidth() * 0.5f, bitmap.getHeight() * 0.5f);
            matrix.postTranslate(150, 150);
            int count = mCanvas.save();
            mCanvas.drawRect(0, 0, mRect.right, mRect.bottom, testPaint);
            mCanvas.drawBitmap(bitmap, matrix, paint);
            mCanvas.restoreToCount(count);
//          invalidate();
        }
    }

    public void createBitmap2() {
        if (matrix != null){
            matrix.reset();
            matrix.postRotate(45, bitmap.getWidth() * 0.5f, bitmap.getHeight() * 0.5f);
            matrix.postTranslate(250, 250);
            int count = mCanvas.save();
            mCanvas.drawRect(0, 0, mRect.right, mRect.bottom, testPaint);
            mCanvas.drawBitmap(bitmap, matrix, paint);
            mCanvas.restoreToCount(count);
//          invalidate();
        }
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
     * 添加item
     * @param isAnim 是否动画
     * @param type 添加在哪个区域
     */
    private void addCoins(boolean isAnim, int type) {
        PokerItemView v = addCoinView(type);
        if(v != null) {
            v.firstTrans(isAnim, type);
            if(mManager != null)
                mManager.addView(v, type);
        }
    }

    /**
     * 添加View
     */
    private PokerItemView addCoinView(int type) {
        if(mManager != null) {
            PokerItemView img = mManager.getCache();
            if(img == null) {
                img = new PokerItemView(mContex);
                img.setCallBack(this);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mCoinSize, mCoinSize);
                params.setMargins(0, 0, dip2px(24), dip2px(24));
                img.setBackgroundResource(R.drawable.ic_dino_poker_bet_10);
                addView(img, params);
                Point p = new Point();
                mManager.createLeft(p);
                
                img.init(mManager.getOffset(), mManager.getStartX(), mManager.getStartY(), p.x + getStartOffset(type), p.y, mManager.createRotation());
            }
            img.setBitmap(bitmap);
            img.setType(type);
            return img;
        }
        return null;
    }
    
    public int getStartOffset(int type) {
        if(mManager != null) {
            return type == TYPE_RIGHT ? 2 * mManager.getOffset() : type == TYPE_MID ? 1 * mManager.getOffset() : 0;
        }
        return 0;
    }

    @Override
    public void onAnimationEnd(PokerItemView v) {
        if(v != null) {
            Bitmap bitmap = v.getBitmap();
            addBitmap(bitmap, (int)v.getEndX(), (int)v.getEndY(), v.getmRotation());
        }
    }
    
    public void addBitmap(Bitmap bp, int x, int y, float r) {
        if (matrix != null && bp != null){
            matrix.reset();
            matrix.postRotate(r, bp.getWidth() * 0.5f, bp.getHeight() * 0.5f);
            matrix.postTranslate(x, y);
//            mCanvas.drawRect(0, 0, mRect.right, mRect.bottom, testPaint);
            mCanvas.drawBitmap(bp, matrix, paint);
            invalidate();
        }

//        createBitmap();
    }

    public void transport() {
        if(tBitmap != null) {
            tBitmap.eraseColor(Color.TRANSPARENT);
        }
    }


    @Override
    public void invalidate() {
        super.invalidate();
    }

    public void invalidate1() {
        this.invalidate();
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
        drawBackground(canvas);
        super.dispatchDraw(canvas);
    }
    
    private void drawBackground(Canvas canvas) {
        if(tBitmap != null) {
            matrix.reset();
            canvas.drawBitmap(tBitmap, matrix, paint);
        }
    }

    public int dip2px(float dpValue) {
        return dip2px(mContex, dpValue);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        return (int) (dpValue * getDensity(context) + 0.5f);
    }

    /**
     * 返回屏幕密度
     */
    public float getDensity(Context context) {
        try {
            return context.getResources().getDisplayMetrics().density;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2.0f;
    }
    
}
