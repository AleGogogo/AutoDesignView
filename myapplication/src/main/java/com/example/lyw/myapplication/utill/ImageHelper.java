package com.example.lyw.myapplication.utill;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by LYW on 2016/11/16.
 */

public class ImageHelper {
    private static final String TAG = "ImageHelper";
    /**
     * @param bitmap
     * @param hue         色调
     * @param lum        透明度
     * @param saturation 饱和度
     * @return
     */
    public static Bitmap BitmapHandle(Bitmap bitmap, float hue, float lum, float
            saturation) {

        Bitmap bm = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight()
                , Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        ColorMatrix hueMatrix = new ColorMatrix();
        //0代表R 1代表G 2代表B
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);
        Log.d(TAG, "hue is "+ hue);
        //设置饱和度
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);
        //设置透明度
        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageColorMatrix = new ColorMatrix();
        //concat this colormatrix with the specified postmatrix.
        imageColorMatrix.postConcat(hueMatrix);
        imageColorMatrix.postConcat(lumMatrix);
        imageColorMatrix.postConcat(saturationMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageColorMatrix));
        canvas.drawBitmap(bm,0,0,paint);

        return bm;
    }
}
