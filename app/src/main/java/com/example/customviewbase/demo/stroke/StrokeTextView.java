package com.example.customviewbase.demo.stroke;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;

/**
 * 不能添加SingleLine，添加了描边没有效果
 * xml不要设置gravity，可能是代码里面onMeasure 加了个偏移，所以会导致文字和阴影对不齐
 */
public class StrokeTextView extends Chronometer {
    private TextView outlineTextView = null;
    
    private int dis = 0;

    public StrokeTextView(Context context) {
        super(context);

        outlineTextView = new TextView(context);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        outlineTextView = new TextView(context, attrs);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        outlineTextView = new TextView(context, attrs, defStyle);
        init();
    }

    public void init() {
        TextPaint paint = outlineTextView.getPaint();
        paint.setStrokeWidth(3);  //描边宽度  
        paint.setStyle(Paint.Style.STROKE);
        outlineTextView.setTextColor(Color.parseColor("#000000"));  //描边颜色  
        outlineTextView.setGravity(getGravity());
    }

    @Override
    public void setLayoutParams (ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        outlineTextView.setLayoutParams(params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth() + 6, getMeasuredHeight());
        //设置轮廓文字  
        CharSequence outlineText = outlineTextView.getText();
        if (outlineText == null || !outlineText.equals(this.getText())) {
            outlineTextView.setText(getText());
            postInvalidate();
        }
        int expandSpec = MeasureSpec.makeMeasureSpec(
                getMeasuredWidth() + 6, MeasureSpec.EXACTLY);
        outlineTextView.measure(expandSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout (boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left + dis, top, right + dis, bottom);
        outlineTextView.layout(left+ dis, top, right+ dis, bottom);
    }
//    
//    @Override
//    public void layout(int l, int t, int r, int b) {
//        super.layout(l, t, r, b);
//        outlineTextView.layout(l + 16, t, r + 16, b);
//    }



    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.save();
//        canvas.translate(16, 0);
        outlineTextView.draw(canvas);
//        outlineTextView.setBackgroundColor(Color.parseColor("#0000ff"));
//        canvas.translate(32, 0);
        super.onDraw(canvas);
//        canvas.restore();
    }
}  