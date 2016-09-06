package kizema.anton.animateviewshowcase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View v = findViewById(R.id.tv);

//        AnimatedView view = new AnimatedView(v.getContext(), v);

        TextView tv = new TextView(this);
        tv.setLayoutParams(new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        tv.setText("Example deco view");

        ViewGroup parent = (ViewGroup) findViewById(R.id.parent);
        AnimatedFrame frame = new AnimatedFrame(tv);

        parent.addView(frame);
    }
}
