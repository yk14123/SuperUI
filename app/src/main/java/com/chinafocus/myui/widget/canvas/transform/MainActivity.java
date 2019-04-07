package com.chinafocus.myui.widget.canvas.transform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SaveRestoreView(this));
//        setContentView(new TransformView(this));
    }
}
