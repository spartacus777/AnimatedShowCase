package kizema.anton.animateviewshowcase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kizema.anton.animateviewshowcase.decorators.AnimatedCircleDeco;
import kizema.anton.animateviewshowcase.decorators.Decorator;
import kizema.anton.animateviewshowcase.helpers.UIHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View v = findViewById(R.id.tv);

//        AnimatedView view = new AnimatedView(v.getContext(), v);

        TextView tv = new TextView(this);
        tv.setLayoutParams(new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        tv.setText("Example deco view");

        ViewGroup parent = (ViewGroup) findViewById(R.id.parent);

        Decorator deco = new AnimatedCircleDeco.Builder(this).setNumOfCircles(3).
                setWidthOfCircle(UIHelper.getDPI(10)).setFrameTime(500).setDefRadius(UIHelper.getPixel(20)).build();
        FrameDecorator frame = new FrameDecorator(tv, deco);

        parent.addView(frame);
    }
}
