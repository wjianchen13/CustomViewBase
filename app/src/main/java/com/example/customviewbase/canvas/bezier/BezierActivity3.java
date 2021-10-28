package com.example.customviewbase.canvas.bezier;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;

public class BezierActivity3 extends AppCompatActivity {

    private RelativeLayout imgvTest;
    
    float startX = 500;
    float startY = 100;

    float endX = 100;
    float endY = 600;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier3);
        imgvTest = findViewById(R.id.imgv_test);
//        RelativeLayout
    }
    
    public void onTest(View v) {
        // 这个不行，不知道为什么
//        ObjectAnimator moveDinoAnimator = ObjectAnimator.ofObject(imgvTest, "mPointF",
//                new SplitPkToolsBezierDinoEvaluator(new PointF(300, 300)),
//                new PointF(startX, startY),
//                new PointF(endX, endY));
////        moveDinoAnimator.setStartDelay(160);
//        moveDinoAnimator.setDuration(2000);
//        moveDinoAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
////                startRingAnim();
//            }
//        });
//        moveDinoAnimator.start();
        
        
        PointF controlPoint = new PointF((endX + startX) / 2, startY + 15);
        PointF mStartPoint = new PointF(startX, startY);
        PointF mEndPoint = new PointF(endX, endY);
        

        ValueAnimator translation = ValueAnimator.ofObject(new BezierEvaluator(controlPoint), mStartPoint, mEndPoint);
        translation.setDuration(320);
        translation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF)animation.getAnimatedValue();
                imgvTest.setX(pointF.x);
                imgvTest.setY(pointF.y);
            }
        });

        // 放大
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imgvTest, "scaleX", 1.2f, 1.0f, 0.5f).setDuration(320);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imgvTest, "scaleY", 1.2f, 1.0f, 0.5f).setDuration(320);
        // 透明度动画
        ObjectAnimator alpha1 = ObjectAnimator.ofFloat(imgvTest, "alpha", 0.0f, 1f, 0.0f).setDuration(320);
        // 步骤2：创建组合动画的对象
        AnimatorSet animSet = new AnimatorSet();

        // 步骤3：根据需求组合动画
        animSet.play(translation).with(scaleX).with(scaleY).with(alpha1);
        animSet.start();
        
        
//        translation.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation, boolean isReverse) {
//                imgvTest.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation, boolean isReverse) {
//                imgvTest.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                imgvTest.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                imgvTest.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });

    }
    
}
