package com.example.customviewbase.frame;

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
    // ...
    @Nullable
    public View findViewById(@IdRes int id) {
        return getDecorView().findViewById(id);
    }

    /**
     * Convenience for * {@link #setContentView(View, android.view.ViewGroup.LayoutParams)}
     * to set the screen content from a layout resource.  The resource will be * inflated, adding all top-level views to the screen. * * @param layoutResID Resource ID to be inflated.
     * @see #setContentView(View, android.view.ViewGroup.LayoutParams)
     */
    public abstract void setContentView(@LayoutRes int layoutResID);
//     ...
    
    private View getDecorView() {
        return null;
    }
}