package com.chinafocus.myui.widget.taobao;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.design.animation.ArgbEvaluatorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class MyDragLayout extends FrameLayout {

    private ViewDragHelper mViewDragHelper;
    private View recyclerView;

    public MyDragLayout(Context context) {
        super(context);
        init();
    }

    public MyDragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyDragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, 0, new ViewDragHelper.Callback() {
            /**
             * 是否用于捕获当前child的触摸事件
             * @param view 当前触摸的子view
             * @param i
             * @return true 捕获并解析  false 不处理
             */
            @Override
            public boolean tryCaptureView(@NonNull View view, int i) {
                return true;
            }

            /**
             * 当View开始被捕获和解析的回调
             * @param capturedChild 捕获的子view
             * @param activePointerId
             */
            @Override
            public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
            }

            /**
             * 获取子View水平方向的拖拽范围
             * 不能移动限定范围，返回值用在，当手指抬起的时候，View缓慢移动到某个位置的缓慢动画上面
             * @param child
             * @return
             */
            @Override
            public int getViewHorizontalDragRange(@NonNull View child) {

                return getMeasuredWidth() - child.getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
            }

            /**
             * 控制子View水平方向移动
             * @param child 当前被捕获的子View
             * @param left  当前被捕获的子View改变的left值  left=ChildView.getLeft()+dx
             * @param dx 本次子View水平方向移动的距离
             * @return
             */
            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {

                if (left <= getPaddingLeft())
                    left = getPaddingLeft();

                if (left >= getMeasuredWidth() - child.getMeasuredWidth() - getPaddingRight())
                    left = getMeasuredWidth() - child.getMeasuredWidth() - getPaddingRight();

                return left;
            }

            /**
             * 获取子View垂直方向的拖拽范围
             * @param child
             * @return
             */
            @Override
            public int getViewVerticalDragRange(@NonNull View child) {
                return getMeasuredHeight() - child.getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
//                return 100;
            }

            /**
             * 控制子View垂直方向移动
             * @param child 当前被捕获的子View
             * @param top 当前被捕获的子View改变的top值  top=ChildView.getTop()+dy
             * @param dy 本次子View垂直方向移动的距离
             * @return
             */
            @Override
            public int clampViewPositionVertical(@NonNull View child, int top, int dy) {

                if (top <= getPaddingTop())
                    top = getPaddingTop();

                if (top >= getMeasuredHeight() - child.getMeasuredHeight() - getPaddingBottom())
                    top = getMeasuredHeight() - child.getMeasuredHeight() - getPaddingBottom();

                return top;
            }

            /**
             * 当View位置发生改变的时候，执行。一般用来做其他子View的跟随移动
             * @param changedView   发生位置改变的View
             * @param left  发生位置改变的View的左边位置
             * @param top   发生位置改变的View的顶部位置
             * @param dx    水平偏移量
             * @param dy    垂直偏移量
             */
            @Override
            public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);

//                int mBlueView_left = recyclerView.getLeft() + dx;
//                int mBlueView_top = recyclerView.getTop() + dy;
//                int mBlueView_right = recyclerView.getRight() + dx;
//                int mBlueView_bottom = recyclerView.getBottom() + dy;
//
//                /**
//                 * 当拖拽红色View的时候，蓝色View也跟着一起移动。并且无论怎么移动，蓝色都不会移除padding区域
//                 */
//                if (changedView == mRedView) {
//
//
//                    if (mBlueView_left < getPaddingLeft()) {
//                        mBlueView_left = getPaddingLeft();
//                        mBlueView_right = mBlueView_left + recyclerView.getMeasuredWidth();
//                    }
//
//                    if (mBlueView_top < getPaddingTop()) {
//                        mBlueView_top = getPaddingTop();
//                        mBlueView_bottom = mBlueView_top + recyclerView.getMeasuredHeight();
//                    }
//
//                    if (mBlueView_right > getMeasuredWidth() - getPaddingRight()) {
//                        mBlueView_right = getMeasuredWidth() - getPaddingRight();
//                        mBlueView_left = mBlueView_right - recyclerView.getMeasuredWidth();
//                    }
//
//                    if (mBlueView_bottom > getMeasuredHeight() - getPaddingBottom()) {
//                        mBlueView_bottom = getMeasuredHeight() - getPaddingBottom();
//                        mBlueView_top = mBlueView_bottom - recyclerView.getMeasuredHeight();
//                    }
//
//                    recyclerView.layout(mBlueView_left, mBlueView_top, mBlueView_right, mBlueView_bottom);
//
                    float f = (changedView.getTop() - getPaddingTop()) * 1f /
                            (getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - changedView.getMeasuredWidth());
//
                    executeAnim(f);

//                }

            }

            /**
             * 当手指抬起的时候，View执行的变化
             * @param releasedChild
             * @param xvel
             * @param yvel
             */
            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);

                int center = getMeasuredWidth() / 2 - releasedChild.getMeasuredWidth() / 2;

                if (releasedChild.getLeft() < center) {
                    mViewDragHelper.smoothSlideViewTo(releasedChild,
                            getPaddingLeft(), releasedChild.getTop());
                } else {
                    mViewDragHelper.smoothSlideViewTo(releasedChild,
                            getMeasuredWidth() - releasedChild.getMeasuredWidth() - getPaddingRight(), releasedChild.getTop());
                }

                ViewCompat.postInvalidateOnAnimation(MyDragLayout.this);
            }
        });
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(MyDragLayout.this);
        }
    }

    /**
     * 当子View填充完毕的时候调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        recyclerView = getChildAt(0);
//        mRedView = getChildAt(1);


    }

    /**
     * 当所有View，测量完毕的时候，调用。可以初始化宽高
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    private void executeAnim(float f) {
//        recyclerView.setRotation(f * 720);
        /**
         * 设置颜色渐变
         * 时间轴：0-1
         * 起始颜色
         * 最终颜色
         */
//        mRedView.setBackgroundColor(ArgbEvaluatorCompat.getInstance().evaluate(f, Color.RED, Color.GREEN));

        //0-1
        //1-0.6
//        recyclerView.setScaleX(1 - 0.4f * f);
        /**
         * 给ViewGroup蒙上一层黑色。当滑动的时候，由黑色变透明
         *    颜色变化值，由黑色，变化到透明
         *   PorterDuff.Mode.SRC_OVER  固定写法
         */
//        getBackground().setColorFilter((Integer) ColorUtil.evaluateColor(f, Color.BLACK, Color.TRANSPARENT), PorterDuff.Mode.SRC_OVER);
        getBackground().setColorFilter(ArgbEvaluatorCompat.getInstance().evaluate(f, Color.BLACK, Color.TRANSPARENT), PorterDuff.Mode.SRC_OVER);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

//        int left = getMeasuredWidth() / 2 - mRedView.getMeasuredWidth() / 2;
        int left = getMeasuredWidth() / 2;
        int top = getPaddingTop();
        int right = left - getPaddingRight();
        int bottom = -getPaddingBottom();
        recyclerView.layout(0, top, left + recyclerView.getMeasuredWidth(), top + recyclerView.getMeasuredHeight());
//        mRedView.layout(left, top, left + mRedView.getMeasuredWidth(), top + mRedView.getMeasuredHeight());
//        recyclerView.layout(left, mRedView.getBottom(), left + recyclerView.getMeasuredWidth(), mRedView.getBottom() + recyclerView.getMeasuredHeight());
//        recyclerView.layout(0, 0, recyclerView.getMeasuredWidth(), recyclerView.getBottom());
    }
}