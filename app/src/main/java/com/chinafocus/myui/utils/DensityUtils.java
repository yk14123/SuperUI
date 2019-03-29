package com.chinafocus.myui.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * @author
 * @date 2019/3/29
 * description：
 */
public class DensityUtils {

    // 参考设备的宽，单位是dp。设计稿上面的宽dp值
    private static final float WIDTH = 360;
    // 当前设备屏幕密度
    private static float appDensity;
    // 当前设备字体缩放比例
    private static float appScaleDensity;

    public static void setDensity(Application application, Activity activity) {
        // 获取当前app的屏幕显示信息
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (appDensity == 0) {
            // 初始化赋值操作
            appDensity = displayMetrics.density;
            appScaleDensity = displayMetrics.scaledDensity;

            Log.e("===>>>", "当前设备的Density = " + appDensity + "  当前设备字体的Density = " + appScaleDensity);

            // 添加字体变换监听回调
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    // 字体发生改变，重新对scaleDensity进行赋值
                    if (newConfig != null && newConfig.fontScale > 0) {
                        appScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        Log.e("===>>>", "当前设备的displayMetrics.widthPixels = " + displayMetrics.widthPixels);

        /**
         *  核心代码
         */
        // 计算目标值density,scaleDensity,densityDpi
        float targetDensity = displayMetrics.widthPixels / WIDTH; // 1080 / 360 = 3.0  1080 / 当前设计稿的宽的值 = 需要调整的设备密度
        float targetScaleDensity = targetDensity * (appScaleDensity / appDensity);
        int targetDensityDpi = (int) (targetDensity * 160);

        Log.e("===>>>", "当前应用程序的targetDensity = " + targetDensity
                + "  当前应用程序的targetScaleDensity = " + targetScaleDensity
                + "  当前应用程序的targetDensityDpi = " + targetDensityDpi);

        /**
         * 华为手机：180dp
         * 当前设备的Density = 3.0  当前设备字体的Density = 3.0
         * 当前应用程序的targetDensity = 3.0  当前应用程序的targetScaleDensity = 3.0  当前应用程序的targetDensityDpi = 480
         *
         * 红米6手机：180dp
         * 当前设备的Density = 2.75  当前设备字体的Density = 2.75
         * 当前应用程序的targetDensity = 3.0  当前应用程序的targetScaleDensity = 3.0  当前应用程序的targetDensityDpi = 480
         *
         * 竖屏设备：180dp
         * 当前设备的Density = 1.0  当前设备字体的Density = 1.15
         * 当前应用程序的targetDensity = 3.0  当前应用程序的targetScaleDensity = 3.4499998  当前应用程序的targetDensityDpi = 480
         */

        //替换Activity的density,scaleDensity,densityDpi
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();

        /**
         *  指定当前activity设备密度，如分辨率1920 * 1080上面则指定为 3 ！
         *  比如宽1080px的手机，屏幕一半则是540px。如果设备密度为3的话，则应该输入180dp
         *  但是很多1080px的手机，设备密度不一样，比如红米6，该设备密度是2.75，则应该输入196dp。
         *  为了让196dp，自动换算成180dp。所以需要targetDensity = displayMetrics.widthPixels / WIDTH;
         */
        dm.density = targetDensity;
        dm.scaledDensity = targetScaleDensity;
        dm.densityDpi = targetDensityDpi;
    }
}
