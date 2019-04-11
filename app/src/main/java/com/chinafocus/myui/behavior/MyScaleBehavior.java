package com.chinafocus.myui.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author
 * @date 2019/4/10
 * description：
 */
public class MyScaleBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    private int height;

    public MyScaleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull V child, int layoutDirection) {
        height = child.getMeasuredHeight();
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    /**
     *
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param axes ViewCompat.SCROLL_AXIS_VERTICAL 为 2 ViewCompat.SCROLL_AXIS_HORIZONTAL 为 1 ViewCompat.SCROLL_AXIS_NONE 为 0
     * @param type
     * @return 为true的时候，表示该child View，需要接受滑动事件！！
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_NONE;
    }


    /**
     * @param coordinatorLayout
     * @param child             behavior绑定的View
     * @param target            recyclerView，下面的所有值全部来源于target
     * @param dxConsumed        对于垂直方向的LayoutManager，该值无论怎么滑，始终为0
     * @param dyConsumed        recyclerView的内容滑动，此时滑动的距离dy是多少，
     * @param dxUnconsumed      对于垂直方向的LayoutManager，该值无论怎么滑，始终为0
     * @param dyUnconsumed      当recycleView滑动到顶部或者底部，此时内容不动了，如果再继续滑动，产生的距离就是dyUnconsumed
     * @param type              0 = 触摸状态，1 = Fling状态
     *                          <p>
     *                          dyUnconsumed > 0 表示手指向上滑，recyclerView内容位置是在底部Top，并且这个时候recyclerView已经到头了，无法再消费了。
     *                          yUnconsumed < 0 表示手指向下滑，recyclerView内容位置是在顶部Top，并且这个时候recyclerView已经到头了，无法再消费了。
     *                          由于 onStartNestedScroll return axes == ViewCompat.SCROLL_AXIS_VERTICAL;只接受垂直方向的事件滑动
     *                          所以 dxConsumed 和 dxUnconsumed 都为0
     */
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);

//        if (dyUnconsumed < 0) {
//            Log.e("MyScaleBehavior", "Touch状态 type == " + type);
//            Log.e("MyScaleBehavior", "View child simpleName == " + child.getClass().getSimpleName());
//            Log.e("MyScaleBehavior", "View target simpleName == " + target.getClass().getSimpleName());
        Log.e("MyScaleBehavior", "dxConsumed == " + dxConsumed);
//            Log.e("MyScaleBehavior", "dyConsumed == " + dyConsumed);
        Log.e("MyScaleBehavior", "dxUnconsumed == " + dxUnconsumed);
//            Log.e("MyScaleBehavior", "dyUnconsumed == " + dyUnconsumed);
//            consumed[0] = dy;
//            child.setTranslationY(child.getTranslationY()+dyUnconsumed);

//        } else {
//            Log.e("MyScaleBehavior", "Fling状态 type == " + type);
//            Log.e("MyScaleBehavior", "View child simpleName == " + child.getClass().getSimpleName());
//            Log.e("MyScaleBehavior", "View target simpleName == " + target.getClass().getSimpleName());
//            Log.e("MyScaleBehavior", "dy == " + dx);
//            Log.e("MyScaleBehavior", "dy == " + dy);
//            child.scrollBy(0,dyUnconsumed);
//        }
        if (type == 0)
            child.setTranslationY(child.getTranslationY() + dyUnconsumed);
    }

    /**
     * 该方法是在事件发源地的move方法里面调用。所以是会频繁调用
     *
     * @param coordinatorLayout
     * @param child             赋值behavior的View
     * @param target            滑动事件发源地的View
     * @param dx                在滑动事件发源地的View上面，dx = 按下的位置downX - 滑动的moveX ， 注意是和最近的moveX的差
     * @param dy                在滑动事件发源地的View上面，dy = 按下的位置downY - 滑动的moveY ， 注意是和最近的moveY的差
     * @param consumed          打算拦截消费的值
     * @param type              0 = 触摸状态，1 = Fling状态
     */
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        if (type == 0) {
//            Log.e("MyScaleBehavior", "Touch状态 type == " + type);
//            Log.e("MyScaleBehavior", "View child simpleName == " + child.getClass().getSimpleName());
//            Log.e("MyScaleBehavior", "View target simpleName == " + target.getClass().getSimpleName());
//            Log.e("MyScaleBehavior", "dx == " + dx);
//            Log.e("MyScaleBehavior", "dy == " + dy);
//            consumed[0] = dy;
//            child.animate().translationYBy(height + dy);

        } else {
//            Log.e("MyScaleBehavior", "Fling状态 type == " + type);
//            Log.e("MyScaleBehavior", "View child simpleName == " + child.getClass().getSimpleName());
//            Log.e("MyScaleBehavior", "View target simpleName == " + target.getClass().getSimpleName());
//            Log.e("MyScaleBehavior", "dy == " + dx);
//            Log.e("MyScaleBehavior", "dy == " + dy);
        }
    }

}
