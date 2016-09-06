package kizema.anton.animateviewshowcase;

import android.view.View;
import android.widget.FrameLayout;

import kizema.anton.animateviewshowcase.decorators.Decorator;

/**
 * Created by somename on 06.09.2016.
 */
public class FrameDecorator extends FrameLayout {

    private View decoratedVIew;
    private Decorator deco;

    public FrameDecorator(View decoratedView, Decorator decorator) {
        super(decoratedView.getContext());

        this.decoratedVIew = decoratedView;
        this.deco = decorator;

        addView(decoratedView);
        addView(deco);

        decoratedView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                deco.setLayoutParams(new FrameLayout.LayoutParams(right - left, bottom - top));
            }
        });
    }

}
