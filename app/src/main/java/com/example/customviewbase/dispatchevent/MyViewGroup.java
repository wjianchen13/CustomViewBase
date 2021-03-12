package com.example.customviewbase.dispatchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by JY113 on 2016/7/1.
 */
public class MyViewGroup extends LinearLayout {

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(Config.show_main) {
            MyLog.log(getTag().toString() + ": " + "dispatchTouchEvent");
        }
        if(Config.detail) {
            switch(ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    MyLog.log(getTag().toString() + ": " + "dispatchTouchEvent ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_MOVE:
                    MyLog.log(getTag().toString() + ": " + "dispatchTouchEvent ACTION_MOVE");
                    break;
                case MotionEvent.ACTION_UP:
                    MyLog.log(getTag().toString() + ": " + "dispatchTouchEvent ACTION_UP");
                    break;
            }
        }
        boolean flag = super.dispatchTouchEvent(ev);

        if(Config.show_default) {
            MyLog.log(getTag().toString() + " dispatchTouchEvent return: " + flag);
        }

        return flag;
//        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(Config.show_main) {
            MyLog.log(getTag().toString() + ": " + "onInterceptTouchEvent");
        }
//        return true;
        if(Config.detail) {
            switch(ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    MyLog.log(getTag().toString() + ": " + "onInterceptTouchEvent ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_MOVE:
                    MyLog.log(getTag().toString() + ": " + "onInterceptTouchEvent ACTION_MOVE");
                    break;
                case MotionEvent.ACTION_UP:
                    MyLog.log(getTag().toString() + ": " + "onInterceptTouchEvent ACTION_UP");
                    break;
            }
        }
        boolean flag = super.onInterceptTouchEvent(ev);
        if(Config.show_default) {
            MyLog.log(getTag().toString() + " onInterceptTouchEvent return: " + flag);
        }

        return flag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(Config.show_main) {
            MyLog.log(getTag().toString() + ": " + "onTouchEvent");
        }
        if(Config.detail) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    MyLog.log(getTag().toString() + ": " + "onTouchEvent ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_MOVE:
                    MyLog.log(getTag().toString() + ": " + "onTouchEvent ACTION_MOVE");
                    break;
                case MotionEvent.ACTION_UP:
                    MyLog.log(getTag().toString() + ": " + "onTouchEvent ACTION_UP");
                    break;
            }
        }
        boolean flag = super.onTouchEvent(event);
        if(Config.show_default) {
            MyLog.log(getTag().toString() + " onTouchEvent return: " + flag);
        }
        return flag;
    }


}
