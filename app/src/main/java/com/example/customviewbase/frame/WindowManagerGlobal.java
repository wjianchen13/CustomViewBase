package com.example.customviewbase.frame;

import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

public final class WindowManagerGlobal {
    private static final String TAG = "WindowManager";
    
    public static void initialize() {
        // 创建了WindowManagerService
    }
    
    public static WindowManagerGlobal getInstance() {
        return new WindowManagerGlobal();
    }

    public void addView(View view, ViewGroup.LayoutParams params,
                        Display display, Window parentWindow) {
        ...
        ViewRootImpl root;
        View panelParentView = null;
        
        ...

        root = new ViewRootImpl(view.getContext(), display);
        view.setLayoutParams(wparams);
        mViews.add(view);
        mRoots.add(root);
        mParams.add(wparams);
        //ViewRootImpl开始绘制view
        root.setView(view, wparams, panelParentView);
        ...
    }

    public void removeView(View view, boolean flag) {
        
    }
}