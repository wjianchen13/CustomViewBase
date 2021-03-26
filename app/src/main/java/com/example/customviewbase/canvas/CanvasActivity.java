package com.example.customviewbase.canvas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;
import com.example.customviewbase.canvas.drawText.DrawTextActivity;
import com.example.customviewbase.canvas.drawarc.ArcActivity;
import com.example.customviewbase.canvas.drawbitmap.BitmapActivity;
import com.example.customviewbase.canvas.drawcircle.CircleActivity;
import com.example.customviewbase.canvas.drawcolor.ColorActivity;
import com.example.customviewbase.canvas.drawline.LineActivity;
import com.example.customviewbase.canvas.drawoval.OvalActivity;
import com.example.customviewbase.canvas.drawpoint.PointActivity;
import com.example.customviewbase.canvas.drawrect.RectActivity;
import com.example.customviewbase.canvas.drawroundrect.RoundRectActivity;
import com.example.customviewbase.canvas.paint.PaintActivity;
import com.example.customviewbase.canvas.rotate.RotateActivity;
import com.example.customviewbase.canvas.scale.ScaleActivity;
import com.example.customviewbase.canvas.translate.TranslateActivity;
import com.example.customviewbase.customview.CustomViewActivity;

/**
 * https://www.gcssloop.com/customview/CustomViewProcess
 */
public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
    }

    /**
     * 绘制颜色
     * @param
     * @return
     */
    public void onColor(View v) {
        Intent it = new Intent();
        it.setClass(CanvasActivity.this, ColorActivity.class);
        startActivity(it);
    }

    /**
     * 绘制点
     * @param
     * @return
     */
    public void onPoint(View v) {
        Intent it = new Intent();
        it.setClass(CanvasActivity.this, PointActivity.class);
        startActivity(it);
    }

    /**
     * 绘制直线
     * @param
     * @return
     */
    public void onLine(View v) {
        Intent it = new Intent();
        it.setClass(CanvasActivity.this, LineActivity.class);
        startActivity(it);
    }

    /**
     * 绘制矩形
     * @param
     * @return
     */
    public void onRect(View v) {
        Intent it = new Intent();
        it.setClass(CanvasActivity.this, RectActivity.class);
        startActivity(it);
    }

    /**
     * 绘制圆角矩形
     * @param
     * @return
     */
    public void onRoundRect(View v) {
        Intent it = new Intent();
        it.setClass(CanvasActivity.this, RoundRectActivity.class);
        startActivity(it);
    }

    /**
     * 绘制椭圆
     * @param
     * @return
     */
    public void onOval(View v) {
        Intent it = new Intent();
        it.setClass(CanvasActivity.this, OvalActivity.class);
        startActivity(it);
    }

    /**
     * 绘制圆
     * @param
     * @return
     */
    public void onCircle(View v) {
        Intent it = new Intent();
        it.setClass(CanvasActivity.this, CircleActivity.class);
        startActivity(it);
    }

    /**
     * 绘制圆弧
     * @param
     * @return
     */
    public void onArc(View v) {
        Intent it = new Intent();
        it.setClass(CanvasActivity.this, ArcActivity.class);
        startActivity(it);
    }

    /**
     * 画笔设置
     * @param
     * @return
     */
    public void onPaint(View v) {
        Intent it = new Intent();
        it.setClass(CanvasActivity.this, PaintActivity.class);
        startActivity(it);
    }

    /**
     * 画布平移
     * @param
     * @return
     */
    public void onTranslate(View v) {
        Intent it = new Intent();
        it.setClass(CanvasActivity.this, TranslateActivity.class);
        startActivity(it);
    }

    /**
     * 画布缩放
     * @param
     * @return
     */
    public void onScale(View v) {
        Intent it = new Intent();
        it.setClass(CanvasActivity.this, ScaleActivity.class);
        startActivity(it);
    }

    /**
     * 画布旋转
     * @param
     * @return
     */
    public void onRotate(View v) {
        Intent it = new Intent();
        it.setClass(CanvasActivity.this, RotateActivity.class);
        startActivity(it);
    }
    
    /**
     * 文字
     * @param
     * @return
     */
    public void onText(View v) {
        Intent it = new Intent();
        it.setClass(CanvasActivity.this, DrawTextActivity.class);
        startActivity(it);
    }

    /**
     * 绘制Bitmap
     * @param
     * @return
     */
    public void onBitmap(View v) {
        Intent it = new Intent();
        it.setClass(CanvasActivity.this, BitmapActivity.class);
        startActivity(it);
    }
    
}