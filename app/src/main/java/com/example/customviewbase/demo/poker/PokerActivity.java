package com.example.customviewbase.demo.poker;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;
import com.example.customviewbase.demo.pp.TestFrameLayout;
import com.example.customviewbase.demo.pp.TestRelativeLayout;

/**
 * 
 */
public class PokerActivity extends AppCompatActivity implements TestRelativeLayout.CallBack {
    
    private TestRelativeLayout trl;
    private PokerLayout pl_test;

    private View vLeft;
    private View vMid;
    private View vRight;
    private Button btnAdd;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poker);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        trl = findViewById(R.id.rlyt_test);
        trl.setCallBack(this);
        pl_test = findViewById(R.id.pl_test);
        vLeft = findViewById(R.id.v_left);
        vMid = findViewById(R.id.v_mid);
        vRight = findViewById(R.id.v_right);
        btnAdd = findViewById(R.id.btn_add);
    }

    boolean isLayout = false;
    
    @Override
    public void onFinishLayout() {
        if (!isLayout) {
            Rect parent = new Rect(trl.getLeft(), trl.getTop(), trl.getRight(), trl.getBottom());
            Rect start = new Rect(btnAdd.getLeft(), btnAdd.getTop(), btnAdd.getRight(), btnAdd.getBottom());
            Rect left = new Rect(vLeft.getLeft(), vLeft.getTop(), vLeft.getRight(), vLeft.getBottom());
            Rect mid = new Rect(vMid.getLeft(), vMid.getTop(), vMid.getRight(), vMid.getBottom());
            Rect right = new Rect(vRight.getLeft(), vRight.getTop(), vRight.getRight(), vRight.getBottom());
    
            pl_test.setInfo(parent, start, left, mid, right);
            isLayout = true;
        }
    }

    /**
     * 清除显示
     * @param v
     */
    public void onTest1(View v) {
        pl_test.transport();
    }

    /**
     * 刷新
     * @param v
     */
    public void onTest2(View v) {
        pl_test.invalidate1();
    }

    /**
     * 启动旋转
     * @param v
     */
    public void onTest3(View v) {

    }
    
    public void onAddLeft(View v) {
        pl_test.addCoins(TestFrameLayout.TYPE_LEFT);
    }

    public void onAddMid(View v) {
        pl_test.addCoins(TestFrameLayout.TYPE_MID);
    }
    
    public void onAddRight(View v) {
        pl_test.addCoins(TestFrameLayout.TYPE_RIGHT);
    }

    public void onOpenLeft(View v) {
        pl_test.addCoinsAnim(TestFrameLayout.TYPE_LEFT);
    }

    public void onOpenMid(View v) {
        pl_test.addCoinsAnim(TestFrameLayout.TYPE_MID);
    }

    public void onOpenRight(View v) {
        pl_test.addCoinsAnim(TestFrameLayout.TYPE_RIGHT);
    }
    
    
    
}