package com.example.lyw.myapplication.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lyw.myapplication.R;

import static android.view.Gravity.CENTER;

/**
 * Created by LYW on 2016/11/14.
 */

public class topBar extends RelativeLayout{
     private TextView titleTextView;
     private Button leftButton,rightButton;
     private int leftColor;
     private int rightColor;
     //private int titleColor;
     private Drawable leftDrawble;
     private Drawable rightDrawble;
    private float titleTextSize;
    private String leftText;
    private String rightText;
    private String titleText;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public topBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.topBar);
        leftColor = array.getColor(R.styleable.topBar_leftColor,0);
        leftDrawble = array.getDrawable(R.styleable.topBar_leftBackground);
        rightColor = array.getColor(R.styleable.topBar_rightColor,0);
        rightDrawble = array.getDrawable(R.styleable.topBar_rightBackground);
        titleTextSize = array.getDimension(R.styleable.topBar_titleTextSize,0);
        leftText = array.getString(R.styleable.topBar_leftText);
        rightText = array.getString(R.styleable.topBar_rightText);
        titleText = array.getString(R.styleable.topBar_titleText);
//        titleColor =array.getColor(R.styleable.topBar_titleTextColor,0);
        array.recycle();

        setBackgroundColor(Color.parseColor("#FFDEAD"));
        leftButton = new Button(context);
        leftButton.setText(leftText);
        leftButton.setTextColor(leftColor);
        leftButton.setBackground(leftDrawble);
        RelativeLayout.LayoutParams params1 = new LayoutParams(ViewGroup
                .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.addRule(ALIGN_PARENT_LEFT,TRUE);
        addView(leftButton,params1);
        rightButton = new Button(context);
        rightButton.setText(rightText);
        rightButton.setTextColor(rightColor);
        rightButton.setBackground(rightDrawble);
        RelativeLayout.LayoutParams params2 = new LayoutParams(ViewGroup
                .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.addRule(ALIGN_PARENT_RIGHT,TRUE);
        addView(rightButton,params2);

        titleTextView = new Button(context);
        titleTextView.setText(titleText);
      //
        titleTextView.setTextSize(titleTextSize);
        RelativeLayout.LayoutParams params3 = new LayoutParams(ViewGroup
                .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params3.addRule(CENTER_HORIZONTAL,TRUE);
        addView(titleTextView,params3);
    }
}
