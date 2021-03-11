package com.example.customviewbase.customview.viewgrouptest.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * name: CustomViewGroup
 * desc: 自定义ViewGroup基本绘制过程
 * author:
 * date: 2018-01-26 11:00
 * remark:
 */
public class CustomViewGroup extends ViewGroup {

	private static final String TAG = "CustomViewGroup";

	public CustomViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomViewGroup(Context context)
	{
		super(context);
	}

	public CustomViewGroup(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	/**
	 * 计算所有ChildView的宽度和高度 然后根据ChildView的计算结果，设置自己的宽和高
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		/**
		 * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
		 */
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

		Log.e(TAG, "==================================================================================================> start");
		printMode(widthMeasureSpec, heightMeasureSpec);
		Log.e(TAG, "===========================> specSize_width = " + sizeWidth + ", specSize_height = " + sizeHeight);

		// 计算出所有的childView的宽和高
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		int cCount = getChildCount();
		int cWidth = 0;
		int cHeight = 0;
		MarginLayoutParams cParams = null;

		int height = 0;
		int width = 0;

		/**
		 * 根据childView计算的出的宽和高，以及设置的margin计算容器的宽和高，主要用于容器是warp_content时
		 */
		for (int i = 0; i < cCount; i++) {
			View childView = getChildAt(i);
			cWidth = childView.getMeasuredWidth();
			cHeight = childView.getMeasuredHeight();
			cParams = (MarginLayoutParams) childView.getLayoutParams();
			width += cWidth + cParams.leftMargin + cParams.rightMargin;
			height += cHeight + cParams.topMargin + cParams.bottomMargin;
		}

		/**
		 * 如果是wrap_content设置为我们计算的值
		 * 否则：直接设置为父容器计算的值
		 */
		setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth
				: width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
				: height);
	}

	private void printMode(int widthMeasureSpec, int heightMeasureSpec) {
		String specMode_width = "";
		int specModeWidth = MeasureSpec.getMode(widthMeasureSpec);
		switch (specModeWidth) {
			case MeasureSpec.EXACTLY:
				specMode_width = "EXACTLY";
				break;
			case MeasureSpec.AT_MOST:
				specMode_width = "AT_MOST";
				break;
			case MeasureSpec.UNSPECIFIED:
				specMode_width = "UNSPECIFIED";
				break;
		}
		//高度度
		String specMode_height = "";
		int specModeHeight = MeasureSpec.getMode(heightMeasureSpec);
		switch (specModeHeight) {
			case MeasureSpec.UNSPECIFIED:
				specMode_height = "UNSPECIFIED";
				break;
			case MeasureSpec.AT_MOST:
				specMode_height = "AT_MOST";
				break;
			case MeasureSpec.EXACTLY:
				specMode_height = "EXACTLY";
				break;
		}
		Log.e(TAG, "===========================> specMode_width = " + specMode_width + " , specMode_height = " + specMode_height);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int cCount = getChildCount();
		int cWidth = 0;
		int cHeight = 0;
		MarginLayoutParams cParams = null;
		for (int i = 0; i < cCount; i++) {
			View childView = getChildAt(i);
			cWidth = childView.getMeasuredWidth();
			cHeight = childView.getMeasuredHeight();
			cParams = (MarginLayoutParams) childView.getLayoutParams();

			int cl = 0, ct = 0, cr = 0, cb = 0;
			cl = cParams.leftMargin;
			ct = cParams.topMargin;
			cr = cl + cWidth;
			cb = cHeight + ct;
			childView.layout(cl, ct, cr, cb);
		}
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}

	@Override
	protected LayoutParams generateDefaultLayoutParams() {
		Log.e(TAG, "generateDefaultLayoutParams");
		return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
	}

	@Override
	protected LayoutParams generateLayoutParams(LayoutParams p) {
		Log.e(TAG, "generateLayoutParams p");
		return new MarginLayoutParams(p);
	}

	@Override
	public void addView(View child, int index, LayoutParams params) {
		super.addView(child, index, params);

	}

}
