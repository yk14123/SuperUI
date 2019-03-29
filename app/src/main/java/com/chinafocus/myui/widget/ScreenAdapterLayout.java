package com.chinafocus.myui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.chinafocus.myui.utils.ScreenUtils;

/**
 * 手搓像素适配
 * @date 2019/3/28
 * description：
 */
public class ScreenAdapterLayout extends RelativeLayout {

    // 处理多次测量的问题
    private boolean mIsFistMeasure;

    public ScreenAdapterLayout(Context context) {
        super(context);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // 测试结果：执行了2次onMeasure
        Log.e("===>>>>","ScreenAdapterLayout onMeasure");

        if (!mIsFistMeasure){

            float scaleX = ScreenUtils.getInstance(getContext()).getHorizontalScale();
            float scaleY = ScreenUtils.getInstance(getContext()).getVerticalScale();

            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                params.width = (int) (params.width * scaleX);
                params.height = (int) (params.height * scaleY);
                params.leftMargin = (int) (params.leftMargin * scaleX);
                params.rightMargin = (int) (params.rightMargin * scaleX);
                params.topMargin = (int) (params.topMargin * scaleY);
                params.bottomMargin = (int) (params.bottomMargin * scaleY);
            }

            mIsFistMeasure = true;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
