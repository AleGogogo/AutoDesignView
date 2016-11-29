package com.example.lyw.myapplication.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.lyw.myapplication.R;

/**
 * Created by LYW on 2016/11/21.
 */

public class FourthActivity extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap bm;
    private GridLayout mGridLayout;
    private Button mChange;
    private Button mReset;
    private int width;
    private int height;
    private EditText[] edits = new EditText[20] ;
    private float[] mColorMatrix = new float[20] ;
    private static final String TAG = "FourthActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_matrix);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMarix();
                setImageMatrix();
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initMatrix();
                setImageMatrix();
            }
        });
    }

    private void initData() {
        //动态添加20个editorText,则需要获取gridlayout的长和宽
        //在调用oncreate()方法时，控件还没有绘制完毕
        mGridLayout.post(new Runnable() {
            @Override
            public void run() {
                width = mGridLayout.getWidth()/5;
                height = mGridLayout.getHeight()/4;
                addEdit();
                initMatrix();
            }
        });

    }

    private void addEdit() {
        for (int i = 0; i <20 ;i++ ){
            EditText editText = new EditText(FourthActivity.this);
            edits[i] = editText;
            mGridLayout.addView(editText,width,height);
        }
    }
    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                edits[i].setText(String.valueOf(1));
            } else {
                edits[i].setText(String.valueOf(0));
            }
        }
    }


    private void getMarix(){
        for (int i=0;i<20;i++){
                mColorMatrix[i] = Float.parseFloat(edits[i].getText().toString());
            Log.d(TAG, "getMarix: " + mColorMatrix[i]);
        }
    }

    private void setImageMatrix(){
        Bitmap bitmap = Bitmap.createBitmap(bm.getWidth(),bm.getHeight(),
                Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mColorMatrix);

        Canvas canvas = new Canvas(bitmap);
        //设置成抗锯齿类型
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap,0,0,paint);
        imageView.setImageBitmap(bitmap);
    }
    private void initView() {
        bm = BitmapFactory.decodeResource(getResources(),R.drawable.test1);
        imageView = (ImageView) findViewById(R.id.id_color_martix);
        imageView.setImageBitmap(bm);
        mGridLayout = (GridLayout) findViewById(R.id.id_gridlayout);
        mChange = (Button) findViewById(R.id.id_button_chang);
        mReset = (Button) findViewById(R.id.id_button_reset);
    }
}
