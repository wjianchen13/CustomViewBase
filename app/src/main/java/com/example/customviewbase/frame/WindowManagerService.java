package com.example.customviewbase.frame;

public class WindowManagerService {
    
    public int addWindow() {
        return 1;
    }

//    public int addWindow(Session session, IWindow client, int seq,
//                         WindowManager.LayoutParams attrs, int viewVisibility, int displayId,
//                         Rect outContentInsets, Rect outStableInsets, Rect outOutsets,
//                         InputChannel outInputChannel) {
//        int[] appOp = new int[1];
//        //判断权限
//        int res = mPolicy.checkAddPermission(attrs, appOp);
//        if (res != WindowManagerGlobal.ADD_OKAY) {
//            return res;
//        }
//        ...
//        final int type = attrs.type;
//        synchronized (mWindowMap) {
//            ...
//            boolean addToken = false;
//            WindowToken token = mTokenMap.get(attrs.token);
//            if (token == null) {
//                if (type >= FIRST_APPLICATION_WINDOW && type <= LAST_APPLICATION_WINDOW) {
//                    Slog.w(TAG, "Attempted to add application window with unknown token "
//                            + attrs.token + ".  Aborting.");
//                    return WindowManagerGlobal.ADD_BAD_APP_TOKEN;
//                }
//                if (type == TYPE_INPUT_METHOD) {
//                    Slog.w(TAG, "Attempted to add input method window with unknown token "
//                            + attrs.token + ".  Aborting.");
//                    return WindowManagerGlobal.ADD_BAD_APP_TOKEN;
//                }
//                if (type == TYPE_VOICE_INTERACTION) {
//                    Slog.w(TAG, "Attempted to add voice interaction window with unknown token "
//                            + attrs.token + ".  Aborting.");
//                    return WindowManagerGlobal.ADD_BAD_APP_TOKEN;
//                }
//                if (type == TYPE_WALLPAPER) {
//                    Slog.w(TAG, "Attempted to add wallpaper window with unknown token "
//                            + attrs.token + ".  Aborting.");
//                    return WindowManagerGlobal.ADD_BAD_APP_TOKEN;
//                }
//                if (type == TYPE_DREAM) {
//                    Slog.w(TAG, "Attempted to add Dream window with unknown token "
//                            + attrs.token + ".  Aborting.");
//                    return WindowManagerGlobal.ADD_BAD_APP_TOKEN;
//                }
//                if (type == TYPE_ACCESSIBILITY_OVERLAY) {
//                    Slog.w(TAG, "Attempted to add Accessibility overlay window with unknown token "
//                            + attrs.token + ".  Aborting.");
//                    return WindowManagerGlobal.ADD_BAD_APP_TOKEN;
//                }
//                token = new WindowToken(this, attrs.token, -1, false);
//                addToken = true;
//            } else if (type >= FIRST_APPLICATION_WINDOW && type <= LAST_APPLICATION_WINDOW) {
//                AppWindowToken atoken = token.appWindowToken;
//                if (atoken == null) {
//                    Slog.w(TAG, "Attempted to add window with non-application token "
//                            + token + ".  Aborting.");
//                    return WindowManagerGlobal.ADD_NOT_APP_TOKEN;
//                } else if (atoken.removed) {
//                    Slog.w(TAG, "Attempted to add window with exiting application token "
//                            + token + ".  Aborting.");
//                    return WindowManagerGlobal.ADD_APP_EXITING;
//                }
//                if (type == TYPE_APPLICATION_STARTING && atoken.firstWindowDrawn) {
//                    // No need for this guy!
//                    if (localLOGV) Slog.v(
//                            TAG, "**** NO NEED TO START: " + attrs.getTitle());
//                    return WindowManagerGlobal.ADD_STARTING_NOT_NEEDED;
//                }
//            } else if (type == TYPE_INPUT_METHOD) {
//                if (token.windowType != TYPE_INPUT_METHOD) {
//                    Slog.w(TAG, "Attempted to add input method window with bad token "
//                            + attrs.token + ".  Aborting.");
//                    return WindowManagerGlobal.ADD_BAD_APP_TOKEN;
//                }
//            } else if (type == TYPE_VOICE_INTERACTION) {
//                if (token.windowType != TYPE_VOICE_INTERACTION) {
//                    Slog.w(TAG, "Attempted to add voice interaction window with bad token "
//                            + attrs.token + ".  Aborting.");
//                    return WindowManagerGlobal.ADD_BAD_APP_TOKEN;
//                }
//            } else if (type == TYPE_WALLPAPER) {
//                if (token.windowType != TYPE_WALLPAPER) {
//                    Slog.w(TAG, "Attempted to add wallpaper window with bad token "
//                            + attrs.token + ".  Aborting.");
//                    return WindowManagerGlobal.ADD_BAD_APP_TOKEN;
//                }
//            } else if (type == TYPE_DREAM) {
//                if (token.windowType != TYPE_DREAM) {
//                    Slog.w(TAG, "Attempted to add Dream window with bad token "
//                            + attrs.token + ".  Aborting.");
//                    return WindowManagerGlobal.ADD_BAD_APP_TOKEN;
//                }
//            } else if (type == TYPE_ACCESSIBILITY_OVERLAY) {
//                if (token.windowType != TYPE_ACCESSIBILITY_OVERLAY) {
//                    Slog.w(TAG, "Attempted to add Accessibility overlay window with bad token "
//                            + attrs.token + ".  Aborting.");
//                    return WindowManagerGlobal.ADD_BAD_APP_TOKEN;
//                }
//            } else if (token.appWindowToken != null) {
//                Slog.w(TAG, "Non-null appWindowToken for system window of type=" + type);
//                // It is not valid to use an app token with other system types; we will
//                // instead make a new token for it (as if null had been passed in for the token).
//                attrs.token = null;
//                token = new WindowToken(this, null, -1, false);
//                addToken = true;
//            }
//        ...
//            return res;
//        }
//
//
//    }
}
