package com.example.lyw.myapplication.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lyw.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LYW on 2016/11/15.
 */

public class SecondActivity extends activity {

private int[] res = {R.id.id_imagea,R.id.id_iamgeb,R.id.id_imagee,R.id.id_iamgec,
                R.id.id_imaged,R.id.id_imagee,R.id.id_imagef,R.id.id_iamgeg,R.id.id_imageh};
    private List<ImageView> imageViews = new ArrayList<>();
    private boolean flag =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        for (int i = 0;i<res.length;i++){
            ImageView imageView = (ImageView) findViewById(res[i]);
            imageView.setOnClickListener(this);
            imageViews.add(imageView);

        }
    }

    @Override
    protected int getId() {
        return R.layout.objrctdemeolayout2;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_imagea:
                if (flag) {
                    startAnima();
                }else {
                    closeAnima();
                }
                Intent intent = new Intent(SecondActivity.this, ThirdActivity
                        .class);
                startActivity(intent);
                break;
            case R.id.id_button2:
                //PropertyValuesHolder objects can be used to create
                // animations with ValueAnimator or ObjectAnimator that
                // operate on several different properties
                // in parallel.
                //优点：更加有效率、更加节省资源
                PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat
                        ("rotation", 0, 360F);
                PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat
                        ("translationX", 0, 200F);
                PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat
                        ("translationY", 0, 200F);
                ObjectAnimator.ofPropertyValuesHolder(imageView, p1, p2, p3)
                        .setDuration(1000).start();
                break;
            case R.id.id_button3:
                //组合动画

                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat
                        (imageView, "translationX", 0, 145F);

                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat
                        (imageView, "translationY", 0, 145F);

                ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat
                        (imageView, "rotation", 0, 180F);
                //有顺序的执行
//                animatorSet.playSequentially(objectAnimator1, objectAnimator2,
//                        objectAnimator3);
                animatorSet.play(objectAnimator1).with(objectAnimator2);
                animatorSet.play(objectAnimator3).after(objectAnimator2);
                animatorSet.setDuration(1000);
                animatorSet.start();


                break;
        }
    }

    private void closeAnima() {
        for (int i = res.length-1;i>= 1; i--){
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageViews
                    .get(i), "translationY",(i-1) * 150, 0);
            objectAnimator.setDuration(1000);
            objectAnimator.setStartDelay(i*300);
            objectAnimator.start();
            flag = true;
        }
    }

    private void startAnima() {
        for (int i =1;i<res.length;i++){
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageViews
                    .get(i), "translationY", 0, (i-1) * 150);
            objectAnimator.setDuration(1000);
            objectAnimator.setStartDelay(i*300);
            objectAnimator.start();
            flag = false;
        }
    }
}
