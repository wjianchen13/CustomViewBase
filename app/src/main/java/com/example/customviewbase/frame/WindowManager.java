package com.example.customviewbase.frame;

import android.os.Parcelable;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

/**可以看到WindowManager是一个接口，而且它继承与ViewManager。WindowManager字面理解就是窗口管理器，每一个窗口
 * 管理器都与一个的窗口显示绑定。获取实例可以通过
 * Context.getSystemService(Context.WINDOW_SERVICE)获取。既然继承了ViewManager，那么它也就可以进行添加删除
 * View的操作了，不过它的操作放在它的实现类WindowManagerImpl里面。成员变量里面
 */
public interface WindowManager extends ViewManager {
    
//    public static class BadTokenException extends RuntimeException{...}
//    public static class InvalidDisplayException extends RuntimeException{...}
//    public Display getDefaultDisplay()；
//    public void removeViewImmediate(View view);
//    public static class LayoutParams extends ViewGroup.LayoutParams
//            implements Parcelable () {
//    }

    public static class LayoutParams {
        
    }
    
}