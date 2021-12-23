package com.example.customviewbase.demo.poker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.customviewbase.demo.pp.TestFrameLayout;

public class PokerItemView extends AppCompatImageView {

    /**
     * 第一点
     */
    private PointF mFirst;

    /**
     * 第二点
     */
    private PointF mSecond;

    /**
     * 旋转角度
     */
    private int mRotation;
    
    /**
     * 区域的偏移距离
     */
    private int mOffset;

    /**
     * 标记当前的icon是属于哪个区域
     */
    private int mType;
    
    private Bitmap mBitmap;
    
    private CallBack mCallBack;
    
    public PokerItemView(@NonNull Context context) {
        super(context);
        init();
    }

    public PokerItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PokerItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

    public void setBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }
    
    public Bitmap getBitmap(){
        return mBitmap;
    }

    private void init() {
        mFirst = new PointF();
        mSecond = new PointF();
    }
    
    public void init(int offset, int fx, int fy, int sx, int xy, int rotation) {
        this.mOffset = offset;
        if(mFirst != null) {
            mFirst.set(fx, fy);
        }
        if(mSecond != null) {
            mSecond.set(sx, xy);
        }
        this.mRotation = rotation;
    }

    public float getEndX() {
        return mSecond.x;
    }

    public float getEndY() {
        return mSecond.y;
    }

    public int getmRotation() {
        return mRotation;
    }

    public void setRotation(int mRotation) {
        this.mRotation = mRotation;
    }

    /**
     * 设置当前view是属于左中右哪个区域
     * @param type
     */
    public void setType(int type) {
        this.mType = type;
    }
    
    
    public void reset() {
        setVisibility(View.GONE);
        setTranslationX(0);
        setTranslationY(0);
        setRotation(0);
        mType = TestFrameLayout.TYPE_DEFAULT;
    }
    
    public void firstTrans(boolean anim, int type) {
        if(anim) {
            if(type == TestFrameLayout.TYPE_LEFT) {
                trans(mFirst.x, mFirst.y, mSecond.x, mSecond.y);
            } else if(type == TestFrameLayout.TYPE_MID) {
                trans(mFirst.x, mFirst.y, mSecond.x, mSecond.y);
            } else if(type == TestFrameLayout.TYPE_RIGHT) {
                trans(mFirst.x, mFirst.y, mSecond.x, mSecond.y);
            }
        } else {
            setTranslationX(mSecond.x);
            setTranslationY(mSecond.y);

            if(mCallBack != null) {
                mCallBack.onAnimationEnd(PokerItemView.this);
            }
            setVisibility(GONE);
        }
    }

    private void trans(float sx, float sy, float ex, float ey) {
        if(sx != ex || sy != ey) {
            ObjectAnimator translationX = ObjectAnimator.ofFloat(this, "translationX", sx, ex).setDuration(3680);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", sy, ey).setDuration(3680);
            ObjectAnimator rotation = ObjectAnimator.ofFloat(this, "rotation", 0f, mRotation);
            rotation.setDuration(280);
            rotation.setStartDelay(400);
            final AnimatorSet animSet = new AnimatorSet();
            animSet.play(translationX).with(translationY).with(rotation);
            animSet.setInterpolator(new AccelerateDecelerateInterpolator());
            animSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    setVisibility(VISIBLE);
                    System.out.println("=====================================> onAnimationStart");
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    System.out.println("=====================================> onAnimationEnd: " + animSet.toString());
                    if(mCallBack != null) {
                        mCallBack.onAnimationEnd(PokerItemView.this);
                    }
                    setVisibility(View.GONE);
                }
            });
            animSet.start();
        }
    }


    private void trans(PointF first, PointF second) {
        if(first != null && second != null) {
            ObjectAnimator translationX = ObjectAnimator.ofFloat(this, "translationX", first.x, second.x);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", first.y, second.y);
            AnimatorSet animSet = new AnimatorSet();
            animSet.play(translationX).with(translationY);
            animSet.setInterpolator(new AccelerateDecelerateInterpolator());
            animSet.setDuration(500);
            animSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    setVisibility(VISIBLE);
                }
            });
            animSet.start();
        }
    }

    /**
     * 获取区域的x坐标
     * @return
     */
    private float getSecondX() {
        float x = 0;
        if(mSecond != null) {
            if(mType == TestFrameLayout.TYPE_LEFT) {
                x = mSecond.x;
            } else if(mType == TestFrameLayout.TYPE_MID) {
                x = mSecond.x + mOffset;
            } else if(mType == TestFrameLayout.TYPE_RIGHT) {
                x = mSecond.x + mOffset * 2;
            }
        }
        return x;
    }
    
    public interface CallBack {
        void onAnimationEnd(PokerItemView v);
    }
    
}
