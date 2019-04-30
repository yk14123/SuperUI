package com.chinafocus.myui.widget.canvas.pathmeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author
 * @date 2019/4/30
 * description：
 */
public class PathMeasureView2 extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private Path mPath;
    private Path mNewPath;
    private PathMeasure mPathMeasure;
    private float totalC;
    private float mLength;
    private Matrix mMatrix;

    public PathMeasureView2(Context context) {
        super(context);
        init();
    }

    public PathMeasureView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathMeasureView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);

        mPath = new Path();
        mPath.addCircle(0, 0, 100, Path.Direction.CW);
        mPathMeasure = new PathMeasure(mPath, true);
        mNewPath = new Path();

        mLength = mPathMeasure.getLength();
        // 和length值会有部分偏差
        // length == 627.30347
        //      c == 628.31854
        float c = (float) (Math.PI * 2 * 100);

        mMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, mPaint);
        canvas.drawLine(mWidth / 2, 0, mWidth / 2, mHeight, mPaint);
        canvas.translate(mWidth / 2, mHeight / 2);

        if (totalC > mLength) {
            totalC = 0;
            mNewPath.reset();
        }
        totalC += 2;
//        if (totalC < mLength){
//            totalC += 2;
        mPathMeasure.getSegment(0, totalC, mNewPath, true);
//            mPathMeasure.getMatrix(totalC,mMatrix,PathMeasure.POSITION_MATRIX_FLAG);
        canvas.drawPath(mNewPath, mPaint);
        invalidate();
//        }else{
//        totalC = 0;
//            canvas.drawPath(mPath, mPaint);
//        }
//        invalidate();
    }
}
