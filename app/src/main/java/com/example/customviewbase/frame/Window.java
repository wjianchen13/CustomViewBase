package com.example.customviewbase.frame;

import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

/**
 * 一个顶级窗口查看和行为的一个抽象基类。这个类的实例作为一个顶级View添加到Window Manager。它提供了一套标准的UI方法，
 * 比如添加背景，标题等等。当你需要用到Window的时候，你应该使用它的唯一实现类PhoneWindow。可以看到，Window是一个抽象基类，
 * 它提供了一系列窗口的方法，比如设置背景，标题等等，而它的唯一实现类则是PhoneWindow
 */
public abstract class Window {

    private Context mContext;
    
//    @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.P, trackingBug = 115609023)
    private WindowManager mWindowManager;
    
    // ...
    @Nullable
    public View findViewById(@IdRes int id) {
        return getDecorView().findViewById(id);
    }

    /**
     * Convenience for * {setContentView(View, android.view.ViewGroup.LayoutParams)}
     * to set the screen content from a layout resource.  The resource will be * inflated, adding all top-level views to the screen. * * @param layoutResID Resource ID to be inflated.
     * setContentView(View, android.view.ViewGroup.LayoutParams)
     */
    public abstract void setContentView(@LayoutRes int layoutResID);
//     ...
    
    public View getDecorView() {
        return null;
    }

    public void setWindowManager(WindowManager wm, IBinder appToken, String appName,
                                 boolean hardwareAccelerated) {
//        mAppToken = appToken;
//        mAppName = appName;
//        mHardwareAccelerated = hardwareAccelerated;
//        if (wm == null) {
//            wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
//        }
        mWindowManager = ((WindowManagerImpl)wm).createLocalWindowManager(this);
    }

    public WindowManager getWindowManager() {
        return mWindowManager;
    }
}