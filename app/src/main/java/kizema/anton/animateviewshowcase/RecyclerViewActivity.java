package kizema.anton.animateviewshowcase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView rvEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        rvEntries = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager mChatLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rvEntries.setLayoutManager(mChatLayoutManager);

        List<DemoAdapter.DEMO> busModels = new ArrayList<>();

        final Random rand = new Random();

        for (int i=0; i< 100; ++i) {
            DemoAdapter.DEMO d = new DemoAdapter.DEMO();

            int r = rand.nextInt(2);
            if (r == 0){
                d.text = "" + rand.nextInt(32000);
            }

            d.type = r;

            busModels.add(d);
        }

        DemoAdapter adapter = new DemoAdapter(busModels);

        rvEntries.setAdapter(adapter);
        rvEntries.setHasFixedSize(true);
    }

}
