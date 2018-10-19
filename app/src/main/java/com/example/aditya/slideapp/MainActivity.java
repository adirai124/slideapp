package com.example.aditya.slideapp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ViewPager mslideView;
    private LinearLayout mDot;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private Button mBack;
    private Button mNext;
    private int mCurPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mslideView = (ViewPager) findViewById(R.id.slideView);
        mDot = (LinearLayout) findViewById(R.id.dots);
        mBack = (Button)findViewById(R.id.pre);
        mNext = (Button)findViewById(R.id.next);

        sliderAdapter = new SliderAdapter(this);
        mslideView.setAdapter(sliderAdapter);
        addDots(0);
        mslideView.addOnPageChangeListener(viewListener);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mslideView.setCurrentItem(mCurPage+1);
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mslideView.setCurrentItem(mCurPage-1);
            }
        });

    }
    public void addDots(int pos){
        mDots = new TextView[4];
        mDot.removeAllViews();
        for(int i = 0; i<mDots.length;i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentwhite));
            mDot.addView(mDots[i]);
        }
        if (mDots.length>0){
            mDots[pos].setTextColor(getResources().getColor(R.color.colorRed));
        }

    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDots(i);
            mCurPage = i;
            if (i == 0) {
                mNext.setEnabled(true);
                mBack.setEnabled(false);
                mBack.setVisibility(View.INVISIBLE);
                mNext.setText("Next");
                mBack.setText("");
            } else if (i == mDots.length -1 ) {
                mNext.setEnabled(true);
                mBack.setEnabled(true);
                mBack.setVisibility(View.VISIBLE);
                mNext.setVisibility(View.INVISIBLE);
                mNext.setText("");
                mBack.setText("Back");
            }else {
                mNext.setEnabled(true);
                mBack.setEnabled(true);
                mBack.setVisibility(View.VISIBLE);
                mNext.setVisibility(View.VISIBLE);
                mNext.setText("Next");
                mBack.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
