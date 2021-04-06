package com.example.customviewbase.frame;

import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

/**
 * 可以看到WindowManagerImpl里面有一个成员变量WindowManagerGlobal，而真正的实现则是在WindowManagerGlobal了，
 * 类似代理，只不过WindowManagerGlobal是个没有实现WindowManager的类的，自己定义了套实现。
 */
public final class WindowManagerImpl implements WindowManager {
    
    private Context mContext;
    private final WindowManagerGlobal mGlobal = WindowManagerGlobal.getInstance();
    private final Display mDisplay = null;
    private final Window mParentWindow = null;
    
    public WindowManagerImpl(Context context, Window parentWindow) {
        
    }
    
    @Override
    public void addView(@NonNull View view, @NonNull ViewGroup.LayoutParams params) {
        applyDefaultToken(params);
        mGlobal.addView(view, params, mDisplay, mParentWindow);
    }
    @Override
    public void removeView(View view) {
        mGlobal.removeView(view, false);
    }

    @Override
    public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
        
    }
    
    private void applyDefaultToken(ViewGroup.LayoutParams params) {
        
    }

    public WindowManagerImpl createLocalWindowManager(Window parentWindow) {
        return new WindowManagerImpl(mContext, parentWindow);
    }
}
