package com.example.customviewbase.demo.pp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;

/**
 * 
 */
public class PpActivity extends AppCompatActivity implements TestRelativeLayout.CallBack {
    
    private PpLayout plTest;
    private ImageView imgvTest;
    private TestFrameLayout flTest;
    private TestRelativeLayout rlytTest;
    
    private boolean isLayout;
    
    private Bitmap bitmap;
    private Button btnStart;
    private View vLeft;
    private View vMid;
    private View vRight;
    private View vTop;
    private Button btnAdd;
    
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            imgvTest.setImageBitmap(bitmap);
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pp);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        imgvTest = findViewById(R.id.imgv_test);
        rlytTest = findViewById(R.id.rlyt_test);
        rlytTest.setCallBack(this);
        flTest = findViewById(R.id.pl_test);
        btnStart = findViewById(R.id.btn_start);
        vLeft = findViewById(R.id.v_left);
        vMid = findViewById(R.id.v_mid);
        vRight = findViewById(R.id.v_right);
        btnAdd = findViewById(R.id.btn_add);
        vTop = findViewById(R.id.v_top);
    }

    @Override
    public void onFinishLayout() {
        isLayout = true;
        
        Rect start = new Rect(btnAdd.getLeft(), btnAdd.getTop(), btnAdd.getRight(), btnAdd.getBottom());
        Rect left = new Rect(vLeft.getLeft(), vLeft.getTop(), vLeft.getRight(), vLeft.getBottom());
        Rect mid = new Rect(vMid.getLeft(), vMid.getTop(), vMid.getRight(), vMid.getBottom());
        Rect right = new Rect(vRight.getLeft(), vRight.getTop(), vRight.getRight(), vRight.getBottom());
        Rect top = new Rect(vTop.getLeft(), vTop.getTop(), vTop.getRight(), vTop.getBottom());
        
        if(flTest != null) {
            flTest.initRect(start, left, mid, right, top);
        }
    }

    /**
     * 启动旋转
     * @param v
     */
    public void onInit(View v) {

    }
    
    public void onAddLeft(View v) {
        flTest.addCoinsAnim(TestFrameLayout.TYPE_LEFT);
//        flTest.addCoins(TestFrameLayout.TYPE_LEFT);
    }

    public void onAddMid(View v) {
        flTest.addCoinsAnim(TestFrameLayout.TYPE_MID);
    }
    
    public void onAddRight(View v) {
        flTest.addCoinsAnim(TestFrameLayout.TYPE_RIGHT);
    }

    public void onOpenLeft(View v) {
        flTest.openLeft();
    }

    public void onOpenMid(View v) {
        flTest.openMid();
    }

    public void onOpenRight(View v) {
        flTest.openRight();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


//    /**
//     * 启动旋转
//     * @param v
//     */
//    public void onInit(View v) {
//        int left = flTest.getLeft();
//
////        for(int i = 0; i < 1000; i ++) {
//            TestImageView img = new TestImageView(this);
//            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(getResources().getDimensionPixelOffset(R.dimen.pp_coin_size), getResources().getDimensionPixelOffset(R.dimen.pp_coin_size));
//            img.setBackgroundResource(R.drawable.ic_coin);
//            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
//            flTest.addView(img, params);
//            System.out.println("==============================> width: " + img.getWidth() + "  height: " + img.getHeight());
//            getStart();
//            ObjectAnimator translationX = ObjectAnimator.ofFloat(img, "translationX", 0, -toX);
//            ObjectAnimator translationY = ObjectAnimator.ofFloat(img, "translationY", 0, -toY);
////        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 0f, 1f);
//            AnimatorSet animSet = new AnimatorSet();
//            animSet.play(translationX).with(translationY);
//            animSet.setDuration(2000);
//            animSet.start();
////            try {
////                Thread.sleep(50);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
//        
//    }
//    
//    private int x;
//    private int y;
//    private int toX;
//    private int toY;
//    
//    private void getStart() {
//        int left = btnStart.getLeft();
//        int right = btnStart.getRight();
//        int top = btnStart.getTop();
//        int bottom = btnStart.getBottom();
//        x = right - left / 2;
//        y = bottom - top / 2;
//        toX = 120;
//        toY = top - 990 + 500;
//    }
//    
//    public void onAdd2(View v) {
//        int start = 5;
//        int end = 8;
//        Random random = new Random();
//        int r = end - start;
//        
//        
//        for(int i = 0; i < 20; i ++) {
//            int ro = random.nextInt(r);
//            System.out.println("==============> a" + i + " : " + (ro + start) + "  org: " + ro);
//        }
//
//        for(int i = 0; i < 20; i ++) {
//            int ro = random.nextInt(10);
//            System.out.println("==============> a" + i + " : " + (ro + start) + "  org: " + ro);
//        }
//    }
//    
//    public void onAdd3(View v) {
//
//        flTest.addCoinsAnim(TestFrameLayout.TYPE_LEFT);
//    }
//
//    public void onAdd4(View v) {
//        flTest.secondAnim(TestFrameLayout.TYPE_LEFT);
//    }
//
//    public void onAdd5(View v) {
//        flTest.thirdAnim(TestFrameLayout.TYPE_LEFT);
//    }
//
//    public void onAdd6(View v) {
//
//    }
//
//    public void onAdd7(View v) {
//        
//    }

    public void onAdd8(View v) {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(imgvTest, "translationX", 0, 500);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(imgvTest, "translationY", 0, 500);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(translationX).with(translationY);
        animSet.setDuration(2000);
        animSet.start();
    }

    public void onAdd9(View v) {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(imgvTest, "translationX", 500, 800);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(imgvTest, "translationY", 500, 800);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(translationX).with(translationY);
        animSet.setDuration(2000);
        animSet.start();
    }

//    /**
//     * 启动旋转
//     * @param v
//     */
//    public void onStart(View v) {
//        long startTime = System.currentTimeMillis(); //起始时间
//
//
//
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_test);
//                    bitmap = Bitmap.createBitmap(90, 90, Bitmap.Config.ARGB_8888);
//                    Canvas canvas = new Canvas(bitmap);
//                    for(int i = 0; i < 1; i ++) {
//                        canvas.drawBitmap(bitmap1, 0, 0, null);
//                    }
//                    imgvTest.setImageBitmap(bitmap);
////                    handler.sendEmptyMessage(1);
//                }
//            }).start();
//
////            
//
//        long endTime = System.currentTimeMillis(); //结束时间
//        long runTime = endTime - startTime;
//
//        System.out.println("==========================> runTime ms: " + runTime);
//        
//    }
    

    public int dip2px(float dpValue) {
        return dip2px(this,dpValue);
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
    
    
}