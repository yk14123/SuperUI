package com.chinafocus.myui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.chinafocus.myui.utils.DensityUtils;

/**
 * @author
 * @date 2019/3/29
 * description：
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DensityUtils.setDensity(getApplication(),this);
    }
}
