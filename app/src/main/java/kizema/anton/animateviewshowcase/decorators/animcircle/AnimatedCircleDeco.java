package kizema.anton.animateviewshowcase.decorators.animcircle;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

import kizema.anton.animateviewshowcase.decorators.Decorator;
import kizema.anton.animateviewshowcase.helpers.UIHelper;

public class AnimatedCircleDeco extends Decorator {

    private static final int NOT_INITED = -1;
    private static final int NUM_CIRCLES_DEF = 2;
    private static final int ANIMATION_TIME_DEF = 5000;

    private Paint circleStrokePaint;
    private Paint circleFillPaint;

    //configs
    private int widthOfCircle = UIHelper.getDPI(1);
    private int defRadius = NOT_INITED;
    private int numOfCircles = NUM_CIRCLES_DEF;
    private int animationTime = ANIMATION_TIME_DEF;

    private int lengthBetweenCircles;

    private List<AnimCircle> animCircleList;

    private ValueAnimator anim;

    private BackgroundLooperThread backgroundLooperThread;

    private AnimatedCircleDeco(Context context) {
        super(context);

        circleStrokePaint = new Paint();
        circleStrokePaint.setAntiAlias(true);
        circleStrokePaint.setColor(Color.BLACK);
        circleStrokePaint.setStyle(Paint.Style.STROKE);
        circleStrokePaint.setStrokeWidth(widthOfCircle);

        backgroundLooperThread = new BackgroundLooperThread();
        backgroundLooperThread.start();

        addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                startAnim();
            }
        });
    }

    public void forceStartAnimation(){
        startAnim();
    }

    public void forceStopAnimation(){
        stopAnimation();
    }

    private void stopAnimation(){
        backgroundLooperThread.cancelAnimAsync();
    }

    private void stopAnimPrivate(){
        if (anim != null) {
            anim.cancel();
        }
    }

    private void startAnim(){
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();

        int maxL = Math.max(w / 2, h / 2);

        if (defRadius == NOT_INITED) {
            defRadius = Math.min(w, h) / 2;
        }

        lengthBetweenCircles = maxL / numOfCircles;

        int counter = 0;
        for (AnimCircle c : animCircleList) {
            c.setStartRadius(defRadius - counter * lengthBetweenCircles);
            ++counter;
        }

        backgroundLooperThread.launchAnimAsync();
    }

    private class BackgroundLooperThread extends HandlerThread {

        private static final int DO_JOB = 1;
        private static final int CANCEL_ANIM_JOB = 2;

        private Handler mHandler;

        public BackgroundLooperThread() {
            super("BackgroundLooperThread");
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();

            mHandler = new Handler(getLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case DO_JOB:
                            launchAnimation();
                            break;
                        case CANCEL_ANIM_JOB:
                            stopAnimPrivate();
                            break;
                    }
                }
            };
        }

        public void launchAnimAsync() {
            if (mHandler != null) {
                mHandler.sendEmptyMessage(DO_JOB);
            }
        }

        public void cancelAnimAsync() {
            if (mHandler != null) {
                mHandler.sendEmptyMessage(CANCEL_ANIM_JOB);
            }
        }
    }

    private void launchAnimation() {
        int w = getWidth();
        int h = getHeight();

        final int maxL = Math.max(w / 2, h / 2);

        stopAnimPrivate();
        anim = ValueAnimator.ofInt(0, maxL);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            private int previusVal = 0;

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();

                if (val < previusVal){
                    previusVal = 0;
                }

                int tmp = val;
                val -= previusVal;
                previusVal = tmp;

                for (AnimCircle c : animCircleList) {
                    c.incrementStartRadius(val, maxL);
                }

                postInvalidate();
            }
        });
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(animationTime).setRepeatCount(-1);
        anim.start();

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

            if (circleFillPaint != null) {
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

        public Builder setAnimationTime(int animationTime){
            animatedCircleDeco.animationTime = animationTime;

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
            animatedCircleDeco.animCircleList = new ArrayList<>(animatedCircleDeco.numOfCircles);

            for (int i = 0; i < animatedCircleDeco.numOfCircles; ++i) {
                AnimCircle circle = new AnimCircle();
                animatedCircleDeco.animCircleList.add(circle);
            }

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
