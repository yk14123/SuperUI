package com.chinafocus.myui.widget.taobao.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class ServiceRightScrollView extends NestedScrollView {
    private static final int size = 4;
    private View inner;
    private float y;
    //    private int yDown, yMove;
//    private boolean isIntercept;
    private Rect normal = new Rect();
    private OnSlideListenerInterface onSlideListener;

    public void setOnSlideListener(OnSlideListenerInterface onSlideListener) {
        this.onSlideListener = onSlideListener;
    }

    public ServiceRightScrollView(Context context) {
        super(context);
    }

    public ServiceRightScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            inner = getChildAt(0);
        }
        super.onFinishInflate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (inner == null) {
            return super.onTouchEvent(ev);
        } else {
            commOnTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                y = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (isNeedAnimation()) {
//                    Log.i("mlguitar", y+"");
                    animation();

//                    if (onSlideListener != null) {
//                        onSlideListener.OnSlideStartAnim();
//                    }

                }
                break;
            case MotionEvent.ACTION_MOVE:
                final float preY = y;
                float nowY = ev.getY();
                int deltaY = (int) (preY - nowY) / size;
                // scrollBy(0, deltaY);
                y = nowY;
                if (isNeedMove()) {
                    if (normal.isEmpty()) {
                        normal.set(inner.getLeft(), inner.getTop(),
                                inner.getRight(), inner.getBottom());
                        return;
                    }
                    int yy = inner.getTop() - deltaY;

                    if (onSlideListener != null && deltaY < 0) {
                        onSlideListener.OnSlideChangeListener(deltaY);
                    }

                    inner.layout(inner.getLeft(), yy, inner.getRight(),
                            inner.getBottom() - deltaY);
                }
                break;
            default:
                break;
        }
    }


    public void animation() {
        TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(),
                normal.top);
        ta.setDuration(200);
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (onSlideListener != null) {
                    onSlideListener.OnSlideStartAnim();
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        inner.startAnimation(ta);
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);
        normal.setEmpty();
    }

    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    public boolean isNeedMove() {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        return scrollY == 0 || scrollY == offset;
    }

    public interface OnSlideListenerInterface {
        void OnSlideChangeListener(float deltaY);

        void OnSlideStartAnim();

    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//
//        int y = (int) event.getY();
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                yDown = y;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                yMove = y;
//                //上滑
//                if (yMove - yDown < 0) {
//                    isIntercept = true;
//                    //下滑
//                } else if (yMove - yDown > 0) {
//                    isIntercept = true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//
//        }
//        return isIntercept;
//    }
}