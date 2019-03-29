package com.chinafocus.myui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.chinafocus.myui.R;


/**
 * 手搓百分比布局
 * @date 2019/3/28
 * description：
 */
public class PercentLayout extends RelativeLayout {

    // 处理多次测量的问题
    private boolean mIsFistMeasure;

    public PercentLayout(Context context) {
        super(context);
    }

    public PercentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // 测试结果：执行了2次onMeasure
        Log.e("===>>>>","ScreenAdapterLayout onMeasure");

        if (!mIsFistMeasure) {

            // 获取父容器的尺寸
            int widthParentSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightParentSize = MeasureSpec.getSize(heightMeasureSpec);

            Log.e("===>>>", "整个xml高度是：" + heightParentSize);

            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                ViewGroup.LayoutParams params = child.getLayoutParams();

                if (checkLayoutParams(params)) {
                    LayoutParams lp = (LayoutParams) params;

                    float widthPercent = lp.widthPercent;
                    float heightPercent = lp.heightPercent;
                    float marginLeftPercent = lp.marginLeftPercent;
                    float marginRightPercent = lp.marginRightPercent;
                    float marginTopPercent = lp.marginTopPercent;
                    float marginBottomPercent = lp.marginBottomPercent;

                    if (widthPercent > 0) {
                        params.width = (int) (widthParentSize * widthPercent);
                    }

                    if (heightPercent > 0) {
                        params.height = (int) (heightParentSize * heightPercent);
                    }

                    if (marginLeftPercent > 0) {
                        ((LayoutParams) params).leftMargin = (int) (widthParentSize * marginLeftPercent);
                    }

                    if (marginRightPercent > 0) {
                        ((LayoutParams) params).rightMargin = (int) (widthParentSize * marginRightPercent);
                    }

                    if (marginTopPercent > 0) {
                        ((LayoutParams) params).topMargin = (int) (heightParentSize * marginTopPercent);
                    }

                    if (marginBottomPercent > 0) {
                        ((LayoutParams) params).bottomMargin = (int) (heightParentSize * marginBottomPercent);
                    }
                }
            }

            mIsFistMeasure = true;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    // setContentView的时候，会调用布局参数！
    @Override
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /**
     *  attachToRoot 在初始化DecorView加载App第一个页面的最上层ViewGroup的xml，的时候，为false
     *  attachToRoot 在加载Activity的xml内部的时候，为true
     *
     *  attachToRoot 为false，则子类直接setLayoutParams（使用父ViewGroup的LayoutParams）
     *  attachToRoot 为true，则调用 父ViewGroup.addView（子View） -->addViewInner -->具体的 父ViewGroup需要重写自己的generateLayoutParams！！
     *  通过generateLayoutParams，调用TypeArray来读取赋给子View的布局规则，比如，子View是否centerInParent，是否align to xx
     *  父ViewGroup再通过读取的规则做相应的layout摆放
     *
     */
    public static class LayoutParams extends RelativeLayout.LayoutParams {

        private float widthPercent;
        private float heightPercent;
        private float marginLeftPercent;
        private float marginRightPercent;
        private float marginTopPercent;
        private float marginBottomPercent;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            // 解析自定义属性
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.PercentLayout_Layout);

            // R.styleable全部是自动生成的！
            /**
             *  PercentLayout_Layout_layout_widthPercent_mt 名字的由来
             *  是系统自动生成，
             *  是根据attrs.xml中的
             *  <declare-styleable name="PercentLayout_Layout">   name1
             *  <attr name="layout_widthPercent_mt" format="float"/>  name2
             *  name1 + name2 得到的
             *
             *  所以attrs.xml的name1和name2其实可以随便取任意名字。不是根据名字来关联关系。而是根据TypeArray去读R.styleable来建立关系
             *  官方推荐如下写法: 这样的话 app:layout 后面就会自动找到
             *  name1以 _Layout 结尾 如： "自定义ViewGroup名" + "_Layout"
             *  name2以 layout_ 开头 如： "layout_" + "自定义ViewGroup名"
             *
             *  <TextView
             *         android:onClick="startSecondActivity"
             *         android:layout_width="match_parent"
             *         android:layout_height="match_parent"
             *         android:text="one activity"
             *         app:layout_widthPercent_mt="0.5"  这里只需要敲app:layout后面会自动提示！
             *         app:layout_heightPercent_mt="0.5" 这里只需要敲app:layout后面会自动提示！
             *         app:layout_marginLeft_percent_mt="0.5" 这里只需要敲app:layout后面会自动提示！
             *         android:background="#f0f"/>
             */
            widthPercent = a.getFloat(R.styleable.PercentLayout_Layout_layout_widthPercent_mt, 0);
            heightPercent = a.getFloat(R.styleable.PercentLayout_Layout_layout_heightPercent_mt, 0);
            marginLeftPercent = a.getFloat(R.styleable.PercentLayout_Layout_layout_marginLeft_percent_mt, 0);
            marginRightPercent = a.getFloat(R.styleable.PercentLayout_Layout_layout_marginRight_percent_mt, 0);
            marginTopPercent = a.getFloat(R.styleable.PercentLayout_Layout_layout_marginTop_percent_mt, 0);
            marginBottomPercent = a.getFloat(R.styleable.PercentLayout_Layout_layout_marginBottom_percent_mt, 0);

            Log.e("===>>>", "widthPercent from LayoutParams = " + widthPercent);

            a.recycle();
        }
    }

}
