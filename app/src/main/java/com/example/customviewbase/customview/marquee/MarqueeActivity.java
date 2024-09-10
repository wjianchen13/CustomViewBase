package com.example.customviewbase.customview.marquee;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.customviewbase.R;

/**
 * 跑马灯
 */
public class MarqueeActivity extends AppCompatActivity {

    protected MaskLinearDinoAdapter dinoMaskAdapter;
    protected MultiLinearDinoLayout layoutDinoMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        layoutDinoMessage = findViewById(R.id.message);
        init();
    }

    private void init() {
        layoutDinoMessage.setDinoLine(1);
        layoutDinoMessage.setItemDinoGap(getResources().getDimensionPixelOffset(R.dimen.dino_distance_30dp));
        layoutDinoMessage.setCacheDinoCount(3);
        layoutDinoMessage.setItemDinoHeight(getResources().getDimensionPixelOffset(R.dimen.dino_distance_28dp));
        layoutDinoMessage.setDinoRevers(false);
        layoutDinoMessage.setBackgroundResource(R.color.dino_c66201d3e);
        dinoMaskAdapter = new MaskLinearDinoAdapter(this);
        layoutDinoMessage.setDinoAdapter(dinoMaskAdapter);
        layoutDinoMessage.setDinoType(MultiLinearDinoLayout.DINO_TYPE_MULTI_LAYOUT_MESSAGE);
        layoutDinoMessage.setOnMultiLinearListener(new MultiLinearDinoLayout.OnMultiLinearDinoListener() {
            @Override
            public void start() {
                layoutDinoMessage.setVisibility(View.VISIBLE);
            }

            @Override
            public void end() {
                layoutDinoMessage.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void onTest1(View view) {
        TextLinearDinoAdapter.TextLinearParseDinoResult result = new TextLinearDinoAdapter.TextLinearParseDinoResult(0, "marquee test1");
        addShadeDinoBroadcast(result);
    }

    public void onTest2(View view) {
        TextLinearDinoAdapter.TextLinearParseDinoResult result = new TextLinearDinoAdapter.TextLinearParseDinoResult(0, "marquee test2");
        addShadeDinoBroadcast(result);
    }

    public void onTest3(View view) {
        TextLinearDinoAdapter.TextLinearParseDinoResult result = new TextLinearDinoAdapter.TextLinearParseDinoResult(0, "marquee test3");
        addShadeDinoBroadcast(result);
    }

    public void addShadeDinoBroadcast(TextLinearDinoAdapter.TextLinearParseDinoResult sysBSBean) {
        if (dinoMaskAdapter != null)
            dinoMaskAdapter.add(sysBSBean);
    }
}