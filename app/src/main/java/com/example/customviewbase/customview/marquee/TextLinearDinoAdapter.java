package com.example.customviewbase.customview.marquee;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.customviewbase.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21 0021.
 */

public class TextLinearDinoAdapter extends MultiLinearDinoLayout.DinoAdapter<TextLinearDinoAdapter.TextLinearParseDinoResult> {

    protected Context dinoContext;

    protected TextLinearDataManager mDataManager;
    private boolean isLoopMessage;
    
    public void add(TextLinearParseDinoResult m){
        // 为防止容器过多，到达一定数量后不再添加
        if(isWorking()
                && m != null) {
            if(mDataManager != null)
                mDataManager.insertItem(m);

            notifyDatasetChanged();
        }
    }

    @Override
    public void setItemContent(final TextLinearParseDinoResult result, View container) {
        if(result != null) {
            LinearLayout llyt = (LinearLayout) container;
            TextView tv = llyt.findViewById(R.id.tv_test);
            tv.setText(result.content);
//            llyt.setOnClickListener(null);
//
//            if(result.dinoJumper != null){
//                if (dinoContext instanceof LiveDinoActivity) {
//                    llyt.setOnClickListener(v -> ((LiveDinoActivity<?, ?>) dinoContext).showDinoChatDialog(result.dinoJumper));
//                }
//            }

            llyt.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                    , View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            llyt.layout(0, 0, llyt.getMeasuredWidth(), llyt.getMeasuredHeight());
        }
    }

    public void clear(){
        super.clear();
        if(mDataManager != null)
            mDataManager.clear();
    }

    @Override
    public View addCacheView(ViewGroup parent) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(dinoContext).inflate(R.layout.item_marquee_test, null);
        layout.setVisibility(View.GONE);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dinoContext.getResources().getDimensionPixelOffset(R.dimen.dino_distance_28dp));
        parent.addView(layout, layoutParams);


//        final TextView textView =  new TextView(dinoContext);
//        textView.setBackgroundResource(android.R.color.transparent);
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getTextSize());
//        textView.setTextColor(ContextCompat.getColor(DinoApp.app(), ThemeDinoController.getBroadcastDefaultTxtColor(dinoContext
//                , dinoContext instanceof LiveDinoActivity && ((LiveDinoActivity<?, ?>) dinoContext).vm().isPhoneUI())));
//        textView.setSingleLine(true);
//        textView.setGravity(Gravity.CENTER_VERTICAL);
//        textView.setVisibility(View.GONE);
//        textView.setPadding(DinoScreenUtils.dip2px(5), 0, DinoScreenUtils.dip2px(5), 0);
////        textView.setBackgroundColor(ContextCompat.getColor(DinoApp.app(), R.color.dino_c615d93));
//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
//                , DinoApp.app().getResources().getDimensionPixelOffset(R.dimen.dino_distance_20dp));
//        parent.addView(textView, layoutParams);

        return layout;
    }

    @Override
    public int size() {
        return mDataManager != null ? mDataManager.size() : 0;
    }

    @Override
    public boolean hasNext() {
//        return !dinoQueue.isEmpty();
        return mDataManager != null && !mDataManager.isEmpty();
    }

    public TextLinearDinoAdapter(Context dinoContext){
        this.dinoContext = dinoContext;
        this.isLoopMessage = true;
        this.mDataManager = new TextLinearDataManager(isLoopMessage);
    }

    public static class TextLinearParseDinoResult {
        public int resId;

        public String content;
        /**
         * 是否已经显示过了
         * 1:没有
         * 0:显示过了
         */
        private int show;
        
        public TextLinearParseDinoResult(int resId,  String content){
            this.resId = resId;
            this.content = content;
        }

        public boolean isShow() {
            return show == 0;
        }

        public void setShow(int show) {
            this.show = show;
        }
    }

    @Override
    public void clearCurrent() {
        super.clearCurrent();
        if(mDataManager != null) {
            mDataManager.clearCurrent();
        }
    }

    @Override
    public TextLinearParseDinoResult getNext() {
        return mDataManager != null ? mDataManager.getItem() : null;
    }


}
