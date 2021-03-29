package com.example.customviewbase.demo.pan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.customviewbase.R;
import com.example.customviewbase.glide.GlideApp;

import java.util.List;

public class PanAdapter implements Adapter {
    
    View V3 = null;

    private String url = "https://timg.kiwii.tv/upload/anchor_image/345X257/99/4029899_54acf4035e058.jpg";

    /**
     * 需要显示的View数据列表
     */
    private List<PanItem> mData;
    private Context mContext;

    public PanAdapter(Context context, List<PanItem> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }
    
    @Override
    public Object getItem(int position) {
        return mData != null ? mData.get(position) : null;
    }
    
    @Override
    public long getItemId(int position) {
        return mData != null ? mData.get(position).getId() : -1;
    }
    
    @Override
    public View getView(int position) {
        if(mContext != null) {
            RelativeLayout view = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.item_pan, null);
            PanLayoutParams lp = new PanLayoutParams(Utils.dip2px(mContext, 60), Utils.dip2px(mContext, 150));
            lp.position = PanLayoutParams.POSITION_CENTERHORIZONTAL;
            view.setLayoutParams(lp);
            TextView tvTest = view.findViewById(R.id.tv_test);
            tvTest.setText(mData.get(position).getName());
            ImageView imgvTest = view.findViewById(R.id.imgv_test);
            GlideApp.with(mContext)
                    .load(url)
                    .circleCrop()
                    .into(imgvTest);
            V3 = view;
            return view;
        }
        return null;
    }
    
    public void set() {
        if(V3 != null) {
            V3.findViewById(R.id.imgv_test).setBackgroundResource(R.drawable.ic_test);
        }
    }
    
}
