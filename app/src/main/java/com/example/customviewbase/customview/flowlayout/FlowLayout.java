package com.example.customviewbase.customview.flowlayout;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.example.customviewbase.customview.customlayoutparams.CustomLayoutParams;

import java.util.ArrayList;
import java.util.List;


public class FlowLayout extends ViewGroup {
    private static final String TAG = "FlowLayout";
    private int mHorizontalSpacing = dp2px(16); //每个item横向间距
    private int mVerticalSpacing = dp2px(8); //每个item横向间距

    private List<List<View>> allLines = new ArrayList<>(); // 记录所有的行，一行一行的存储，用于layout
    List<Integer> lineHeights = new ArrayList<>(); // 记录每一行的行高，用于layout


    public FlowLayout(Context context) {
        super(context);
//        initMeasureParams();
    }

    //反射
    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//        initMeasureParams();
    }

    //主题style
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        initMeasureParams();
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
    
    //四个参数 自定义属性

    private void clearMeasureParams() {
        allLines.clear();
        lineHeights.clear();
    }

    //度量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        clearMeasureParams();//内存 抖动
        //先度量孩子
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);  //ViewGroup解析的父亲给我的宽度
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec); // ViewGroup解析的父亲给我的高度

        List<View> lineViews = new ArrayList<>(); //保存一行中的所有的view
        int lineWidthUsed = 0; //记录这行已经使用了多宽的size
        int lineHeight = 0; // 一行的行高

        int parentNeededWidth = 0;  // measure过程中，子View要求的父ViewGroup的宽
        int parentNeededHeight = 0; // measure过程中，子View要求的父ViewGroup的高

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            MarginLayoutParams childLP =  (MarginLayoutParams) childView.getLayoutParams();
            if (childView.getVisibility() != View.GONE) {
                //将layoutParams转变成为 measureSpec
//                int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight,
//                        childLP.width);
//                int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom,
//                        childLP.height);
//                childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);

                measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, 0);
                
                //获取子view的度量宽高
                int childMesauredWidth = childView.getMeasuredWidth();
                int childMeasuredHeight = childView.getMeasuredHeight();
                log("child width: " + childMesauredWidth + " child height: " + childMeasuredHeight);
                
                int childMarginHorizontal = childLP.leftMargin + childLP.rightMargin;
                
                //如果需要换行
                if (childMesauredWidth + lineWidthUsed + mHorizontalSpacing + childMarginHorizontal > selfWidth) {

                    //一旦换行，我们就可以判断当前行需要的宽和高了，所以此时要记录下来
                    allLines.add(lineViews);
                    lineHeights.add(lineHeight);

                    parentNeededHeight = parentNeededHeight + lineHeight + mVerticalSpacing;
                    parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing);

                    lineViews = new ArrayList<>();
                    lineWidthUsed = 0;
                    lineHeight = 0;
                }
                // view 是分行layout的，所以要记录每一行有哪些view，这样可以方便layout布局
                lineViews.add(childView);
                //每行都会有自己的宽和高
                lineWidthUsed = lineWidthUsed + childMesauredWidth + mHorizontalSpacing + childMarginHorizontal ;
                lineHeight = Math.max(lineHeight, childMeasuredHeight);

                // 处理最后一行数据,如果没有换行，是不会走上面的if分支的，所以会不显示，这里是处理最后一个元素，最后一个元素不换行，不走if分支
                if (i == childCount - 1) {
                    allLines.add(lineViews);
                    lineHeights.add(lineHeight);
                    parentNeededHeight = parentNeededHeight + lineHeight + mVerticalSpacing;
                    parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing);
                }

            }
        }



        //再度量自己,保存
        //根据子View的度量结果，来重新度量自己ViewGroup
        // 作为一个ViewGroup，它自己也是一个View,它的大小也需要根据它的父亲给它提供的宽高来度量
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int realWidth = (widthMode == MeasureSpec.EXACTLY) ? selfWidth: parentNeededWidth;
        int realHeight = (heightMode == MeasureSpec.EXACTLY) ?selfHeight: parentNeededHeight;
        setMeasuredDimension(realWidth, realHeight);
    }

    //布局
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineCount = allLines.size();

        int curL = getPaddingLeft();
        int curT = getPaddingTop();
        MarginLayoutParams params = null;
        for (int i = 0; i < lineCount; i++){
            List<View> lineViews = allLines.get(i);
            
            int lineHeight = lineHeights.get(i);
            for (int j = 0; j < lineViews.size(); j++){
                View view = lineViews.get(j);
                params = (MarginLayoutParams) view.getLayoutParams();
                log("margin left: " + params.leftMargin + " margin top: " + params.topMargin + " margin right: " + params.rightMargin + " margin bottom: " + params.bottomMargin);
                int left = curL + params.leftMargin;
                int top =  curT;

//                int right = left + view.getWidth();
//                int bottom = top + view.getHeight();

                 int right = left + view.getMeasuredWidth();
                 int bottom = top + view.getMeasuredHeight();
                 view.layout(left,top,right,bottom);
                 curL = right + mHorizontalSpacing + params.rightMargin;
            }
            curT = curT + lineHeight + mVerticalSpacing;
            curL = getPaddingLeft();
        }

    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//    }

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
    
    private void log(String str) {
        System.out.println("=========================> " + str);
    }

}
