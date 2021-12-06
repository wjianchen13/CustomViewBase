package com.example.customviewbase.demo.pp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class TestRelativeLayout extends RelativeLayout {

    private CallBack mCallBack;

    public void setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

    public TestRelativeLayout(Context context) {
        super(context);
    }

    public TestRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(mCallBack != null)
            mCallBack.onFinishLayout();
    }
    
    public interface CallBack {
        void onFinishLayout();
    }
    
}
