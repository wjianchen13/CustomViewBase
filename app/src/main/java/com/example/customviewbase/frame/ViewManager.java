package com.example.customviewbase.frame;

import android.view.View;
import android.view.ViewGroup;

/** ViewManager接口定义了一组规则，也就是add、update、remove的操作View接口。也就是说ViewManager是用来
 * 添加和移除activity中View的接口，可以通过Context.getSystemService（）获取实例。
 */
public interface ViewManager {
    
    public void addView(View view, ViewGroup.LayoutParams params);
    public void updateViewLayout(View view, ViewGroup.LayoutParams params);
    public void removeView(View view);

}