package com.chinafocus.myui.widget.canvas.pathmeasure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new PathMeasureView(this));
        setContentView(new PathMeasureView2(this));
    }
}
