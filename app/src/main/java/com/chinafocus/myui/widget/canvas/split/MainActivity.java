package com.chinafocus.myui.widget.canvas.split;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.chinafocus.myui.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SplitView(this));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
        bitmap.getWidth();
        bitmap.getHeight();
        int pixel = bitmap.getPixel(0, 0);

        Log.e("===>>>", "pixel = " + pixel);
    }
}
