package com.chinafocus.myui.widget.taobao.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;

/**
 * @author
 * @date 2019/4/29
 * description：
 */
public class MyScrollView extends NestedScrollView {

    private View inner;
    private float mDownY;
    private Rect normal = new Rect();

    public MyScrollView(@NonNull Context context) {
        super(context);
    }

    public MyScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0)
            inner = getChildAt(0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (inner == null) {
            return super.onTouchEvent(ev);
        } else {
            overByTouch(ev);
        }
        return super.onTouchEvent(ev);
    }

    private void overByTouch(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();

                break;
            case MotionEvent.ACTION_MOVE:

                final float preY = mDownY;
                float moveY = ev.getY();

                // 这里一定要提前转换int。统一值，这样下面计算不会出错！
                int disY = (int) (preY - moveY);
                mDownY = moveY;

//                Log.e("needOverByAnim","needOverByAnim  getScrollY() -->" + getScrollY() );

                if (needOverByAnim()) {
                    // 先把第一个view的左上右下位置临时保存在RectF里面
                    if (normal.isEmpty()) {
                        normal.set(inner.getLeft(), inner.getTop(),
                                inner.getRight(), inner.getBottom());
                        Log.e("needOverByAnim","++++++++++++++++++++++++++++++");
                        Log.e("needOverByAnim", "save inner.getTop() == " + inner.getTop());
                        Log.e("needOverByAnim", "save inner.getBottom() == " + inner.getBottom());
                        Log.e("needOverByAnim","++++++++++++++++++++++++++++++");
                        return;
                    }
                    inner.layout(inner.getLeft(), (int) (inner.getTop() - disY),
                            inner.getRight(), (int) (inner.getBottom() - disY));
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!normal.isEmpty()) {
                    startOverByAnim();
                }
                break;
            default:
                break;
        }
    }

    private void startOverByAnim() {
//        int bottom = inner.getBottom() - inner.getTop();
//        Log.e("needOverByAnim","bottom() == " + bottom);
//        Log.e("needOverByAnim","inner.getHeight() == " + inner.getHeight());
//        Log.e("needOverByAnim","inner.getBottom() == " + inner.getBottom());
        Log.e("needOverByAnim", "inner.getTop() == " + inner.getTop());        Log.e("needOverByAnim", "normal.top == " + normal.top);
        Log.e("needOverByAnim", "inner.getBottom() == " + inner.getBottom());
        Log.e("needOverByAnim", "normal.bottom == " + normal.bottom);

        int top = inner.getTop();
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);

        TranslateAnimation ta = new TranslateAnimation(0, 0, top, 0);
        ta.setDuration(2000);
        inner.startAnimation(ta);

        Log.e("needOverByAnim","needOverByAnim  after 3000");
//        inner.layout(normal.left, normal.top, normal.right, normal.bottom);

//        inner.setTranslationY(-inner.getTop());
//        inner.layout(normal.left, normal.top, normal.right, normal.bottom);
        normal.setEmpty();


////属性动画 利用 top 为标准值统一处理
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(inner.getTop(), normal.top);
//        valueAnimator.setDuration(200);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int top = (int) animation.getAnimatedValue();
//                inner.layout(inner.getLeft(), top,
//                        inner.getRight(), inner.getBottom() - inner.getTop() + top);
//            }
//        });

        ////属性动画 利用 bottom 为标准值统一处理
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(inner.getBottom(), normal.bottom);
//        valueAnimator.setDuration(200);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
////                int top = (int) animation.getAnimatedValue();
////                inner.layout(inner.getLeft(), top,
////                        inner.getRight(), inner.getHeight() + top);
//
//                int bottom = (int) animation.getAnimatedValue();
//                inner.layout(inner.getLeft(), bottom - inner.getHeight(),
//                        inner.getRight(), bottom);
//            }
//        });

//        valueAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
////                inner.layout(normal.left, normal.top, normal.right, normal.bottom);
//                normal.setEmpty();
//            }
//        });
//        valueAnimator.start();
    }

    private boolean needOverByAnim() {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        // 当top触顶的时候 scrollY == 0
        // 当bottom触底的时候 scrollY == offset
        return scrollY == 0 || scrollY == offset;
    }
}
