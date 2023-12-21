package com.example.customviewbase.demo.last;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomViewGroup extends ViewGroup {
    private TextView textView;

    // 在构造方法中初始化 TextView
    public CustomViewGroup(Context context) {
        super(context);

    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int measuredHeight = heightSize; //
        int measuredWidth = widthSize; //


        // 测量 TextView 的宽度和高度
        int textViewWidthMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.AT_MOST);
        int textViewHeightMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.AT_MOST);
        int childCount = getChildCount(); // 子View數量
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }

            LayoutParams childParams = child.getLayoutParams();
            int horizontalMargin = 0, verticalMargin = 0;
            if (childParams instanceof MarginLayoutParams) {
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                MarginLayoutParams marginParams = (MarginLayoutParams) childParams;
                horizontalMargin = marginParams.leftMargin + marginParams.rightMargin;
                verticalMargin = marginParams.topMargin + marginParams.bottomMargin;
            } else {
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
            }
            if(child instanceof TextView) {
                textView = (TextView)child;
//                textView.setText("hello");
                textView.measure(textViewWidthMeasureSpec, textViewHeightMeasureSpec);

                // 获取 TextView 布局对象
                Layout layout = textView.getLayout();
                if (layout != null) {
                    int lineCount = layout.getLineCount();
                    if (lineCount > 0) {
                        int lastLineIndex = lineCount - 1;
                        int lastLineWidth = (int) layout.getLineWidth(lastLineIndex);
                        layout.getLineTop(lastLineWidth);
                        // 输出最后一行的宽度
                        System.out.println("最后一行的宽度：" + lastLineWidth);
                    }
                }
            }
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 布局 TextView
        textView.layout(l, t, r, b);
    }
}
