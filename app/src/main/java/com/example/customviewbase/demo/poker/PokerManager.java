package com.example.customviewbase.demo.poker;

import android.graphics.Point;
import android.graphics.Rect;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokerManager {

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
    private List<PokerItemView> mLeftViews;
    private List<PokerItemView> mMidViews;
    private List<PokerItemView> mRightViews;
    private List<PokerItemView> mViewCache;

    /**
     * 第一点
     */
    private Point mFirst;

    /**
     * 第三点
     */
    private Point mThird;

    private Point mTopPoint;

    public PokerManager() {
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
    
    public void addView(PokerItemView v, int type) {
        if(type == PokerLayout.TYPE_LEFT) {
            if(mLeftViews != null && v != null) {
                mLeftViews.add(v);
            }
        } else if(type == PokerLayout.TYPE_MID) {
            if(mMidViews != null && v != null) {
                mMidViews.add(v);
            }
        } else if(type == PokerLayout.TYPE_RIGHT) {
            if(mRightViews != null && v != null) {
                mRightViews.add(v);
            }
        }            
    }

    /**
     * 旋转随机角度
     * @return
     */
    public int createRotation() {
        if(random == null)
            random = new Random();
        int r = random.nextInt(300);
        return r - 150;
    }
    
    public int getOffset() {
        if(mLeftRect != null && mMidRect != null) {
            return mMidRect.left - mLeftRect.left;
        }
        return 0;
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
    
    public int getStartX() {
        return mStartRect.left;
    }

    public int getStartY() {
        return mStartRect.top;
    }


    /**
     * 生成左侧坐的标点
     * @param p
     */
    public void createLeft(Point p) {
        if(p != null && mLeftRect != null && mStartRect != null) {
            Point tp = new Point();
            random(tp, mLeftRect.right - mLeftRect.left - mCoinsSize, mLeftRect.bottom - mLeftRect.top - mCoinsSize);
            p.set(tp.x + mLeftRect.left, tp.y + mLeftRect.top);
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
    public PokerItemView getCache() {
        if(mViewCache != null && mViewCache.size() > 0) {
            PokerItemView v = mViewCache.get(0);
            mViewCache.remove(v);
            return v;
        }
        return null;
    }

    /**
     * 隐藏某个View，并且加入到可用缓存
     * @param v
     */
    public void setGone(PokerItemView v) {
        if(v != null && mViewCache != null) {
            v.reset();
            mViewCache.add(v);
        }
    }
    
    
    
}
