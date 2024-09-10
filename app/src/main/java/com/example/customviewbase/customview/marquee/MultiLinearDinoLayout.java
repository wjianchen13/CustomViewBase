package com.example.customviewbase.customview.marquee;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

import androidx.annotation.CallSuper;
import androidx.core.view.ViewCompat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21 0021.
 */

public class MultiLinearDinoLayout extends HorizontalScrollView {
    
    public static final int DINO_TYPE_MULTI_LAYOUT_MESSAGE = 1; // 小跑道
    public static final int DINO_TYPE_MULTI_LAYOUT_BROADCAST = 2; // 大跑道

    private Context mContext;
    private FrameLayout dinoParent;
    private int itemDinoGap = 0;
    private int dinoLine = 1;
    private DinoAdapter dinoAdapter;
    private final List<Channel> dinoChannels = new ArrayList<>();
    private int cacheDinoCount = 2;    // textview缓存数
    private OnMultiLinearDinoListener dinoListener;
    private final List<View> dinoCache = new ArrayList<>();
    private int dinoType;
    private int itemDinoHeight;
    private boolean isDinoWorking = true;
    private boolean dinoRevers;    // 由下至上，默认由上至下
    private final int dinoSpeed = 58;

    public int dip2px(float dpValue) {
        return dip2px(mContext, dpValue);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        return (int) (dpValue * getDensity(context) + 0.5f);
    }

