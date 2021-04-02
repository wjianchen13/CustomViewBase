package com.example.customviewbase.frame;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//private final class DecorView extends FrameLayout implements RootViewSurfaceTaker {
 final class DecorView extends FrameLayout {

    /* package */int mDefaultOpacity = PixelFormat.OPAQUE;

    /** The feature ID of the panel, or -1 if this is the application's DecorView */
    private final int mFeatureId = 0;

    private final Rect mDrawingBounds = new Rect();

    private final Rect mBackgroundPadding = new Rect();

    private final Rect mFramePadding = new Rect();

    private final Rect mFrameOffsets = new Rect();
//        ....


    public DecorView(@NonNull Context context, int mDefaultOpacity) {
        super(context);
        this.mDefaultOpacity = mDefaultOpacity;
    }

    public DecorView(@NonNull Context context, @Nullable AttributeSet attrs, int mDefaultOpacity) {
        super(context, attrs);
        this.mDefaultOpacity = mDefaultOpacity;
    }

    public DecorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int mDefaultOpacity) {
        super(context, attrs, defStyleAttr);
        this.mDefaultOpacity = mDefaultOpacity;
    }

    public DecorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes, int mDefaultOpacity) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mDefaultOpacity = mDefaultOpacity;
    }
}
