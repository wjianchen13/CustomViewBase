package com.example.customviewbase.demo.pp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.customviewbase.demo.pp.TestFrameLayout.log;

public class TestManager {

    private Random random;
    
    

    /**
     * 筹码尺寸大小
     */
    private int mCoinsSize;
    
    /**
     * 区域
     */
    private Rect mTopRect;
    private Rect mLeftRect;
    private Rect mMidRect;
    private Rect mRightRect;
    private Rect mStartRect;

    /**
     * 投注，筹码View
     */
    private List<TestImageView> mLeftViews;
    private List<TestImageView> mMidViews;
    private List<TestImageView> mRightViews;
    private List<TestImageView> mViewCache;

    /**
     * 第一点
     */
    private Point mFirst;

    /**
     * 第三点
     */
    private Point mThird;

    private Point mTopPoint;

    public TestManager() {
        this.random = new Random();
        this.mStartRect = new Rect();
        this.mLeftRect = new Rect();
        this.mMidRect = new Rect();
        this.mRightRect = new Rect();
        this.mTopRect = new Rect();
        this.mLeftViews = new ArrayList<>();
        this.mMidViews = new ArrayList<>();
        this.mRightViews = new ArrayList<>();
        this.mViewCache = new ArrayList<>();
        this.mFirst = new Point();
        this.mThird = new Point();
        this.mTopPoint = new Point();
    }
    
    public void addView(TestImageView v, int type) {
        if(type == TestFrameLayout.TYPE_LEFT) {
            if(mLeftViews != null && v != null) {
                mLeftViews.add(v);
            }
        } else if(type == TestFrameLayout.TYPE_MID) {
            if(mMidViews != null && v != null) {
                mMidViews.add(v);
            }
        } else if(type == TestFrameLayout.TYPE_RIGHT) {
            if(mRightViews != null && v != null) {
                mRightViews.add(v);
            }
        }            
    }
    
    public int getOffset() {
        if(mLeftRect != null && mMidRect != null) {
            return mMidRect.left - mLeftRect.left;
        }
        return 0;
    }

    public void secondAnim(int type) {
        if(type == TestFrameLayout.TYPE_LEFT) {
            secondAnim(mLeftViews);
        } else if(type == TestFrameLayout.TYPE_MID) {
            secondAnim(mMidViews);
        } else if(type == TestFrameLayout.TYPE_RIGHT) {
            secondAnim(mRightViews);
        }
    }
    
