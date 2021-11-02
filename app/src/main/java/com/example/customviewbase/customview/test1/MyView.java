package com.example.customviewbase.customview.test1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);   // 获取宽的模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec); // 获取高的模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);   // 获取宽的尺寸
        int heightSize = MeasureSpec.getSize(heightMeasureSpec); // 获取高的尺寸
        
        String wMode = widthMode == MeasureSpec.AT_MOST ? "AT_MOST" : widthMode == MeasureSpec.EXACTLY ? "EXACTLY" : "UNSPECIFIED";
        String hMode = heightMode == MeasureSpec.AT_MOST ? "AT_MOST" : heightMode == MeasureSpec.EXACTLY ? "EXACTLY" : "UNSPECIFIED";
        log("============================================================================================== View");
//        log(getStack());
        log("宽的模式: " + wMode);
        log("高的模式: " + hMode);
        log("宽的尺寸: " + widthSize);
        log("高的尺寸: " + heightSize);
        int width;
        int height ;

        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(200, MeasureSpec.EXACTLY);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(200, MeasureSpec.EXACTLY);
        setMeasuredDimension(childWidthMeasureSpec, childHeightMeasureSpec);
        
    }
    
    public static void log(String str) {
        System.out.println("===================> " + str);
    }

    /**
     * 获取堆栈信息
     * @param
     * @return
     */
    public String getStack() {
        String stack = "";
        Throwable ex = new Throwable();
        StackTraceElement[] stackElements = ex.getStackTrace();
        if(stackElements != null) {
            for(int i = 0; i < stackElements.length; i++) {
                stack += "at " + stackElements[i].getClassName() + "." + stackElements[i].getMethodName()
                        + "(" + stackElements[i].getFileName() + ":" + stackElements[i].getLineNumber() + ")\n";
            }
        }
        return stack;
    }
}
