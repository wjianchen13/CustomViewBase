package com.example.customviewbase.demo.pp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class TestImageView extends AppCompatImageView {

    /**
     * 第一点
     */
    private PointF mFirst;

    /**
     * 第二点
     */
    private PointF mSecond;

    /**
     * 第三点
     */
    private PointF mThird;

    /**
     * 第四点
     */
    private PointF mEnd;

    /**
     * 区域的偏移距离
     */
    private int mOffset;

    /**
     * 标记当前的icon是属于哪个区域
     */
    private int mType;
    
    public TestImageView(@NonNull Context context) {
        super(context);
        init();
    }

    public TestImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    
    private void init() {
        mFirst = new PointF();
        mSecond = new PointF();
        mThird = new PointF();
        mEnd = new PointF();
    }
    
    public void init(int offset, int fx, int fy, int sx, int xy, int tx, int ty, int ffx, int tty) {
        this.mOffset = offset;
        if(mFirst != null) {
            mFirst.set(fx, fy);
        }
        if(mSecond != null) {
            mSecond.set(sx, xy);
        }
        if(mThird != null) {
            mThird.set(tx, ty);
        }
        if(mEnd != null) {
            mEnd.set(ffx, tty);
        }
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
    }
    
    public void firstTrans(boolean anim, int type) {
        if(anim) {
            if(type == TestFrameLayout.TYPE_LEFT) {
                trans(mFirst.x, mFirst.y, mSecond.x, mSecond.y);
            } else if(type == TestFrameLayout.TYPE_MID) {
                trans(mFirst.x, mFirst.y, mSecond.x + mOffset, mSecond.y);
            } else if(type == TestFrameLayout.TYPE_RIGHT) {
                trans(mFirst.x, mFirst.y, mSecond.x + 2 * mOffset, mSecond.y);
            }
        } else {
            setTranslationX(mSecond.x);
            setTranslationY(mSecond.y);
            setVisibility(VISIBLE);
        }
    }

    public void secondAnim() {
        trans(mSecond, mThird);
    }

    public void thirdAnim(int type) {
        if(type == TestFrameLayout.TYPE_LEFT) {
            trans(mThird.x, mThird.y, mSecond.x + mOffset, mSecond.y);
        } else if(type == TestFrameLayout.TYPE_MID) {
            trans(mThird.x, mThird.y, mSecond.x + mOffset, mSecond.y);
        } else if(type == TestFrameLayout.TYPE_RIGHT) {
            trans(mThird.x, mThird.y, mSecond.x + 2 * mOffset, mSecond.y);
        }
    }

    public void fourAnim() {
        trans(mThird, mEnd);
    }

    private void trans(float sx, float sy, float ex, float ey, Animator.AnimatorListener listener) {
        if(sx != ex || sy != ey) {
            ObjectAnimator translationX = ObjectAnimator.ofFloat(this, "translationX", sx, ex);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", sy, ey);
            final AnimatorSet animSet = new AnimatorSet();
            animSet.play(translationX).with(translationY);
            animSet.setInterpolator(new AccelerateDecelerateInterpolator());
            animSet.setDuration(500);
            if(listener != null)
                animSet.addListener(listener);
            animSet.start();
        }
    }

    private void trans(float sx, float sy, float ex, float ey) {
        if(sx != ex || sy != ey) {
            ObjectAnimator translationX = ObjectAnimator.ofFloat(this, "translationX", sx, ex);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", sy, ey);
            final AnimatorSet animSet = new AnimatorSet();
            animSet.play(translationX).with(translationY);
            animSet.setInterpolator(new AccelerateDecelerateInterpolator());
            animSet.setDuration(500);
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
    
    public void addSecondAnim(AnimatorSet set) {
        if(set != null && mSecond != null && mThird != null) {
            ObjectAnimator translationX = ObjectAnimator.ofFloat(this, "translationX", getSecondX(), mThird.x);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", mSecond.y, mThird.y);
            set.play(translationX).with(translationY);
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
    
    
}
