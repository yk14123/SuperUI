package com.chinafocus.myui.widget.taobao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.chinafocus.myui.R;

/**
 * @author
 * @date 2019/4/29
 * description：
 */
public class ThirdActivity extends AppCompatActivity {

    private ImageView mImageView;
    private int mTop;
    private int mBottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_taobao_third);

        mImageView = findViewById(R.id.iv_third_image);
        mImageView.setVisibility(View.INVISIBLE);
        mTop = mImageView.getTop();
        mBottom = mImageView.getBottom();

        Log.e("mImageView", "mTop == " + mTop);
        Log.e("mImageView", "mBottom == " + mBottom);
        Log.e("mImageView", "mImageView.getMeasuredHeight() == " + mImageView.getMeasuredHeight());
        Log.e("mImageView", "mImageView.getHeight() == " + mImageView.getHeight());


        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdActivity.this, "image被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void testMove(View view) {

//        TranslateAnimationMethod();
        ScaleAnimationMethod();

    }

    private void ScaleAnimationMethod() {

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, mImageView.getWidth() / 2, mImageView.getHeight() / 2);
        sa.setDuration(1000);
//        sa.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                mImageView.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
        sa.setFillAfter(true);
        mImageView.startAnimation(sa);

    }

    private void TranslateAnimationMethod() {
        /**
         *  位移动画的Y距离，是基于Layout的位置来计算的。
         *  从屏幕外面位移动画，到屏幕里面。两种方式
         *  方式一：View.GONE：然后开启位移动画的时候，需要在onAnimationStart开启：mView.setVisibility(View.INVISIBLE);或者View.VISIBLE都可以
         *  方式二: View.INVISIBLE：直接开启位移动画，无需任何 可见与不可见 的操作！
         *  以上操作，位移动画后，都需要设置 mTranslateAnimation.setFillAfter(true);
         */
//        mImageView.layout(mImageView.getLeft(), 500, mImageView.getRight(), mImageView.getMeasuredHeight() + 500);
//        Log.e("mImageView", "mImageView.getHeight() == " + mImageView.getMeasuredHeight());
        TranslateAnimation ta = new TranslateAnimation(0, 0, 732, 0);
        ta.setDuration(1000);
//        ta.setFillBefore(true);
        ta.setFillAfter(true);
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//                mImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mImageView.startAnimation(ta);
        mImageView.layout(mImageView.getLeft(), 732, mImageView.getRight(), 732 + 732);
//        /**
//         * 属性动画，Layout相关数据依然不变！！但是事件响应区域确实变了，补间动画事件响应区域没有变
//         */
//        mImageView.animate().translationY(500).setDuration(2000).start();

    }
}
