package com.chinafocus.myui.widget.canvas.bezier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chinafocus.myui.R;

public class MainActivity extends AppCompatActivity {

    private DragBubbleView mDragBubbleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new PathView(this));
        setContentView(R.layout.activity_canvas_bezier);

        mDragBubbleView = findViewById(R.id.dbv_drag_bubble_view);
    }

    public void resetDragBubble(View view){
        mDragBubbleView.init();
    }

}
