package com.example.customviewbase.demo.last;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.view.NestedScrollingChild;

public class CustomViewGroup1 extends ViewGroup {

    private static final int OFFSET = 100;

    public CustomViewGroup1(Context context) {
        super(context);
    }

    public CustomViewGroup1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams (p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams (LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;
        int remainWidth = 0; // 剩余宽度
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams lp = child.getLayoutParams();
            int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, 0, lp.width);
            int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, 0, lp.height);
//            child.measure(childWidthSpec, childHeightSpec);

            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
            if(i == 0) {
                if(child instanceof TextView) {
                    TextView textView = (TextView)child;
                    Layout layout = textView.getLayout();
                    if (layout != null) {
                        int lineCount = layout.getLineCount();
                        if (lineCount > 0) {
                            int lastLineIndex = lineCount - 1;
                            int lastLineWidth = (int) layout.getLineWidth(lastLineIndex);
                            remainWidth = child.getMeasuredWidth() - lastLineWidth;
                            // 输出最后一行的宽度
                            System.out.println("最后一行的宽度：" + lastLineWidth);
                        }
                    }
                }
//                width = getMeasuredWidth();
                height = child.getMeasuredHeight();
            } else if(i == 1) {
                int childWidth = child.getMeasuredWidth();
                if(childWidth + 30 > remainWidth) { // 显示不下了，换行
                    height += child.getMeasuredHeight();
                } else {
                    height = Math.max(height, child.getMeasuredHeight()) + 10;
                }
            }
        }

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                for (int i = 0; i < childCount; i++) {
                    View child = getChildAt(i);
                    int widthAddOffset = i * OFFSET + child.getMeasuredWidth();
                    width = Math.max(width, widthAddOffset);
                }
                break;
            default:
                break;

        }

//        switch (heightMode) {
//            case MeasureSpec.EXACTLY:
//                height = heightSize;
//                break;
//            case MeasureSpec.AT_MOST:
//            case MeasureSpec.UNSPECIFIED:
//                for (int i = 0; i < childCount; i++) {
//                    View child = getChildAt(i);
//                    height = height + child.getMeasuredHeight();
//                }
//                break;
//            default:
//                break;
//
//        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;
        ViewGroup.LayoutParams params = null;
        int childCount = getChildCount();
        int curL = getPaddingLeft();
        int curT = getPaddingTop();

        int lastLineTop = 0; // 最后一行的top
        int lastLineBottom = 0; // 最后一行的bottom
        int remainWidth = 0; // 剩余宽度
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if(i == 0 && child instanceof TextView) {
                params = child.getLayoutParams();
                int leftMargin = 0;
                int topMargin = 0;
                int rightMargin = 0;
                int bottomMargin = 0;
                if (params != null && params instanceof MarginLayoutParams) {
                    MarginLayoutParams p = (MarginLayoutParams) params;
                    leftMargin = p.leftMargin;
                    topMargin = p.topMargin;
                    rightMargin = p.rightMargin;
                    bottomMargin = p.bottomMargin;
                }
                left = curL + leftMargin;
                right = left + child.getMeasuredWidth();
                top = curT + topMargin;
                bottom = top + child.getMeasuredHeight();
                Layout layout = ((TextView)child).getLayout();
                if (layout != null) {
                    int lineCount = layout.getLineCount();
                    if (lineCount > 0) {
                        int lastLineIndex = lineCount - 1;
                        int lastLineWidth = (int) layout.getLineWidth(lastLineIndex);
                        lastLineTop = layout.getLineTop(lastLineIndex);
                        lastLineBottom = layout.getLineBottom(lastLineIndex);
                        remainWidth = child.getMeasuredWidth() - lastLineWidth;
                        System.out.println("最后一行的宽度：" + lastLineWidth);
                    }
                }
                child.layout(left, top, right, bottom);
            } else if(i == 1) {
                params = child.getLayoutParams();
                int leftMargin = 0;
                int topMargin = 0;
                int rightMargin = 0;
                int bottomMargin = 0;
                int parentWidth = getMeasuredWidth();
                if (params != null && params instanceof MarginLayoutParams) {
                    MarginLayoutParams p = (MarginLayoutParams) params;
                    leftMargin = p.leftMargin;
                    topMargin = p.topMargin;
                    rightMargin = p.rightMargin;
                    bottomMargin = p.bottomMargin;
                }
                left = parentWidth - child.getMeasuredWidth();
                right = parentWidth;

                if(child.getMeasuredWidth() + 30 > remainWidth) { // 显示不下了，换行
                    top = lastLineBottom;
                } else {
                    top = lastLineTop;
                }
                bottom = top + child.getMeasuredHeight();
                child.layout(left, top, right, bottom);
            }

        }
    }
}