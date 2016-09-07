package kizema.anton.animateviewshowcase;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kizema.anton.animateviewshowcase.decorators.Decorator;
import kizema.anton.animateviewshowcase.decorators.animcircle.AnimatedCircleDeco;
import kizema.anton.animateviewshowcase.helpers.UIHelper;

public class MainActivity extends AppCompatActivity {

    private ViewGroup parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = (ViewGroup) findViewById(R.id.parent);


        addTextView("Example 1", 5);
        addTextView("Example 2", 2);

        addTextView("Example Example Example ExampleExample ExampleExample Examplev Example" +
                "ExamplevExampleExample ExampleExampleExampleExample ExampleExample Example Example ", 10);
    }

    private void addTextView(String text, int num){
        TextView tv = new TextView(this);
        tv.setLayoutParams(new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, UIHelper.getPixel(300)));
        tv.setText(text);
        tv.setGravity(Gravity.CENTER);

        Decorator deco = new AnimatedCircleDeco.Builder(this)
                .setNumOfCircles(num)
                .setWidthOfCircle(UIHelper.getDPI(5))
                .setFrameTime(300)
                .setDefRadius(UIHelper.getPixel(20))
                .setColor(Color.parseColor("#166ee8d8"))
//                .setStrokeColor(Color.parseColor("#6ee8d8"))
                .build();
        FrameDecorator frame = new FrameDecorator(tv, deco);

        parent.addView(frame);
    }
}
