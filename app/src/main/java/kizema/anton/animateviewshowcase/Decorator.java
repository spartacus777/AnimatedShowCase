package kizema.anton.animateviewshowcase;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

public class Decorator extends View {

    Paint vertexPaint;
    int counter;

    Bitmap fireIcon;

    //animation velocity = 200px / second.
    public Decorator(Context context) {
        super(context);

        fireIcon = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
        vertexPaint = new Paint();
        vertexPaint.setAntiAlias(true);
        vertexPaint.setColor(Color.BLACK);
        vertexPaint.setStyle(Paint.Style.STROKE);
//        vertexPaint.setStrokeJoin(Paint.Join.ROUND);
//        vertexPaint.setStrokeCap(Paint.Cap.ROUND);
        vertexPaint.setStrokeWidth(2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();



        int endX = 400 - 8 * counter;
        canvas.drawLine(0, 0, w, h, vertexPaint);
//        canvas.drawBitmap(fireIcon, endX - 6, 100 - 12, null);
//        if (endX > 100)
//            mHandler.postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    // TODO Auto-generated method stub
//                    counter++;
//                    invalidate();
//                }
//            }, 1000 / 25);
    }

    Handler mHandler = new Handler();

    /**
     * Invoke this method to animate the line.
     */
    public void lightCracker() {

        counter = 0;
        invalidate();
    }

    private class Line {
        int startX, startY;
        int endX, endY;


    }
}