    /**
     * 返回屏幕密度
     */
    public float getDensity(Context context) {
        try {
            return context.getResources().getDisplayMetrics().density;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2.0f;
    }

    public void setDinoAdapter(final DinoAdapter dinoAdapter){
        this.dinoAdapter = dinoAdapter;
        if(this.dinoAdapter != null) {
            this.dinoAdapter.layout = MultiLinearDinoLayout.this;
            post(new Runnable() {
                @Override
                public void run() {
                    dinoChannels.clear();
                    for (int i = 0; i < dinoLine; i++) {
                        Channel channel = new Channel(dinoRevers ? (dinoLine - i - 1) * itemDinoHeight : i * itemDinoHeight);
                        dinoChannels.add(channel);
                    }

                    if (MultiLinearDinoLayout.this.dinoAdapter != null) {
                        removeAllViews();

                        ViewGroup.LayoutParams layoutParams = getLayoutParams();
//                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        layoutParams.height = dinoLine * itemDinoHeight;
                        requestLayout();

                        addView(dinoParent = new FrameLayout(getContext())
                                , new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                        dinoParent.setBackgroundResource(android.R.color.transparent);
                        dinoCache.clear();
                        for (int i = 0; i < cacheDinoCount; i++) {
                            dinoCache.add(MultiLinearDinoLayout.this.dinoAdapter.addCacheView(dinoParent));
                        }

                        if (MultiLinearDinoLayout.this.dinoAdapter != null && MultiLinearDinoLayout.this.dinoAdapter.hasNext()) next();
                    }
                }
            });
        }
    }

    public void notifyDatasetChanged(){
        next();
    }

    private void init(){
        itemDinoGap = dip2px(26);
        itemDinoHeight = dip2px(20);
        setFillViewport(true);
        setFadingEdgeLength(0);
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        setHorizontalFadingEdgeEnabled(false);
        setVerticalScrollBarEnabled(false);
    }

    public MultiLinearDinoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public void setDinoType(int dinoType) {
        this.dinoType = dinoType;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    private boolean allViewRest(){

        if(isDinoWorking) {
            for (int i = 0; dinoParent != null && i < dinoParent.getChildCount(); i++) {
                View v = dinoParent.getChildAt(i);
                if (v != null
                        && v.getTag() != null
                        && v.getTag() instanceof Animator)
                    return false;
            }
        }

        return true;
    }

    private Channel getBestChannel(){
        Channel c = null;
        for (Channel channel : dinoChannels){
            if(channel.isEmpty())
                return channel;

            if(channel.isCanShowNext()){
                if(c == null
                        || channel.leftSpace() > c.leftSpace())
                    c = channel;
            }
        }
        return c;
    }

    public MultiLinearDinoLayout(Context context) {
        super(context);
        init();
    }

    public MultiLinearDinoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private View getView(){

//        SuniLog.iTag("cachesizelog", "cache size is" + mCache.size());

        if(!dinoCache.isEmpty()) {
            View v = dinoCache.get(0);
            dinoCache.remove(v);
            return v;
        }

        return dinoAdapter.addCacheView(dinoParent);
    }

    private void next(){

        if(!isDinoWorking)
            return;

        // 选一个最合理的渠道开始动画，并添加到该渠道列表
        if(dinoAdapter != null && dinoAdapter.hasNext()){
            Channel channel = getBestChannel();
            if(channel != null){

                if(isEmpty()){
                    // 开始
                    if(dinoListener != null)
                        dinoListener.start();
                }

                View v = getView();
                ChannelDinoItem item = new ChannelDinoItem(channel, v);
                item.startAnim();
            }
        } else {
            // 全部播放完毕
            if(isEmpty() && dinoListener != null)
                dinoListener.end();
        }
    }

    private boolean isEmpty(){
        for (Channel channel : dinoChannels){
            if(!channel.isEmpty())
                return false;
        }
        return true;
    }

    public void setDinoLine(int mLine) {
        this.dinoLine = mLine;
    }

    public void setDinoRevers(boolean mRevers) {
        this.dinoRevers = mRevers;
    }

    public boolean isDinoRevers() {
        return dinoRevers;
    }

    public int getItemDinoHeight() {
        return itemDinoHeight;
    }

    public int getCacheDinoCount() {
        return cacheDinoCount;
    }

    public void setCacheDinoCount(int mCacheCount) {
        this.cacheDinoCount = mCacheCount;
    }

    public int getItemDinoGap() {
        return itemDinoGap;
    }

    public void clearCurrent(){
        isDinoWorking = false;

        for (Channel channel : dinoChannels){
            for (ChannelDinoItem item : channel.dinoItems){
                item.clear(true);
            }
            channel.dinoItems.clear();
        }

        if(dinoCache != null
                && !dinoCache.isEmpty())
            dinoCache.clear();

        if(dinoListener != null)
            dinoListener.end();

        isDinoWorking = true;
    }

    private int visibleWidth(){
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clear();
    }

    public boolean isPlaying(){
        return !allViewRest();
    }

    private long getTime(long time) {
        return time;
    }

    public void clear(){
        isDinoWorking = false;
        if(dinoAdapter != null){
            dinoAdapter.clear();
        }
        for (Channel channel : dinoChannels){
            for (ChannelDinoItem item : channel.dinoItems){
                item.clear(true);
            }
        }
        dinoChannels.clear();
        dinoCache.clear();
    }

    public int getDinoLine() {
        return dinoLine;
    }

    public void setItemDinoGap(int gap) {
        this.itemDinoGap = gap;
    }

    public void setItemDinoHeight(int heigh) {
        this.itemDinoHeight = heigh;
    }

    public void setOnMultiLinearListener(OnMultiLinearDinoListener listener){
        this.dinoListener = listener;
    }

    public static abstract class DinoAdapter<T> {

        @CallSuper public void clear(){

        }
        public MultiLinearDinoLayout layout;

        protected boolean isWorking(){
            return layout != null && layout.isDinoWorking;
        }
        public abstract boolean hasNext();

        public abstract T getNext();
        public abstract View addCacheView(ViewGroup parent);

        public void notifyDatasetChanged(){
            if(layout != null)
                layout.notifyDatasetChanged();
        }

        public void clearCurrent(){
            if(layout != null)
                layout.clearCurrent();
        }
        public abstract void setItemContent(T bean, View container);
        public abstract int size();
    }

    private class ChannelDinoItem {
        boolean updateDinoNotify;
        boolean iAmDinoDie;
        View dinoView;
        Channel myDinoChannel;
        ValueAnimator dinoAnimator;

        public float leftSpace(){
            return visibleWidth() - (dinoView.getX() + dinoView.getWidth());
        }

        public void clear(boolean fromKill){

            iAmDinoDie = fromKill;

            if(dinoAnimator != null)
                dinoAnimator.cancel();

            clearView(dinoView);
            dinoView.setTag(null);
            dinoView.setVisibility(View.GONE);
            dinoView.setX(visibleWidth());
            if(!iAmDinoDie)
                myDinoChannel.remove(this);
            dinoCache.add(dinoView);
        }

        public void clear(){
            clear(iAmDinoDie);
        }

        public ChannelDinoItem(Channel myDinoChannel, View dinoView){
            this.myDinoChannel = myDinoChannel;
            this.dinoView = dinoView;
            myDinoChannel.add(this);
        }

        public void startAnim(){
            if(dinoAnimator == null) {

                dinoAdapter.setItemContent(dinoAdapter.getNext(), dinoView);
                long time = Float.valueOf(((visibleWidth() + dinoView.getWidth()) / (float) dinoSpeed) * 1000).longValue();

                int parentWidth = dinoParent.getWidth();
                int viewWidth = dinoView.getWidth();

//                SuniLog.iTag("cachesizelog", parentWidth + " MeasuredWidth:" + mParent.getMeasuredWidth() + " " + viewWidth + " MeasuredWidth:" + view.getMeasuredWidth() + " " + time);
//                SuniLog.iTag("cachesizelog", " " + getWidth() + " MeasuredWidth:" + getMeasuredWidth());

                dinoAnimator = ValueAnimator.ofFloat(visibleWidth(), -dinoView.getWidth());
                dinoAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        dinoView.setX(value);
                        if(!updateDinoNotify && isCanShowNext()){
                            next();
                            updateDinoNotify = true;
                        }
                    }
                });

                dinoAnimator.setDuration(getTime(time));
                dinoAnimator.setTarget(dinoView);
                dinoAnimator.setInterpolator(new LinearInterpolator());
                dinoAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        clear();
                        if(dinoListener != null)
                            dinoListener.onAnimEnd(dinoView, dinoAnimator);
                        next();
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {
                        dinoView.setTag(dinoAnimator);
                        dinoView.setVisibility(View.VISIBLE);
                        dinoView.setY(myDinoChannel.offsetDinoY);
                        if(dinoListener != null)
                            dinoListener.onAnimStart(dinoView, dinoAnimator);
                    }
                });
                dinoAnimator.start();
            }
        }

        public boolean isCanShowNext(){
            return leftSpace() >= itemDinoGap;
        }

    }

    public static abstract class OnMultiLinearDinoListener {
        public void onAnimStart(View view, Animator animator){}
        public void start(){}
        public void end(){}
        public void onAnimEnd(View view, Animator animator){}
    }

    private class Channel{
        LinkedList<ChannelDinoItem> dinoItems = new LinkedList<>();
        int offsetDinoY;

        public void add(ChannelDinoItem item){
            dinoItems.add(item);
        }

        public float leftSpace(){
            View lastView = lastView();
            if(lastView == null)
                return 0;

            return visibleWidth() - (lastView.getX() + lastView.getWidth());
        }

        public void remove(ChannelDinoItem item){
            dinoItems.remove(item);
        }

        public View lastView(){
            if(!isEmpty()){
                return dinoItems.getLast().dinoView;
            }
            return null;
        }

        public boolean isCanShowNext(){
            return leftSpace() >= itemDinoGap;
        }

        public Channel(int offsetDinoY){
            this.offsetDinoY = offsetDinoY;
        }

        public boolean isEmpty(){
            return dinoItems.isEmpty();
        }
    }

    public static void clearView(View v) {

        if (v == null)
            return;

        ViewCompat.setAlpha(v, 1);
        ViewCompat.setScaleY(v, 1);
        ViewCompat.setScaleX(v, 1);
        ViewCompat.setTranslationY(v, 0);
        ViewCompat.setTranslationX(v, 0);
        ViewCompat.setRotation(v, 0);
        ViewCompat.setRotationY(v, 0);
        ViewCompat.setRotationX(v, 0);
        ViewCompat.setPivotY(v, v.getMeasuredHeight() / 2);
        ViewCompat.setPivotX(v, v.getMeasuredWidth() / 2);
        ViewCompat.animate(v).setInterpolator(null).setStartDelay(0);
        v.clearAnimation();
    }
}
