package kizema.anton.animateviewshowcase.decorators.animcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import kizema.anton.animateviewshowcase.decorators.Decorator;
import kizema.anton.animateviewshowcase.helpers.UIHelper;

public class AnimatedCircleDeco extends Decorator {

    private static final int NOT_INITED = -1;
    private static final int DEF_FRAME_TIME = -1;

    private Paint circleStrokePaint;
    private Paint circleFillPaint;

    private Handler mHandler = new Handler();

    //configs
    private int widthOfCircle = UIHelper.getDPI(1);
    private int defRadius = NOT_INITED;
    private int numOfCircles = 2;
    private int frameTime = DEF_FRAME_TIME;//ms

    private int lengthBetweenCircles;
    private int deltaDistance;

    private List<AnimCircle> animCircleList;

    private AnimatedRunnable animatedRunnable;

    //animation velocity = 200px / second.
    private AnimatedCircleDeco(Context context) {
        super(context);

        circleStrokePaint = new Paint();
        circleStrokePaint.setAntiAlias(true);
        circleStrokePaint.setColor(Color.BLACK);
        circleStrokePaint.setStyle(Paint.Style.STROKE);
        circleStrokePaint.setStrokeWidth(widthOfCircle);

        animatedRunnable = new AnimatedRunnable();
    }

    private class AnimatedRunnable implements Runnable {

        @Override
        public void run() {
            for (AnimCircle c : animCircleList) {
                c.incrementStartRadius(deltaDistance);
            }
            invalidate();

            mHandler.postDelayed(this, frameTime);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int w = getWidth();
        int h = getHeight();

        int maxL = Math.max(w / 2, h / 2);

        if (defRadius == NOT_INITED) {
            defRadius = Math.min(w, h) / 2;
        }

//        int d = maxL - defRadius;
        lengthBetweenCircles = maxL / numOfCircles;
        deltaDistance = lengthBetweenCircles / 3;

        int counter = 0;
        for (AnimCircle c : animCircleList) {
            c.setStartRadius(defRadius - counter * lengthBetweenCircles - deltaDistance);
            ++counter;
        }

        mHandler.removeCallbacks(animatedRunnable);
        mHandler.post(animatedRunnable);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();

        for (AnimCircle c : animCircleList) {

            if (c.getStartRadius() < 0) {
                continue;
            }

            if (c.getStartRadius() >= h / 2 && c.getStartRadius() >= w / 2) {
                c.setStartRadius(0);
                continue;
            }

            if (circleFillPaint != null){
                canvas.drawCircle(w / 2, h / 2, c.getStartRadius(), circleFillPaint);
            }

            canvas.drawCircle(w / 2, h / 2, c.getStartRadius(), circleStrokePaint);
        }
    }

    public static class Builder {

        private AnimatedCircleDeco animatedCircleDeco;

        public Builder(Context context) {
            animatedCircleDeco = new AnimatedCircleDeco(context);
        }

        public Builder setNumOfCircles(int numOfCircles) {
            animatedCircleDeco.numOfCircles = numOfCircles;
            animatedCircleDeco.animCircleList = new ArrayList<>(numOfCircles);

            for (int i = 0; i < numOfCircles; ++i) {
                AnimCircle circle = new AnimCircle();
                animatedCircleDeco.animCircleList.add(circle);
            }

            return this;
        }

        public Builder setWidthOfCircle(int widthOfCircle) {
            animatedCircleDeco.widthOfCircle = widthOfCircle;

            animatedCircleDeco.circleStrokePaint.setStrokeWidth(widthOfCircle);

            return this;
        }

        public Builder setDefRadius(int defRadius) {
            animatedCircleDeco.defRadius = defRadius;

            return this;
        }

        public Builder setFrameTime(int frameTime) {
            animatedCircleDeco.frameTime = frameTime;

            return this;
        }

        public Builder setStrokeColor(int strokeColor) {
            animatedCircleDeco.circleStrokePaint.setColor(strokeColor);

            return this;
        }

        public Builder setColor(int circleColor) {
            animatedCircleDeco.circleFillPaint = new Paint();
            animatedCircleDeco.circleFillPaint.setAntiAlias(true);
            animatedCircleDeco.circleFillPaint.setColor(circleColor);
            animatedCircleDeco.circleFillPaint.setStyle(Paint.Style.FILL);

            return this;
        }

        public AnimatedCircleDeco build() {
            return animatedCircleDeco;
        }

    }

    public int getWidthOfCircle() {
        return widthOfCircle;
    }

    public int getDefRadius() {
        return defRadius;
    }

    public int getNumOfCircles() {
        return numOfCircles;
    }


}