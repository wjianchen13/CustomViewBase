package com.example.customviewbase.demo.last;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.customviewbase.R;

import java.util.List;

/**
 *
 */
public class LastListAdapter2 extends RecyclerView.Adapter<LastListAdapter2.ViewHolder>{

    private List<String> mDataset;

    public LastListAdapter2(List<String> dataset) {
        super();
        mDataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = View.inflate(viewGroup.getContext(), R.layout.item_last_test, null);
//        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_last_test2, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.tvTest.setText(mDataset.get(i));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public List<String> getmDataset() {
        return mDataset;
    }

    public void setmDataset(List<String> mDataset) {
        this.mDataset = mDataset;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvTest;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTest = (TextView) itemView.findViewById(R.id.tv_test);
        }
    }
}
