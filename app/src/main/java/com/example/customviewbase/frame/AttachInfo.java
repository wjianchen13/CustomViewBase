package com.example.customviewbase.frame;

import android.os.IBinder;

public class AttachInfo {

    /**
     * 就是访问wms的Binder接口
     */
    final IWindowSession mSession = null;

    /**
     * WMS回调应用程序的Binder接口
     */
//    final IWindow mWindow;
    
    /**
     *  它的赋值是window.asBinder，代表的是W对象，IWindow是通过new W创建的。mWindowToken也是WMS和应用程序交互的Binder接口。获取到后就可以通过view.getWindowToken获取
     */
    final IBinder mWindowToken = null;
    
    AttachInfo(/*IWindowSession session, IWindow window, Display display,
               ViewRootImpl viewRootImpl, Handler handler, Callbacks effectPlayer*/) {
//        mSession = session;
//        mWindow = window;
//        mWindowToken = window.asBinder();
//        mDisplay = display;
//        mViewRootImpl = viewRootImpl;
//        mHandler = handler;
//        mRootCallbacks = effectPlayer;
    }

}
