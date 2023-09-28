package com.example.customviewbase.demo.center;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class MyLayout extends FrameLayout {

    public MyLayout(@NonNull Context context) {
        super(context);
    }

    public MyLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        View v = getChildAt(0);

        if(v != null) {
            System.out.println("========================> measure width: " + getMeasuredWidth() + "   measure height: " + getMeasuredHeight());
            System.out.println("========================> child measure width: " + v.getMeasuredWidth() + "   child measure height: " + v.getMeasuredHeight());
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        View v = getChildAt(0);

        if(v != null) {
            int cl = v.getLeft();
            int ct = v.getTop();
            int cr = v.getRight();
            int cb = v.getBottom();
            System.out.println("========================> my width: " + getWidth() + "   my height: " + getHeight());
            System.out.println("========================> cl: " + cl + "   ct: " + ct + "   cr: " + cr + "   cb: " + cb);
            int childMeasureHeight = v.getMeasuredHeight();
            int height = getHeight();
            if(height < childMeasureHeight) {
                int offset = (childMeasureHeight - height ) / 2;
                v.layout(v.getLeft(), v.getTop() - offset, v.getRight(), v.getBottom() - offset);
            }
            System.out.println("========================> childMeasureHeight: " + childMeasureHeight + "   height: " + height);

        }
    }
}
