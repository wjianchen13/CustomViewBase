package com.example.customviewbase.specmode;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2018/9/29.
 * a.当控件的layout_width或layout_height指定为wrap_content时，为AT_MOST
 b.当控件的layout_width或layout_height指定为match_parent或具体数值时，为EXACTLY
 */

public class CustomView extends View {
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //宽度
        String specMode_width = "";
        int specModeWidth = MeasureSpec.getMode(widthMeasureSpec);
        switch (specModeWidth) {
            case MeasureSpec.EXACTLY:
                specMode_width = "EXACTLY";
                break;
            case MeasureSpec.AT_MOST:
                specMode_width = "AT_MOST";
                break;
            case MeasureSpec.UNSPECIFIED:
                specMode_width = "UNSPECIFIED";
                break;
        }
        //高度度
        String specMode_height = "";
        int specModeHeight = MeasureSpec.getMode(heightMeasureSpec);
        switch (specModeHeight) {
            case MeasureSpec.UNSPECIFIED:
                specMode_height = "UNSPECIFIED";
                break;
            case MeasureSpec.AT_MOST:
                specMode_height = "AT_MOST";
                break;
            case MeasureSpec.EXACTLY:
                specMode_height = "EXACTLY";
                break;
        }
        setMeasuredDimension(200, 200);
        Log.e("TAG", "specMode_width = " + specMode_width + " , specMode_height = " + specMode_height);
        Log.e("TAG", "specSize_width = " + MeasureSpec.getSize(widthMeasureSpec) + ", specSize_height = " + MeasureSpec.getSize(heightMeasureSpec));
    }

}
