package kizema.anton.animateviewshowcase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kizema.anton.animateviewshowcase.decorators.Decorator;
import kizema.anton.animateviewshowcase.helpers.AnimationSetUpHelper;
import kizema.anton.animateviewshowcase.helpers.ColorHelper;
import kizema.anton.animateviewshowcase.helpers.UIHelper;

public class MainActivity extends AppCompatActivity {

    private ViewGroup parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent = (ViewGroup) findViewById(R.id.parent);


        View v = addTextView("Scroll View example", 10, 1, 100, ColorHelper.COLOR_3);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ScrollViewActivity.class);
                startActivity(i);
            }
        });

        View v1 = addTextView("Recycler View example", 3, 2, 10, ColorHelper.COLOR_1);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(i);
            }
        });
    }


    private View addTextView(String text, int num, int widthDpi, int defRadiusInDp, int color){
        TextView tv = new TextView(this);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        p.addRule(RelativeLayout.CENTER_HORIZONTAL);
        tv.setLayoutParams(p);
        tv.setPadding(UIHelper.getPixel(60), UIHelper.getPixel(20), UIHelper.getPixel(60), UIHelper.getPixel(20));
        tv.setText(text);
        tv.setGravity(Gravity.CENTER);

        Decorator deco = AnimationSetUpHelper.getInstance().buildAnimatedDeco(this, num, widthDpi, defRadiusInDp, color);
        FrameDecorator frame = new FrameDecorator(tv, deco);

        parent.addView(frame);

        return frame;
    }

}
