package com.example.customviewbase.frame;

import androidx.annotation.LayoutRes;

/*
public class Activity extends ContextThemeWrapper
        implements LayoutInflater.Factory2,
        Window.Callback, KeyEvent.Callback,
        OnCreateContextMenuListener, ComponentCallbacks2,
        Window.OnWindowDismissedCallback { ... }
 */
public class Activity {

    private Window mWindow;
    
    public void setContentView(@LayoutRes int layoutResID) {
        getWindow().setContentView(layoutResID);
        initWindowDecorActionBar();
    }
    
    public Window getWindow() {
        return mWindow;
    }

    public void initWindowDecorActionBar() {

    }
}
