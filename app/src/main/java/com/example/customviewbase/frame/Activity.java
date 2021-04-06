package com.example.customviewbase.frame;

import android.app.Application;
import android.app.Instrumentation;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.IBinder;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

/*
public class Activity extends ContextThemeWrapper
        implements LayoutInflater.Factory2,
        Window.Callback, KeyEvent.Callback,
        OnCreateContextMenuListener, ComponentCallbacks2,
        Window.OnWindowDismissedCallback { ... }
 */
public class Activity implements ComponentCallbacks2 {

    /**
     * 在ContextWrapper中定义
     */
    Context mBase;
    
    private Window mWindow;

    private WindowManager mWindowManager;
    
    public boolean mStartedActivity;
    
    public void setContentView(@LayoutRes int layoutResID) {
        getWindow().setContentView(layoutResID);
        initWindowDecorActionBar();
    }
    
    public Window getWindow() {
        return mWindow;
    }

    public void initWindowDecorActionBar() {

    }

    /**
     * 在Activity创建到attach的时候，对应的Window窗口也被创建起来，而且Window也与WindowManager绑定
     */
    final void attach(Context context, ActivityThread aThread,
                      Instrumentation instr, IBinder token, int ident,
                      Application application, Intent intent, ActivityInfo info,
                      CharSequence title, Activity parent, String id)
                      /*NonConfigurationInstances lastNonConfigurationInstances,
                      Configuration config, String referrer, IVoiceInteractor voiceInteractor) */{

        /** 1.ContextImpl的绑定 */
        attachBaseContext(context);
        /** 2.在当前Activity创建Window */
        mWindow = new PhoneWindow(null);
        /** 为Window设置WindowManager 可以看到这里WindiwManager的创建是context.getSystemService(Context.WINDOW_SERVICE)*/
        mWindow.setWindowManager(null,
                null, "",
                (info.flags & ActivityInfo.FLAG_HARDWARE_ACCELERATED) != 0);
        mWindowManager = mWindow.getWindowManager();
                          
        // 1.ContextImpl的绑定 
//        attachBaseContext(context);
        // 2.在当前Activity创建Window 
//        mWindow = new PhoneWindow(this);
//        mWindow.setCallback(this);
//        mWindow.setOnWindowDismissedCallback(this);
//        mWindow.getLayoutInflater().setPrivateFactory(this);
//        ...
//        //为Window设置WindowManager
//        mWindow.setWindowManager(
//                (WindowManager)context.getSystemService(Context.WINDOW_SERVICE),
//                mToken, mComponent.flattenToString(),
//                (info.flags & ActivityInfo.FLAG_HARDWARE_ACCELERATED) != 0);
//        if (mParent != null) {
//            mWindow.setContainer(mParent.getWindow());
//        }
//        //创建完后通过getWindowManager就可以得到WindowManager实例
//        mWindowManager = mWindow.getWindowManager();
//        mCurrentConfig = config;
    }

    /**
     * 最终会调用到ContextWrapper的attachBaseContext()方法
     * @param base
     */
    protected void attachBaseContext(Context base) {
        if (mBase != null) {
            throw new IllegalStateException("Base context already set");
        }
        mBase = base;
    }
    
    public ViewManager getWindowManager() {
        return null;
    }

    @Override
    public void onTrimMemory(int level) {
        
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {

    }

    @Override
    public void onLowMemory() {

    }
}
