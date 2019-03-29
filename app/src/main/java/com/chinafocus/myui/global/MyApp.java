package com.chinafocus.myui.global;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.chinafocus.myui.utils.DensityUtils;

/**
 * @author
 * @date 2019/3/29
 * description：
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // activity全局监听
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                DensityUtils.setDensity(MyApp.this,activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }
}
