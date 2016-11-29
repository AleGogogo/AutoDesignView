package com.example.lyw.myapplication.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.lyw.myapplication.R;
import com.example.lyw.myapplication.utill.ImageHelper;

/**
 * Created by LYW on 2016/11/16.
 */

public class ThirdActivity extends Activity implements SeekBar.OnSeekBarChangeListener{

    private ImageView imageView;
    private SeekBar mSeerBar_lum;
    private SeekBar mSeerBar_saturation;
    private SeekBar mSeerBar_hue;
    private static final int MAX_VALUE = 255;
    private static final int MIN_VALUE = 127;
    private Bitmap mBitmap;
    private float mhue,mlum,msaturation;
    private static final String TAG = "ThirdActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handle_image);
        mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test3);
        initView();
        iniData();

    }

    private void iniData() {

        mSeerBar_saturation.setMax(MAX_VALUE);
        mSeerBar_saturation.setOnSeekBarChangeListener(this);
        mSeerBar_saturation.setProgress(MIN_VALUE);

        mSeerBar_hue.setMax(MAX_VALUE);
        mSeerBar_hue.setOnSeekBarChangeListener(this);
        mSeerBar_hue.setProgress(MIN_VALUE);

        mSeerBar_lum.setMax(MAX_VALUE);
        mSeerBar_lum.setOnSeekBarChangeListener(this);
        mSeerBar_lum.setProgress(MIN_VALUE);

       imageView.setImageBitmap(mBitmap);

    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.id_image_beauty);
        mSeerBar_lum = (SeekBar) findViewById(R.id.id_seekbar_lum);
        mSeerBar_hue = (SeekBar) findViewById(R.id.id_seekbar_hue);
        mSeerBar_saturation = (SeekBar) findViewById(R.id.id_seekbar_saturation);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean
            fromUser) {
        switch (seekBar.getId()){
            case R.id.id_seekbar_hue:

                   mhue = (progress - MIN_VALUE)*1.0F/MIN_VALUE *180;
                Log.d(TAG, "到底进来了没有");
                break;
            case R.id.id_seekbar_lum:

                  msaturation = (progress *1.0F) / MIN_VALUE ;

                break;
            case R.id.id_seekbar_saturation:

                mlum = (progress *1.0F) / MIN_VALUE ;

                break;


        }
        Log.d(TAG, "那你把值传进去了么？ mhue is "+ mhue);
        Bitmap bm = ImageHelper.BitmapHandle(mBitmap,mhue,mlum,msaturation);
        Log.d(TAG, "还是那张图么？");
        imageView.setImageBitmap(bm);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
