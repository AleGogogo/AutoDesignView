package com.example.lyw.myapplication.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;

import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lyw.myapplication.R;

public class MainActivity extends activity {


    @Override
    protected void initListener() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        imageView = (ImageView) findViewById(R.id.id_iamge1);
        button1 = (Button) findViewById(R.id.id_button1);
        button2 = (Button) findViewById(R.id.id_button2);
        button3 = (Button) findViewById(R.id.id_button3);
    }

    @Override
    protected int getId() {
        return R.layout.objrctdemeolayout;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_button1:
                //属性动画调用start方法是异步进行的，所以这三个动作可以同时进行
                ObjectAnimator.ofFloat(imageView, "translationX", 0, 145F)
                        .setDuration(1000).start();
                ObjectAnimator.ofFloat(imageView, "translationY", 0, 145F)
                        .setDuration(1000).start();
                ObjectAnimator.ofFloat(imageView, "rotation", 0, 180F)
                        .setDuration(1000).start();
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

                Intent intent = new Intent(MainActivity.this, SecondActivity
                        .class);
                startActivity(intent);

                break;
        }
    }
}
