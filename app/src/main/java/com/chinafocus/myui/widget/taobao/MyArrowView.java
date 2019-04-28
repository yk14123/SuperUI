package com.chinafocus.myui.widget.taobao;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

public class MyArrowView extends View {

    private int mWidth = 0;
    private int mHeight = 0;
    private Path mPath = new Path();

    private Paint mPaint = new Paint();

    public MyArrowView(Context context) {
        super(context);
        init();
    }

    public MyArrowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyArrowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(0xffaaaaaa);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(MeasureSpec.makeMeasureSpec(1080, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(1920, MeasureSpec.EXACTLY));
    }

    @Override
    public void onDraw(@NonNull Canvas canvas) {
//        final Drawable drawable = ArrowDrawable.this;
//        final Rect bounds = drawable.getBounds();
        int width = 1080;
        int height = 1920;
        if (mWidth != width || mHeight != height) {
            int lineWidth = width * 30 / 225;
            mPath.reset();

            float vector1 = (float) (lineWidth * Math.sin(Math.PI / 4));
            float vector2 = (float) (lineWidth / Math.sin(Math.PI / 4));
            mPath.moveTo(width / 2, height);
            mPath.lineTo(0, height / 2);
            mPath.lineTo(vector1, height / 2 - vector1);
            mPath.lineTo(width / 2 - lineWidth / 2, height - vector2 - lineWidth / 2);
            mPath.lineTo(width / 2 - lineWidth / 2, 0);
            mPath.lineTo(width / 2 + lineWidth / 2, 0);
            mPath.lineTo(width / 2 + lineWidth / 2, height - vector2 - lineWidth / 2);
            mPath.lineTo(width - vector1, height / 2 - vector1);
            mPath.lineTo(width, height / 2);
            mPath.close();

            mWidth = width;
            mHeight = height;
        }
        canvas.drawPath(mPath, mPaint);
    }
}