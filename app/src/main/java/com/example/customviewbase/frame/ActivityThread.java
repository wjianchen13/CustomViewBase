package com.example.customviewbase.frame;

import android.annotation.TargetApi;
import android.app.Instrumentation;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import java.util.Map;

public class ActivityThread {

    Instrumentation mInstrumentation;

    /**
     * 
     * @param r
     * @param customIntent
     */
    private void handleLaunchActivity(ActivityClientRecord r, Intent customIntent) {
        
        // Initialize before creating the activity
        WindowManagerGlobal.initialize();

        Activity a = performLaunchActivity(r, customIntent);

        if (a != null) {
//            r.createdConfig = new Configuration(mConfiguration);
//            Bundle oldState = r.state;
//            handleResumeActivity(r.token, false, r.isForward);
            handleResumeActivity(null, false, false, false);
        }
    }

    @TargetApi(21)
    private Activity performLaunchActivity(ActivityClientRecord r, Intent customIntent) {
//        ...
        /** 1. Activity通过ClassLoader创建出来 */
        Activity activity = null;
/*        try { 
            java.lang.ClassLoader cl = r.packageInfo.getClassLoader();
            activity = mInstrumentation.newActivity(
                    cl, component.getClassName(), r.intent);
        } */
//        ...
        try {
            /** 2.接着创建Activity所需的Application和Context */
/*            Application app = r.packageInfo.makeApplication(false, mInstrumentation);*/
            if (activity != null) {
                /** 2.创建Activity所需的Context */
//                Context appContext = createBaseContextForActivity(r, activity);
//                ...
                /** 3.将Context与Activity进行绑定 */
//                activity.attach(appContext, this, getInstrumentation(), r.token,
//                        r.ident, app, r.intent, r.activityInfo, title, r.parent,
//                        r.embeddedID, r.lastNonConfigurationInstances, config,
//                        r.referrer, r.voiceInteractor);
                activity.attach(null, null, null,null, 0, null, null, null, null, null, null);

                /** 4.调用activity.oncreate */
//                mInstrumentation.callActivityOnCreate(activity, r.state, r.persistentState);
                mInstrumentation.callActivityOnCreate(null, null, null);
//                ...
                /** 5.调用Activity的onstart方法 android 10调用performStart的地方和这里不一样 */
//                activity.performStart();
                
                /** 调用activitu的OnRestoreInstanceState方法进行Window数据恢复  */
//                mInstrumentation.callActivityOnRestoreInstanceState(activity, r.state, r.persistentState);
//                ...
                return activity;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * @param token
     * @param clearHide
     * @param isForward
     * @param reallyResume
     */
    final void handleResumeActivity(IBinder token, boolean clearHide, boolean isForward, boolean reallyResume){

        /** 1.调用activity.onResume，把activity数据记录更新到ActivityClientRecord */
        ActivityClientRecord r = performResumeActivity(token, clearHide);

        if (r != null) {
            final Activity a = r.activity;
            /** 2.activity.mStartedActivity是用来标记启动Activity，有没有带返回值，一般我们startActivity(intent)是否默认是startActivityForResult(intent,-1)，默认值是-1，
            所以这里mStartedActivity = false */
            boolean willBeVisible = !a.mStartedActivity;
//        ...
            //mFinished标记Activity有没有结束，而r.window一开始activity并未赋值给ActivityClientRecord，所以这里为null
//            if (r.window == null && !a.mFinished && willBeVisible) {
//                r.window = r.activity.getWindow(); //赋值
                View decor = r.window.getDecorView();
                WindowManagerImpl wm = null;
//                decor.setVisibility(View.INVISIBLE);
//                ViewManager wm = a.getWindowManager();
//                WindowManager.LayoutParams l = r.window.getAttributes();
//                a.mDecor = decor;
//                l.type = WindowManager.LayoutParams.TYPE_BASE_APPLICATION;
//                l.softInputMode |= forwardBit;
//                if (a.mVisibleFromClient) {
//                    a.mWindowAdded = true;
                    /** 3.把当前的DecorView与WindowManager绑定一起 */
                        wm.addView(decor, null);
//                    wm.addView(decor, l);
//                }
//
//        ...
//                if (!r.activity.mFinished && willBeVisible
//                        && r.activity.mDecor != null && !r.hideForNow) {
                    /** 4.标记当前的Activity有没有设置新的配置参数，比如现在手机是横屏的，而之后你转成竖屏，那么这里的newCofig就会被赋值，表示参数改变 */
//                    if (r.newConfig != null) {
//                        r.tmpConfig.setTo(r.newConfig);
//                        if (r.overrideConfig != null) {
//                            r.tmpConfig.updateFrom(r.overrideConfig);
//                        }
                        /** 5.然后调用这个方法回调，表示屏幕参数发生了改变 */
                        performConfigurationChanged(r.activity, null);
//            ...
//                        WindowManager.LayoutParams l = r.window.getAttributes();
//            ...
                      /** 6.改变之后update更新当前窗口的DecorView */
//                        if (r.activity.mVisibleFromClient) {
//                            ViewManager wm = a.getWindowManager();
//                            View decor = r.window.getDecorView();
//                            wm.updateViewLayout(decor, l);
//                        }
//                    }
//                    // 参数没改变
//                    r.activity.mVisibleFromServer = true;
//                    mNumVisibleActivities++;
//                    if (r.activity.mVisibleFromClient) {
                        /** 由于前面设置了INVASIBLE，所以现在要把DecorView显示出来了 */
//                        r.activity.makeVisible();
//                    }
//                }

                  /** 8.通知ActivityManagerService，Activity完成Resumed */
//                ActivityManagerNative.getDefault().activityResumed(token);
//            }
        }
    }


    Map<IBinder, ActivityClientRecord> mActivities;

    public final ActivityClientRecord performResumeActivity(IBinder token, boolean clearHide) {

        ActivityClientRecord r = mActivities.get(token);
//      ...
//        r.activity.mStartedActivity = false;
//        r.activity.onStateNotSaved();
//        r.activity.mFragments.noteStateNotSaved();
//       ...    //Activity调用onResume，就不再贴出来了，里面还有判断要不呀奥onReStart，这个想必知道Activity生命周期的人就秒懂了
//        r.activity.performResume();
//       ...
//        r.paused = false;
//        r.stopped = false;
//        r.state = null;
//        r.persistentState = null;

        return r;
    }


    private void performConfigurationChanged(ComponentCallbacks2 cb, Configuration newConfig) {
        cb.onConfigurationChanged(null);
    }

}
