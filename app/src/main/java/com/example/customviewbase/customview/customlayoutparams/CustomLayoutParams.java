package com.example.customviewbase.customview.customlayoutparams;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.example.customviewbase.R;

public class CustomLayoutParams extends ViewGroup.MarginLayoutParams {
    
    public static final int POSITION_MIDDLE = 0; // 中间
    public static final int POSITION_LEFT = 1; // 左上方
    public static final int POSITION_RIGHT = 2; // 右上方
    public static final int POSITION_BOTTOM = 3; // 左下角
    public static final int POSITION_RIGHTANDBOTTOM = 4; // 右下角

    public int position = POSITION_LEFT;  // 默认我们的位置就是左上角

    public CustomLayoutParams(Context c, AttributeSet attrs) {
        super(c, attrs);
        TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.CustomLayout);
        // 获取设置在子控件上的位置属性
        position = a.getInt(R.styleable.CustomLayout_layout_position, position);
        a.recycle();
    }

    public CustomLayoutParams( int width, int height) {
        super(width, height);
    }

    public CustomLayoutParams(ViewGroup.LayoutParams source) {
        super(source);
    }


}
