package com.example.customviewbase.canvas.drawText;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.customviewbase.R;

/**
 * name: 文字描边和渐变
 * desc: canvas绘制文字
 * author:
 * date: 2018-06-26 16:00
 * remark:
 */
public class DrawTextView1 extends View {

    public static final String TAG = DrawTextView1.class.getSimpleName();

    private Context mContext;

    private Paint mTextPaint = new Paint();

    private Paint mStrokePaint;

    private CharSequence mText;

    private Paint mRectPaint;
    private Rect mRectBounds;
    private int mTextHeight;

    private int mTextWidth;

    /**
     * 字体渐变
     */
    private LinearGradient mTextGradient;

    /**
     * 描边渐变
     */
    private LinearGradient mStrokeGradient;

    private Paint paint = null;

    /**
     * 字体颜色是否渐变
     */
    private boolean isTextColorGradient = false;

    /**
     * 描边是否渐变
     */
    private boolean isStrokeGradient = false;

    /**
     * 字体颜色，不是渐变的时候时候
     */

    private @ColorInt int mTextColor = 0;

    /**
     * 描边颜色，不是渐变的时候使用
     */
    private @ColorInt int mStrokeColor = 0;

    /**
     * 渐变字体颜色
     */
    private @ColorInt int mTextStartColor = 0;

    /**
     * 渐变字体颜色
     */
    private @ColorInt int mTextEndColor = 0;

    /**
     * 描边颜色
     */
    private @ColorInt int mStrokeStartColor = 0;

    /**
     * 描边颜色
     */
    private @ColorInt int mStrokeEndColor = 0;

    /**
     * 字体大小
     */
    private int mTextSize;

    /**
     * 描边大小
     */
    private int mStrokeWidth;


    public DrawTextView1(Context context) {
        super(context);
        this.mContext = context;
        reset();
        init();
    }

    public DrawTextView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        reset();
        init();
    }

    public DrawTextView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        reset();
        init();
    }

    private void reset() {
        setData(false, true, ContextCompat.getColor(getContext(), R.color.common_white), 0,
                0, 0, ContextCompat.getColor(getContext(), R.color.c_ll_ff2176f5), ContextCompat.getColor(getContext(), R.color.c_ll_fffb4056));
    }

    /**
     * 设置显示内容
     */
    public void setContent(CharSequence text, int strokeStartColor, int strokeEndColor) {
        this.mText = text;
        setData(ContextCompat.getColor(getContext(), R.color.common_white), strokeStartColor, strokeEndColor);
        requestLayout();
    }

    /**
     * 设置显示数据
     */
    private void setData(int textColor, int strokeStartColor, int strokeEndColor) {
        setData(false, true, textColor, 0,
                0, 0, strokeStartColor, strokeEndColor);
    }

    /**
     * 设置显示数据
     */
    private void setData(boolean textColorGradient, boolean strokeGradient, int textColor, int strokeColor,
                        int textStartColor, int textEndColor, int strokeStartColor, int strokeEndColor) {
        this.isTextColorGradient = textColorGradient;
        this.isStrokeGradient = strokeGradient;
        this.mTextColor = textColor;
        this.mStrokeColor = strokeColor;
        this.mTextStartColor = textStartColor;
        this.mTextEndColor = textEndColor;
        this.mStrokeStartColor = strokeStartColor;
        this.mStrokeEndColor = strokeEndColor;
    }

    private void init() {
        mText = "0";
        mStrokeWidth = 8;
        mTextSize = 50;
        initPaint();
        initStrokePaint();
        initRectPaint();
    }

    private void initPaint() {
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setStyle(Paint.Style.FILL);
//        mTextPaint.setStrokeWidth(25);
        mTextPaint.setFakeBoldText(true);
        mTextPaint.setTextSize(mTextSize);
    }

    private void initStrokePaint() {
        mStrokePaint = new Paint();
        mStrokePaint.setColor(Color.BLACK);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(mStrokeWidth);
        mStrokePaint.setFakeBoldText(true);
        mStrokePaint.setTextSize(mTextSize);
    }

    private void initRectPaint() {
        mRectPaint = new Paint();
        mRectPaint.setColor(Color.RED);
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(1);
        mRectPaint.setTextSize(mTextSize);
    }

    public void getTextBounds() {
        if(mText != null) {
            mRectBounds = new Rect();
            String text = mText.toString();
            Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "DEMONIZED-1.TTF");
            mTextPaint.setTypeface(typeface);
            mTextPaint.getTextBounds(text, 0, mText.length(), mRectBounds);
            Paint.FontMetrics fm = mTextPaint.getFontMetrics();
            float contentHeight = fm.bottom - fm.top;
            int width = mRectBounds.width();
            int height = mRectBounds.height();
            mTextHeight = height;
            mTextWidth = (int)Math.ceil(mTextPaint.measureText(text));
            Log.d(TAG, "Width: " + width + ", Height: " + height);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        getTextBounds();
        int widthMode = MeasureSpec. getMode(widthMeasureSpec);
        int heightMode = MeasureSpec. getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec. getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec. getSize(heightMeasureSpec);
        int layoutWidth = 0;
        int layoutHeight = 0;

        if(widthMode == MeasureSpec.EXACTLY){
            layoutWidth = sizeWidth;
        } else {
            layoutWidth = mTextWidth + getVerticalDistance() * 2 + 5;
        }
        if(heightMode == MeasureSpec. EXACTLY){
            layoutHeight = sizeHeight;
        } else{
            layoutHeight = mTextHeight + getVerticalDistance() * 2;
        }
        setMeasuredDimension(layoutWidth, layoutHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initGradient();
    }

    private void initGradient() {
        // 从assets文件夹中加载TTF字体
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "DEMONIZED-1.TTF");
        mTextPaint.setTypeface(typeface);

        if(isTextColorGradient) {
            mTextGradient = new LinearGradient(0f, 0f + 5, 0f, mTextHeight - 5,
                    new int[]{ContextCompat.getColor(getContext(), R.color.c_ll_ff00bfcb), ContextCompat.getColor(getContext(), R.color.c_ll_ffffc735)}, null, Shader.TileMode.CLAMP);
            mTextPaint.setShader(mTextGradient);
        } else {
            mTextPaint.setShader(null);
            mTextPaint.setColor(mTextColor);
        }
        mStrokePaint.setTypeface(typeface);
        mStrokeGradient = new LinearGradient(0f, 0f +  5, 0f, mTextHeight - 5,
                new int[]{mStrokeStartColor, mStrokeEndColor}, null, Shader.TileMode.CLAMP);
        mStrokePaint.setShader(mStrokeGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mText != null) {
//            canvas.drawRect(0, mTextHeight + 20, 0 + mTextWidth, mTextHeight + 20 + mRectBounds.top - mRectBounds.bottom, mRectPaint);
            String text = mText.toString();
            canvas.drawText(text, (getMeasuredWidth() - mTextWidth) / 2, (getMeasuredHeight() - mTextHeight) / 2 + mTextHeight, mStrokePaint);
            canvas.drawText(text, (getMeasuredWidth() - mTextWidth) / 2, (getMeasuredHeight() - mTextHeight) / 2 + mTextHeight, mTextPaint);
        }
    }

    /**
     * 垂直方向需要加上字体描边的高度，否则描边顶部和底部会显示不全
     * @return
     */
    private int getVerticalDistance() {
        return mStrokeWidth;
    }


}
