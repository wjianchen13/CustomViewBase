package com.example.customviewbase.customview.custommargin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.customviewbase.customview.customlayoutparams.CustomLayoutParams;

/**
 * 自定义布局管理器的示例。
 * https://blog.csdn.net/ldld1717/article/details/80458917?spm=1001.2014.3001.5501
 * 简单实现水平排列效果
 */
public class CustomMarginLayout extends ViewGroup {
    
    private static final String TAG = "CustomParamsLayout";

    public CustomMarginLayout(Context context) {
        super(context);
    }

    public CustomMarginLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomMarginLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CustomLayoutParams(getContext(), attrs);
    }
    
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new CustomLayoutParams (p);
    }
    
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new CustomLayoutParams (LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
    }
    
    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof CustomLayoutParams;
    }

    /**
     * 要求所有的孩子测量自己的大小，然后根据这些孩子的大小完成自己的尺寸测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式   
        int widthMode = MeasureSpec. getMode(widthMeasureSpec);
        int heightMode = MeasureSpec. getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec. getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec. getSize(heightMeasureSpec);
        int layoutWidth = 0;
        int layoutHeight = 0;
        int cWidth = 0;
        int cHeight = 0;
        int count = getChildCount();

        // 计算出所有的childView的宽和高
        for( int i = 0; i < count; i++){
            View child = getChildAt(i);
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
        }
        CustomLayoutParams params = null;
        if(widthMode == MeasureSpec. EXACTLY){
            //如果布局容器的宽度模式时确定的（具体的size或者match_parent）
            layoutWidth = sizeWidth;
        } else{
            //如果是未指定或者wrap_content，我们都按照包裹内容做，宽度方向上只需要拿到所有子控件中宽度做大的作为布局宽度
            for ( int i = 0; i < count; i++)  {
                View child = getChildAt(i);
                cWidth = child.getMeasuredWidth();
                params = (CustomLayoutParams) child.getLayoutParams();
                //获取子控件宽度和左右边距之和，作为这个控件需要占据的宽度
                int marginWidth = cWidth+params.leftMargin+params.rightMargin ;
                layoutWidth = marginWidth > layoutWidth ? marginWidth : layoutWidth;
            }
        }
        //高度很宽度处理思想一样
        if(heightMode == MeasureSpec. EXACTLY){
            layoutHeight = sizeHeight;
        } else{
            for ( int i = 0; i < count; i++)  {
                View child = getChildAt(i);
                cHeight = child.getMeasuredHeight();
                params = (CustomLayoutParams) child.getLayoutParams();
                int marginHeight = cHeight+params.topMargin+params.bottomMargin ;
                layoutHeight = marginHeight > layoutHeight ? marginHeight : layoutHeight;
            }
        }

        // 测量并保存layout的宽高
        setMeasuredDimension(layoutWidth, layoutHeight);
    }

    @Override
    protected void onLayout( boolean changed, int left, int top, int right,
                             int bottom) {
        final int count = getChildCount();
        int childMeasureWidth = 0;
        int childMeasureHeight = 0;
        CustomLayoutParams params = null;
        for ( int i = 0; i < count; i++) {
            View child = getChildAt(i);
            // 注意此处不能使用getWidth和getHeight，这两个方法必须在onLayout执行完，才能正确获取宽高
            childMeasureWidth = child.getMeasuredWidth();
            childMeasureHeight = child.getMeasuredHeight();
            params = (CustomLayoutParams) child.getLayoutParams();
            switch (params. position) {
                case CustomLayoutParams. POSITION_MIDDLE:    // 中间
                    left = (getWidth()-childMeasureWidth)/2 - params.rightMargin + params.leftMargin ;
                    top = (getHeight()-childMeasureHeight)/2 + params.topMargin - params.bottomMargin ;
                    break;
                case CustomLayoutParams. POSITION_LEFT:      // 左上方
                    left = 0 + params. leftMargin;
                    top = 0 + params. topMargin;
                    break;
                case CustomLayoutParams. POSITION_RIGHT:     // 右上方
                    left = getWidth()-childMeasureWidth - params.rightMargin;
                    top = 0 + params. topMargin;
                    break;
                case CustomLayoutParams. POSITION_BOTTOM:    // 左下角
                    left = 0 + params. leftMargin;
                    top = getHeight()-childMeasureHeight-params.bottomMargin ;
                    break;
                case CustomLayoutParams. POSITION_RIGHTANDBOTTOM:// 右下角
                    left = getWidth()-childMeasureWidth - params.rightMargin;
                    top = getHeight()-childMeasureHeight-params.bottomMargin ;
                    break;
                default:
                    break;
            }

            // 确定子控件的位置，四个参数分别代表（左上右下）点的坐标值
            child.layout(left, top, left+childMeasureWidth, top+childMeasureHeight);
        }
    }
}
