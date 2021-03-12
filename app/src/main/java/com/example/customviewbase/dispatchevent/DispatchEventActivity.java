package com.example.customviewbase.dispatchevent;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.customviewbase.R;

public class DispatchEventActivity extends Activity {

    private MyViewGroup mvgV2 = null;
    private MyView mvV2 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_event);
        if (Config.show_onTouch) {
            mvgV2 = (MyViewGroup) findViewById(R.id.mvg_v2);
            mvV2 = (MyView) findViewById(R.id.mv_v2);
            mvgV2.setOnTouchListener(new MyTouchListener());
            mvgV2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mvV2.setOnTouchListener(new MyTouchListener());
            mvV2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (Config.show_main) {
            MyLog.log("DispatchEventActivity" + ": " + "dispatchTouchEvent");
        }
        //        return super.dispatchTouchEvent(ev);
        if (Config.detail) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    MyLog.log("DispatchEventActivity" + ": " + "dispatchTouchEvent ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_MOVE:
                    MyLog.log("DispatchEventActivity" + ": " + "dispatchTouchEvent ACTION_MOVE");
                    break;
                case MotionEvent.ACTION_UP:
                    MyLog.log("DispatchEventActivity" + ": " + "dispatchTouchEvent ACTION_UP");
                    break;
            }
        }
        boolean flag = super.dispatchTouchEvent(ev);
        if (Config.show_default) {
            MyLog.log("DispatchEventActivity dispatchTouchEvent return" + ": " + flag);
        }

        return flag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (Config.show_main) {
            MyLog.log("DispatchEventActivity" + ": " + "onTouchEvent");
        }
        //        return super.onTouchEvent(event);
        if (Config.detail) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    MyLog.log("DispatchEventActivity" + ": " + "onTouchEvent ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_MOVE:
                    MyLog.log("DispatchEventActivity" + ": " + "onTouchEvent ACTION_MOVE");
                    break;
                case MotionEvent.ACTION_UP:
                    MyLog.log("DispatchEventActivity" + ": " + "onTouchEvent ACTION_UP");
                    break;
            }
        }
        boolean flag = super.onTouchEvent(event);
        if (Config.show_default) {
            MyLog.log("DispatchEventActivity onTouchEvent return" + ": " + flag);
        }

        return flag;
    }

    private class MyTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (v.getId() == R.id.mvg_v2) {
                if (Config.show_main) {
                    MyLog.log(v.getTag().toString() + ": " + "onTouch");
                }
                if (Config.detail) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            MyLog.log(v.getTag().toString() + ": " + "onTouch ACTION_DOWN");
                            break;
                        case MotionEvent.ACTION_MOVE:
                            MyLog.log(v.getTag().toString() + ": " + "onTouchACTION_MOVE");
                            break;
                        case MotionEvent.ACTION_UP:
                            MyLog.log(v.getTag().toString() + ": " + "onTouch ACTION_UP");
                            break;
                    }
                }
                return false;
            } else if (v.getId() == R.id.mv_v2) {
                if (Config.show_main) {
                    MyLog.log(v.getTag().toString() + ": " + "onTouch");
                }
                if (Config.detail) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            MyLog.log(v.getTag().toString() + ": " + "onTouch ACTION_DOWN");
                            break;
                        case MotionEvent.ACTION_MOVE:
                            MyLog.log(v.getTag().toString() + ": " + "onTouch ACTION_MOVE");
                            break;
                        case MotionEvent.ACTION_UP:
                            MyLog.log(v.getTag().toString() + ": " + "onTouch ACTION_UP");
                            break;
                    }
                }
                return false;
            }
            return false;
        }
    }


    
}

















