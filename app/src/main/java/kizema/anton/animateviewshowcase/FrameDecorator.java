package kizema.anton.animateviewshowcase;

import android.view.View;
import android.widget.FrameLayout;

import kizema.anton.animateviewshowcase.decorators.Decorator;

public class FrameDecorator extends FrameLayout {

    private View decoratedView;
    private Decorator deco;

    public enum AnimationMode {
        TOP, BACK;
    }

    public FrameDecorator(View decoratedView, Decorator decorator) {
        this(decoratedView, decorator, AnimationMode.TOP);

    }

    public FrameDecorator(View decoratedView, Decorator decorator, AnimationMode order) {
        super(decoratedView.getContext());

        this.decoratedView = decoratedView;
        this.deco = decorator;

        if (order == AnimationMode.TOP) {
            addView(decoratedView);
            addView(deco);
        } else {
            addView(deco);
            addView(decoratedView);
        }

        decoratedView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                deco.setLayoutParams(new FrameLayout.LayoutParams(right - left, bottom - top));
            }
        });
    }

}
