package kizema.anton.animateviewshowcase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kizema.anton.animateviewshowcase.decorators.Decorator;
import kizema.anton.animateviewshowcase.decorators.animcircle.AnimatedCircleDeco;
import kizema.anton.animateviewshowcase.helpers.ColorHelper;
import kizema.anton.animateviewshowcase.helpers.UIHelper;

public class ScrollViewActivity extends AppCompatActivity {

    private ViewGroup parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        parent = (ViewGroup) findViewById(R.id.parent);


        addTextView("Example 1", 5, 1, 100, ColorHelper.COLOR_3);
        addTextView("Example 2", 2, 8, 30, ColorHelper.COLOR_2);

        addImageView(2, 8, 4, ColorHelper.COLOR_3, FrameDecorator.AnimationMode.BACK);

        addTextView("Example Example Example ExampleExample ExampleExample Examplev Example" +
                "ExamplevExampleExample ExampleExampleExampleExample ExampleExample Example Example ", 10, 50, 5, ColorHelper.COLOR_1);

        addImageView(10, 2, 5, ColorHelper.COLOR_3, FrameDecorator.AnimationMode.TOP);

        addIvWithDefaults();
    }

    private void addIvWithDefaults(){
        //Check defaults
        AnimatedCircleDeco deco = new AnimatedCircleDeco.Builder(this).build();
        ImageView iv = new ImageView(this);
        iv.setLayoutParams(new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, UIHelper.getPixel(300)));
        iv.setImageResource(R.mipmap.ic_launcher);
        iv.setScaleType(ImageView.ScaleType.CENTER);
        FrameDecorator frame = new FrameDecorator(iv, deco);
        parent.addView(frame);
    }

    private void addTextView(String text, int num, int widthDpi, int defRadiusInDp, int color){
        TextView tv = new TextView(this);
        tv.setLayoutParams(new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, UIHelper.getPixel(300)));
        tv.setText(text);
        tv.setGravity(Gravity.CENTER);

        Decorator deco = AnimationSetUpHelper.getInstance().buildAnimatedDeco(this, num, widthDpi, defRadiusInDp, color);
        FrameDecorator frame = new FrameDecorator(tv, deco);

        parent.addView(frame);
    }

    private void addImageView(int num, int widthDpi, int defRadiusInDp, int color, FrameDecorator.AnimationMode mode){
        ImageView iv = new ImageView(this);
        iv.setLayoutParams(new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, UIHelper.getPixel(300)));
        iv.setImageResource(R.mipmap.ic_launcher);
        iv.setScaleType(ImageView.ScaleType.CENTER);

        Decorator deco = AnimationSetUpHelper.getInstance().buildAnimatedDeco(this, num, widthDpi, defRadiusInDp, color);
        FrameDecorator frame = new FrameDecorator(iv, deco, mode);

        parent.addView(frame);
    }


}
