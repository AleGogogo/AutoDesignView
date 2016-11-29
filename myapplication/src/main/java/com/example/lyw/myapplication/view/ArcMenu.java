package com.example.lyw.myapplication.view;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;


import com.example.lyw.myapplication.R;




/**
 * Created by LYW on 2016/11/24.
 */

public class ArcMenu extends ViewGroup implements View.OnClickListener {
    private static final int POS_LEFT_TOP = 0;
    private static final int POS_LEFT_BOTTOM = 1;
    private static final int POS_RIGHT_TOP = 2;
    private static final int POS_RIGHT_BOTTOM = 3;
    private static final String TAG = "ArcMenu";

    @Override
    public void onClick(View v) {
        rotateMainButton(v, 0f, 360f, 300);
        toggleMenu(300);
    }

    /**
     * 切换菜单
     * @param duration 间隔时间
     */
    public void toggleMenu(int duration) {
        //为menuItem添加平移动画和旋转动画
        int cCount = getChildCount();
        for (int i =0; i<cCount-1;i++){
            final View child = getChildAt(i+1);
            child.setVisibility(View.VISIBLE);
            int cl = (int) (mRadius * Math.sin(Math.PI / 2 / (cCount - 2)
                    * i));
            int ct = (int) (mRadius * Math.cos(Math.PI / 2 / (cCount - 2)
                    * i));
            int xflag = 1;
            int yflag = 1;

            if ((mPosition == Position.LEFT_BOTTOM)||(mPosition == Position.LEFT_TOP)){
                xflag = -1 ;
            }
            if ((mPosition == Position.LEFT_TOP)||(mPosition == Position.RIGHT_TOP)){
                yflag = -1 ;
            }

            AnimationSet animatorSet = new AnimationSet(true);
            Animation transAnima = null;
            //to open
            if (mStutas == Stutas.CLOSE){
                //还是不理解  最后x 的位置不应该是0 啊，也应该是cl
                //换一种理解，意思是0 表示回到了原来的位置
                transAnima = new TranslateAnimation(cl*xflag,0,ct*yflag,0);
                child.setClickable(true);
                child.setFocusable(true);
            }else
            // to close
            {

                transAnima = new TranslateAnimation(0,xflag*cl,0,yflag*ct);
                child.setClickable(false);
                child.setFocusable(false);
            }
            transAnima.setFillAfter(true);
            transAnima.setDuration(duration);
            transAnima.setStartOffset((i * 100) / cCount);
            transAnima.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (mStutas == Stutas.CLOSE){
                        child.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            //做旋转动画
            RotateAnimation rotateAnimation = new RotateAnimation(0, 720,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation
                    .RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setDuration(duration);

            animatorSet.addAnimation(rotateAnimation);
            animatorSet.addAnimation(transAnima);
            child.startAnimation(animatorSet);

            final int pos = i+1;//除去主按钮
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onMenuItemClickListener != null) {
                        onMenuItemClickListener.onClick(child, pos);
                    }
                        menuItermAnim(pos - 1);
                        childStatus();

                }
            });

        }
        //切换菜单状态
        childStatus();
    }



    private void childStatus() {
        mStutas = (mStutas == Stutas.CLOSE? Stutas.OPEN:Stutas.CLOSE);
    }

    /**
     * 添加MenuIterm的点击动画
     * @param pos  要实现动画的位置
     */
        private void menuItermAnim(int pos) {
            for (int i = 0;i < getChildCount()-1;i++){
                View child  = getChildAt(i+1);

                if (i == pos){
                    child.startAnimation(scaleBigAnimation(300));
                }else {
                    child.startAnimation(scaleSmallAnimation(300));
                }
                child.setFocusable(false);
                child.setClickable(false);
            }
    }

    private Animation scaleBigAnimation(int duration) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 3.0f, 1.0f,
                3.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        AnimationSet set = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        set.setDuration(duration);
        set.setFillAfter(true);
        return set;
    }

    private Animation scaleSmallAnimation(int duration) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f,
                0.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        AnimationSet set = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        set.setDuration(duration);
        set.setFillAfter(true);
        return set;
    }

    /**
     * 旋转主按钮
     *
     * @param v  按钮图标
     * @param v1
     * @param v2
     * @param i
     */
    private void rotateMainButton(View v, float v1, float v2, int i) {
        RotateAnimation rotateAnimation = new RotateAnimation(v1, v2,
                Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(i);
        rotateAnimation.setFillAfter(true);
        v.startAnimation(rotateAnimation);
    }



    public enum Stutas {
        OPEN, CLOSE;
    }

    public enum Position {
        LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM;
    }

    private Position mPosition = Position.LEFT_BOTTOM;

    private Stutas mStutas = Stutas.CLOSE;

    //菜单的按钮
    private View mViewButton;

    private int mRadius;

    private onMenuItemClickListener onMenuItemClickListener;

    public ArcMenu(Context context) {
        this(context, null);
    }

    public ArcMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                100, getResources().getDisplayMetrics());

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable
                .ArcMenu, defStyleAttr, 0);

        getPosition(a);

        mRadius = (int) a.getDimension(R.styleable.ArcMenu_radius, TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        100, getResources().getDisplayMetrics()));
        a.recycle();
        Log.d(TAG, "ArcMenu:  mRadius is" + mRadius + ", mpositon = " +
                mPosition);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            layoutMainButton();
            int count = getChildCount();
            Log.d(TAG, "onLayout: count is" + count);
            for (int i = 0; i < count - 1; i++) {
                View child = getChildAt(i + 1);
                child.setVisibility(View.GONE);
                int cl = (int) (mRadius * Math.sin(Math.PI / 2 / (count - 2)
                        * i));
                int ct = (int) (mRadius * Math.cos(Math.PI / 2 / (count - 2)
                        * i));
                Log.d(TAG, "onLayout: cl is " + cl + " , ct is" + ct);
                int cwidth = child.getMeasuredWidth();
                int cheight = child.getMeasuredHeight();

                //如果在左下或者是右下
                if (mPosition == Position.LEFT_BOTTOM || mPosition ==
                        Position.RIGHT_BOTTOM) {
                    ct = getMeasuredHeight() - cheight - ct;
                }
                if (mPosition == Position.RIGHT_TOP || mPosition ==
                        Position.RIGHT_BOTTOM) {
                    cl = getMeasuredWidth() - cwidth - cl;
                }

                child.layout(cl, ct, cl + cwidth, ct + cheight);
            }

        }

    }

    private void layoutMainButton() {
        mViewButton = getChildAt(0);
        mViewButton.setOnClickListener(this);

        int l = 0;
        int t = 0;

        int width = mViewButton.getMeasuredWidth();
        int height = mViewButton.getMeasuredHeight();
        switch (mPosition) {
            case LEFT_TOP:
                l = 0;
                t = 0;
                break;
            case LEFT_BOTTOM:
                l = 0;
                t = getMeasuredHeight() - height;
                break;
            case RIGHT_TOP:
                l = getMeasuredWidth() - width;
                t = 0;
                break;
            case RIGHT_BOTTOM:
                l = getMeasuredWidth() - width;
                t = getMeasuredHeight() - height;
                break;
        }
        mViewButton.layout(l, t, l + width, t + height);
    }

    private void getPosition(TypedArray a) {
        int pos = a.getInt(R.styleable.ArcMenu_position, 3);
        switch (pos) {
            case POS_LEFT_TOP:
                mPosition = Position.LEFT_TOP;
                break;
            case POS_LEFT_BOTTOM:
                mPosition = Position.LEFT_BOTTOM;
                break;
            case POS_RIGHT_TOP:
                mPosition = Position.RIGHT_TOP;
                break;
            case POS_RIGHT_BOTTOM:
                mPosition = Position.RIGHT_BOTTOM;
                break;
        }
    }

    /**
     * 点击子菜单项的回调接口
     */
    public interface onMenuItemClickListener {

        void onClick(View view, int pos);
    }

    public void setOnMenuItemClickListener(onMenuItemClickListener
                                                   onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }
}
