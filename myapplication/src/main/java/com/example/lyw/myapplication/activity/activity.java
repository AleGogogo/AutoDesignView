package com.example.lyw.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static android.R.attr.id;

/**
 * Created by LYW on 2016/11/15.
 */

public abstract class activity extends Activity implements View.OnClickListener{
    public ImageView imageView;
    public Button button1;
    public Button button2;
    public Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getId());
        initView();
        initListener();
    }
    protected abstract void initListener();

    protected abstract void initView() ;

    protected  abstract int getId();
}
