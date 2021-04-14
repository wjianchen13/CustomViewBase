package com.example.customviewbase.frame;

public class View {

    private AttachInfo mAttachInfo;
    
    void dispatchAttachedToWindow(AttachInfo info, int visibility) {
        //System.out.println("Attached! " + this);
        mAttachInfo = info;
        
        /** 会判断是否是ViewGroup，如果是，则调用到ViewGroup里面的方法 */
//        if (mOverlay != null) {
//            mOverlay.getOverlayView().dispatchAttachedToWindow(info, visibility);
//        }
//        ...
    }
    
}
