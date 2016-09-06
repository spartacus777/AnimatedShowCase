package kizema.anton.animateviewshowcase.decorators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CrossOverDeco extends Decorator {
    Paint vertexPaint;

    public CrossOverDeco(Context context) {
        super(context);

        vertexPaint = new Paint();
        vertexPaint.setAntiAlias(true);
        vertexPaint.setColor(Color.BLACK);
        vertexPaint.setStyle(Paint.Style.STROKE);
        vertexPaint.setStrokeWidth(2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();

        canvas.drawLine(0, 0, w, h, vertexPaint);
    }

}