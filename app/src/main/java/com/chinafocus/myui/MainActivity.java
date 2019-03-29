package com.chinafocus.myui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

import com.chinafocus.myui.bean.Person;
import com.chinafocus.myui.global.Constants;
import com.chinafocus.myui.module.MyEventBus;
import com.chinafocus.myui.module.MyThread;
import com.chinafocus.myui.module.annotation.MyBus;

public class MainActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 需在setContentView之前调用
//        DensityUtils.setDensity(getApplication(),this);

//        setContentView(R.layout.activity_main);

        // 1.因为流海屏适配，只有发生在全屏！设置全屏，需在setContentView之前调用
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getWindow().getDecorView().setOnApplyWindowInsetsListener( new View.OnApplyWindowInsetsListener() {
            @SuppressLint("NewApi")
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {

                Log.e("===>>>" ,"setOnApplyWindowInsetsListener");
                // 用这里的View判断刘海状态，这个回调会在onCreate之后执行！
                // 再做流海屏适配的时候，需要判断手机，是不是刘海
                boolean hasDisplayCutout = hasDisplayCutout(window);
                Log.e("===>>>" , "hasDisplayCutout -->"+hasDisplayCutout);
                if (hasDisplayCutout){
                    // 2.允许--内容区延伸到刘海区
                    WindowManager.LayoutParams layoutParams = getWindow().getAttributes();

                    /**
                     * @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT // 全屏模式下，内容下移（状态栏变黑） 非全屏模式下，内容不变
                     * @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES //允许内容区延伸到刘海区（状态栏变白，需要设置沉浸式后，实现全屏效果）
                     * @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER // 不允许内容区延伸到刘海区（状态栏变黑）
                     */
                    layoutParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                    window.setAttributes(layoutParams);

                    // 3.设置沉浸式状态栏，让内容浸入到内容区
                    int flags = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                    int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
                    systemUiVisibility |= flags;// 追加沉浸式设置
                    window.getDecorView().setSystemUiVisibility(systemUiVisibility);
                }


                getWindow().getDecorView().setOnApplyWindowInsetsListener(null);
                return v.onApplyWindowInsets(insets);
            }
        });

        setContentView(R.layout.activity_liuhai_main);
        Log.e("===>>>" ,"setContentView");
//        MyEventBus.getDefault().register(this);

    }

    /**
     * 判断是否有流海屏
     * @return
     */
    @TargetApi(Build.VERSION_CODES.P)
    private boolean hasDisplayCutout(Window window) {
        DisplayCutout displayCutout;
        View rootView = window.getDecorView();
        WindowInsets insets = rootView.getRootWindowInsets();
        if (insets != null) {
            displayCutout = insets.getDisplayCutout();
            if (displayCutout != null) {
                return displayCutout.getBoundingRects() != null && displayCutout.getBoundingRects().size() > 0 && displayCutout.getSafeInsetTop() > 0;
            }
        }
        return false;
    }


    public void startSecondActivity(View view) {
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }


    @MyBus(thread = MyThread.MAIN)
    public void receivePersonData(Person person) {
        Log.e(Constants.MYBUS_TAG, person + "MainActivity获取数据成功  from receivePersonData" + "当前线程是：" + Thread.currentThread().getName());
    }

    @MyBus(thread = MyThread.MAIN)
    public void receivePersonData2(String person) {
        Log.e(Constants.MYBUS_TAG, person + "MainActivity获取数据成功" + "  from receivePersonData2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyEventBus.getDefault().unRegister(this);
    }
}
