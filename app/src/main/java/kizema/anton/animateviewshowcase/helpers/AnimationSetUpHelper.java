package kizema.anton.animateviewshowcase.helpers;

import android.content.Context;

import kizema.anton.animateviewshowcase.decorators.Decorator;
import kizema.anton.animateviewshowcase.decorators.animcircle.AnimatedCircleDeco;

public class AnimationSetUpHelper {

    private static AnimationSetUpHelper instance;

    public static synchronized AnimationSetUpHelper getInstance(){
        if (instance == null){
            instance = new AnimationSetUpHelper();
        }

        return instance;
    }

    public Decorator buildAnimatedDeco(Context c, int circleNum, int widthDpi, int defRadiusInDp, int color){
        return buildAnimatedDeco(c, circleNum, widthDpi, defRadiusInDp, color, -1);
    }

    public Decorator buildAnimatedDeco(Context c, int circleNum, int widthDpi, int defRadiusInDp, int color, int strokeColor){
        AnimatedCircleDeco.Builder builder = new AnimatedCircleDeco.Builder(c)
                .setNumOfCircles(circleNum)
                .setWidthOfCircle(UIHelper.getDPI(widthDpi))
                .setDefRadius(UIHelper.getPixel(defRadiusInDp))
                .setColor(color);

        if (strokeColor != -1) {
            builder.setStrokeColor(strokeColor);
        }

        return builder.build();
    }
}
