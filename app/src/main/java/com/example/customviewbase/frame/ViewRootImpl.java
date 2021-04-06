package com.example.customviewbase.frame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Trace;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.Choreographer;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

class ViewRootImpl {

    public final android.view.WindowManager.LayoutParams mWindowAttributes = new android.view.WindowManager.LayoutParams();
    
    final IWindowSession mWindowSession = null;

    boolean mHandlingLayoutInLayoutRequest = false;

    final Thread mThread = null;

    boolean mLayoutRequested;

    final TraversalRunnable mTraversalRunnable = new TraversalRunnable();

    int mWidth;

    int mHeight;

    View mView;

    android.view.WindowManager.LayoutParams lp = mWindowAttributes;

    public ViewRootImpl(Context context, Display display) {
        
    }
    
    
    public void setView(View view, WindowManager.LayoutParams attrs, View panelParentView) {
//                ...
        /**
         * 首先会调用到requestLayout（），表示添加Window之前先完成第一次layout布局过程，以确保在收到任何系统事件后面重新布局。requestLayout最终会调用performTraversals方法来完成View的绘制。
         */
        requestLayout();
        int res;
        try {
            /**
             * 接着会通过WindowSession最终来完成Window的添加过程。在下面的代码中mWindowSession类型是IWindowSession，
             * 它是一个Binder对象，真正的实现类是Session，也就是说这其实是一次IPC过程，远程调用了Session中的addToDisPlay方法。
             */
//            res = mWindowSession.addToDisplay(mWindow, mSeq, mWindowAttributes,
//                    getHostVisibility(), mDisplay.getDisplayId(),
//                    mAttachInfo.mContentInsets, mAttachInfo.mStableInsets,
//                    mAttachInfo.mOutsets, mInputChannel);
        } catch (Exception e) {
            
        }
    }
    

    /**
     * 这个方法是com.android.server.wm.Session里面的
     * 这里的mService就是WindowManagerService，也就是说Window的添加请求，最终是通过WindowManagerService来添加的。
     */
//    @Override
//    public int addToDisplay(IWindow window, int seq, android.view.WindowManager.LayoutParams attrs,
//                            int viewVisibility, int displayId, Rect outFrame, Rect outContentInsets,
//                            Rect outStableInsets, Rect outOutsets,
//                            DisplayCutout.ParcelableWrapper outDisplayCutout, InputChannel outInputChannel,
//                            InsetsState outInsetsState) {
//        return mService.addWindow(this, window, seq, attrs, viewVisibility, displayId, outFrame,
//                outContentInsets, outStableInsets, outOutsets, outDisplayCutout, outInputChannel,
//                outInsetsState);
//    }
    
    public void requestLayout() {
        if (!mHandlingLayoutInLayoutRequest) {
            checkThread();
            mLayoutRequested = true;
            scheduleTraversals();
        }
    }

    void checkThread() {
        if (mThread != Thread.currentThread()) {
            throw new CalledFromWrongThreadException(
                    "Only the original thread that created a view hierarchy can touch its views.");
        }
    }

    void scheduleTraversals() {
//        if (!mTraversalScheduled) {
//            mTraversalScheduled = true;
//            mTraversalBarrier = mHandler.getLooper().getQueue().postSyncBarrier();
//            mChoreographer.postCallback(
//                    Choreographer.CALLBACK_TRAVERSAL, mTraversalRunnable, null);
//            if (!mUnbufferedInputDispatch) {
//                scheduleConsumeBatchedInput();
//            }
//            notifyRendererOfFramePending();
//            pokeDrawLockIfNeeded();
//        }
        new Handler().post(mTraversalRunnable);
        
    }

    final class TraversalRunnable implements Runnable {
        @Override
        public void run() {
            doTraversal();
        }
    }

    void doTraversal() {
//            ...
        performTraversals();
//            ...
    }

    private void performTraversals() {
        int childWidthMeasureSpec = getRootMeasureSpec(mWidth, lp.width);
        int childHeightMeasureSpec = getRootMeasureSpec(mHeight, lp.height);

//        ......
        performMeasure(childWidthMeasureSpec, childHeightMeasureSpec);
//        ...
        performLayout(lp, mWidth, mHeight);
//        ......
        performDraw(); 
    }

    private void performMeasure(int childWidthMeasureSpec, int childHeightMeasureSpec) {
//        ...
        try {
            mView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        } finally {
//            Trace.traceEnd(Trace.TRACE_TAG_VIEW);
        }
    }

    private void performLayout(android.view.WindowManager.LayoutParams lp, int desiredWindowWidth,
                               int desiredWindowHeight) {
        mLayoutRequested = false;

        final View host = mView;
        if (host == null) {
            return;
        }
        try {
            host.layout(0, 0, host.getMeasuredWidth(), host.getMeasuredHeight());
//            ...
        } finally {

        }
    }

    private void performDraw() {
        final boolean fullRedrawNeeded = true;
        draw(fullRedrawNeeded);
    }

    private boolean draw(boolean fullRedrawNeeded) {
        if (!drawSoftware(null,  0, 0,
                false, null, null)) {
            return false;
        }
        return true;
    }

    private boolean drawSoftware(Surface surface, /*AttachInfo attachInfo,*/ int xoff, int yoff,
                                 boolean scalingRequired, Rect dirty, Rect surfaceInsets) {
        final Canvas canvas = null;
        mView.draw(canvas);
        return true;
    }

    private static int getRootMeasureSpec(int windowSize, int rootDimension) {
        int measureSpec;
        switch (rootDimension) {

            case ViewGroup.LayoutParams.MATCH_PARENT:
                // Window can't resize. Force root view to be windowSize.
                measureSpec = View.MeasureSpec.makeMeasureSpec(windowSize, View.MeasureSpec.EXACTLY);
                break;
            case ViewGroup.LayoutParams.WRAP_CONTENT:
                // Window can resize. Set max size for root view.
                measureSpec = View.MeasureSpec.makeMeasureSpec(windowSize, View.MeasureSpec.AT_MOST);
                break;
            default:
                // Window wants to be an exact size. Force root view to be that size.
                measureSpec = View.MeasureSpec.makeMeasureSpec(rootDimension, View.MeasureSpec.EXACTLY);
                break;
        }
        return measureSpec;
    }

    public static final class CalledFromWrongThreadException extends AndroidRuntimeException {
        public CalledFromWrongThreadException(String msg) {
            super(msg);
        }
    }
    
}
