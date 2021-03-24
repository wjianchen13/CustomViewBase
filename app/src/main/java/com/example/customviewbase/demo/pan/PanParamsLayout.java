package com.example.customviewbase.demo.pan;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义布局管理器的示例。
 * https://blog.csdn.net/ldld1717/article/details/80458917?spm=1001.2014.3001.5501
 * 简单实现水平排列效果
 */
public class PanParamsLayout extends ViewGroup {
    
    private static final String TAG = "CustomParamsLayout";

    public PanParamsLayout(Context context) {
        super(context);
    }

    public PanParamsLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PanParamsLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
        return new PanLayoutParams (LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
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
        // 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式  
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
    protected void onLayout( boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();
        int childMeasureWidth = 0;
        int childMeasureHeight = 0;
        PanLayoutParams params = null;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            // 注意此处不能使用getWidth和getHeight，这两个方法必须在onLayout执行完，才能正确获取宽高
            childMeasureWidth = child.getMeasuredWidth();
            childMeasureHeight = child.getMeasuredHeight();

            params = (PanLayoutParams) child.getLayoutParams();
            switch (params.position) {
                case PanLayoutParams.POSITION_MIDDLE:    // 中间
                    left = (getWidth() - childMeasureWidth) / 2;
                    top = (getHeight() - childMeasureHeight) / 2;
                    break;
                case PanLayoutParams.POSITION_LEFT:      // 左上方
                    left = 0;
                    top = 0;
                    break;
                case PanLayoutParams.POSITION_RIGHT:     // 右上方
                    left = getWidth() - childMeasureWidth;
                    top = 0;
                    break;
                case PanLayoutParams.POSITION_BOTTOM:    // 左下角
                    left = 0;
                    top = getHeight() - childMeasureHeight;
                    break;
                case PanLayoutParams.POSITION_RIGHTANDBOTTOM:// 右下角
                    left = getWidth() - childMeasureWidth;
                    top = getHeight() - childMeasureHeight;
                    break;
                default:
                    break;
            }

            // 确定子控件的位置，四个参数分别代表（左上右下）点的坐标值
            child.layout(left, top, left + childMeasureWidth, top + childMeasureHeight);
        }
    }

    @Override
    public void addView(View child) {
        super.addView(child);
    }
}