    private void secondAnim(final List<TestImageView> list) {
        if(list != null && list.size() > 0) {
            AnimatorSet animSet = new AnimatorSet();
            animSet.setInterpolator(new AccelerateDecelerateInterpolator());
            animSet.setDuration(500);
            animSet.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    if(list != null) {
                        for (int i = 0; i < list.size(); i++) {
                            setGone(list.get(i));
                        }
                        list.clear();

                        log("secondAnim mLeftViews size: " + mLeftViews.size());
                        log("secondAnim mViewCache size: " + mViewCache.size());
                    }
                }
            });
            for(int i = 0; i < list.size(); i ++) {
                TestImageView v = list.get(i);
                if(v != null)
                    v.addSecondAnim(animSet);
            }
            animSet.start();
        }
    }

    public void thirdAnim(int type) {
        if(type == TestFrameLayout.TYPE_LEFT) {
            thirdAnim(mLeftViews);
        } else if(type == TestFrameLayout.TYPE_MID) {
            thirdAnim(mMidViews);
        } else if(type == TestFrameLayout.TYPE_RIGHT) {
            thirdAnim(mRightViews);
        }
    }

    private void thirdAnim(List<TestImageView> list) {
        if(list != null) {
            for(int i = 0; i < list.size(); i ++) {
                TestImageView v = list.get(i);
                if(v != null)
                    v.thirdAnim(TestFrameLayout.TYPE_LEFT);
            }
        }
    }

    /**
     * coinsSize 筹码尺寸
     */
    public void init(int coinsSize) {
        this.mCoinsSize = coinsSize;
    }
    
    public void setStartRect(int left, int top, int right, int bottom) {
        if(mStartRect != null) {
            mStartRect.set(left, top, right, bottom); 
        }
    }
    
    public void setLeftRect(int left, int top, int right, int bottom) {
        if(mLeftRect != null) {
            mLeftRect.set(left, top, right, bottom);
        }
    }

    public void setMidRect(int left, int top, int right, int bottom) {
        if(mMidRect != null) {
            mMidRect.set(left, top, right, bottom);
        }
    }

    public void setRightRect(int left, int top, int right, int bottom) {
        if(mRightRect != null) {
            mRightRect.set(left, top, right, bottom);
        }
    }
    
    public void setTopRect(int left, int top, int right, int bottom) {
        if(mTopRect != null) {
            mTopRect.set(left, top, right, bottom);
            if(mTopPoint != null && mStartRect != null) {
                int y = mStartRect.bottom - mTopRect.top - mCoinsSize - (mTopRect.bottom - mTopRect.top - mCoinsSize) / 2;
                int x = mStartRect.right - mTopRect.left - mCoinsSize - (mTopRect.right - mTopRect.left - mCoinsSize) / 2;
                mTopPoint.set(-x, -y);
            }
        }
    }
    
    public Point getTopPoint() {
        return mTopPoint;
    }

    /**
     * 生成左侧坐的标点
     * @param p
     */
    public void createLeft(Point p) {
        if(p != null && mLeftRect != null && mStartRect != null) {
            Point tp = new Point();
            random(tp, mLeftRect.right - mLeftRect.left - mCoinsSize, mLeftRect.bottom - mLeftRect.top - mCoinsSize);
            p.set(-(mStartRect.right - mLeftRect.left - mCoinsSize - tp.x), -(mStartRect.bottom - mLeftRect.top - mCoinsSize - tp.y));
        }
    }

    /**
     * 生成中间的坐标点
     * @param p
     */
    public void createMid(Point p) {
        if(p != null && mMidRect != null && mStartRect != null) {
            Point tp = new Point();
            random(tp, mMidRect.right - mMidRect.left - mCoinsSize, mMidRect.bottom - mMidRect.top - mCoinsSize);
            p.set(-(mStartRect.right - mMidRect.left - mCoinsSize - tp.x), -(mStartRect.bottom - mMidRect.top - mCoinsSize - tp.y));
        }
    }

    /**
     * 生成右边的坐标点
     * @param p
     */
    public void createRight(Point p) {
        if(p != null && mRightRect != null && mStartRect != null) {
            Point tp = new Point();
            random(tp, mRightRect.right - mRightRect.left - mCoinsSize, mRightRect.bottom - mRightRect.top - mCoinsSize);
            p.set(-(mStartRect.right - mRightRect.left - mCoinsSize - tp.x), -(mStartRect.bottom - mRightRect.top - mCoinsSize - tp.y));
        }
    }

    /**
     * 随机生成相对坐标
     * @param p 保存生成结果
     * @param tx x轴范围
     * @param ty y轴范围
     */
    private void random(Point p, int tx, int ty) {
        if(p != null) {
            if(random == null)
                random = new Random();
            if(random != null) {
                int x = random.nextInt(tx);
                int y = random.nextInt(ty);
                p.set(x, y);
            }
        }
    }

    /**
     * 从缓存里面取显示的View
     * @return
     */
    public TestImageView getCache() {
        log("TestManager getCache size: " + mViewCache.size());
        if(mViewCache != null && mViewCache.size() > 0) {
            TestImageView v = mViewCache.get(0);
            mViewCache.remove(v);
            return v;
        }
        return null;
    }

    /**
     * 隐藏某个View，并且加入到可用缓存
     * @param v
     */
    public void setGone(TestImageView v) {
        if(v != null && mViewCache != null) {
            v.reset();
            mViewCache.add(v);
        }
    }
    
    
    
}
