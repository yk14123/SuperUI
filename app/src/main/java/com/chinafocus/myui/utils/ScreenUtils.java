package com.chinafocus.myui.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * @author
 * @date 2019/3/28
 * description：
 */
public class ScreenUtils {

    // 设计稿，参考设备的宽高
    private static final float STANDARD_WIDTH = 720;
    private static final float STANDARD_HEIGHT = 1280;

    // 这时屏幕真实显示的宽高
    private int mDisplayWidth;
    private int mDisplayHeight;

    private static volatile ScreenUtils instance;

    private ScreenUtils(Context context) {
        // 获取屏幕宽高
        if (mDisplayHeight == 0 || mDisplayWidth == 0) {
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (manager != null) {
                DisplayMetrics metrics = new DisplayMetrics();
                manager.getDefaultDisplay().getMetrics(metrics);
                if (metrics.widthPixels > metrics.heightPixels) {
                    // 横屏
                    mDisplayWidth = metrics.heightPixels;
                    mDisplayHeight = metrics.widthPixels;
                } else {
                    // 竖屏
                    mDisplayWidth = metrics.widthPixels;
                    // 高度去除状态栏高度，获取精准高度
                    mDisplayHeight = metrics.heightPixels - getStatusBarHeight(context);

                    Log.e("===>>>", "状态栏高度是：" + getStatusBarHeight(context));
                }
            }
        }

    }

    /**
     * 获取状态栏高度
     *
     * @param context 上下文
     * @return 状态栏高度
     */
    public int getStatusBarHeight(Context context) {
        // name：取值的名称
        // defType：取的类型
        // defPackage：再哪个包下去取
        int resID = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resID > 0) {
            return context.getResources().getDimensionPixelSize(resID);
        }
        return 0;
    }

    public static ScreenUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (ScreenUtils.class) {
                if (instance == null) {
                    instance = new ScreenUtils(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    // 获取水平方向的缩放比
    public float getHorizontalScale() {
        return mDisplayWidth / STANDARD_WIDTH;
    }

    // 获取垂直方向的缩放比
    public float getVerticalScale() {
        return mDisplayHeight / STANDARD_HEIGHT;
    }

}
