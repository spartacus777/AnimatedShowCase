package kizema.anton.animateviewshowcase;

import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by somename on 06.09.2016.
 */
public class AnimatedFrame extends FrameLayout {

    private View decoratedVIew;
    private Decorator deco;

    public AnimatedFrame(View decoratedVIew) {
        super(decoratedVIew.getContext());

        this.decoratedVIew = decoratedVIew;
        addView(decoratedVIew);

        deco = new Decorator(decoratedVIew.getContext());
        addView(deco);

        decoratedVIew.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                deco.setLayoutParams(new FrameLayout.LayoutParams(right - left, bottom - top));
            }
        });
    }

}
