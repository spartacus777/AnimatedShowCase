package kizema.anton.animateviewshowcase.decorators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import kizema.anton.animateviewshowcase.decorators.animcircle.AnimCircle;
import kizema.anton.animateviewshowcase.helpers.UIHelper;

public class AnimatedCircleDeco extends Decorator {

    private static final int NOT_INITED = -1;
    private static final int DEF_FRAME_TIME = -1;

    Paint circlePaint;

    Handler mHandler = new Handler();

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

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.STROKE);
//        vertexPaint.setStrokeJoin(Paint.Join.ROUND);
//        vertexPaint.setStrokeCap(Paint.Cap.ROUND);
        circlePaint.setStrokeWidth(widthOfCircle);

        animatedRunnable = new AnimatedRunnable();

        animCircleList = new ArrayList<>(numOfCircles);
    }

    private class AnimatedRunnable implements Runnable {

        @Override
        public void run() {
//            defRadius += deltaDistance;

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

        int d = maxL - defRadius;
        lengthBetweenCircles = d / numOfCircles;
        deltaDistance = lengthBetweenCircles / 3;

        int counter = 0;
        for (AnimCircle c : animCircleList) {
            c.setStartRadius(defRadius - counter * lengthBetweenCircles - deltaDistance);
            ++counter;
        }

//        defRadius -= deltaDistance;

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

            canvas.drawCircle(w / 2, h / 2, c.getStartRadius(), circlePaint);
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

    public static class Builder {

        private AnimatedCircleDeco animatedCircleDeco;

        private boolean setDefRadiusWasCalled = false;

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

            animatedCircleDeco.circlePaint.setStrokeWidth(widthOfCircle);

            return this;
        }

        public Builder setDefRadius(int defRadius) {
            setDefRadiusWasCalled = true;

            animatedCircleDeco.defRadius = defRadius;

//            int counter = 0;
//            for (AnimCircle c : animatedCircleDeco.animCircleList){
//                c.setStartRadius(defRadius - counter);
//            }

            return this;
        }

        public Builder setFrameTime(int frameTime) {
            animatedCircleDeco.frameTime = frameTime;

            return this;
        }

        public AnimatedCircleDeco build() {
            if (!setDefRadiusWasCalled) {
                //TODO !!!!!!!!!!!!!!!!
//                setDefRadius(AnimatedCircleDeco.NOT_INITED);
            }
            return animatedCircleDeco;
        }

    }

}
