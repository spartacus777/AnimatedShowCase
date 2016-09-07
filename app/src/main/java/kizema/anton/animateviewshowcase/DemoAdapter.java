package kizema.anton.animateviewshowcase;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import kizema.anton.animateviewshowcase.decorators.Decorator;
import kizema.anton.animateviewshowcase.helpers.ColorHelper;
import kizema.anton.animateviewshowcase.helpers.UIHelper;

public class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_TV = 1;
    private static final int TYPE_IV = 2;

    private static Object tagTv = new Object();
    private static Object tagIv = new Object();

    public static class DEMO{

        //0 tv, 1 iv
        public int type = 0;

        public String text;
    }

    private List<DEMO> busModels;

    public DemoAdapter(List<DEMO> busModels) {
        this.busModels = busModels;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {

        DEMO model = busModels.get(position);

        if (model.type == 0){
            return TYPE_TV;
        }

        return TYPE_IV;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivImageView;

        public ImageViewHolder(View itemView) {
            super(itemView);

            ivImageView = (ImageView) itemView.findViewWithTag(tagIv);
        }
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitleId;

        public TextViewHolder(View itemView) {
            super(itemView);

            tvTitleId = (TextView) itemView.findViewWithTag(tagTv);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_TV: {

                TextView tv = new TextView(parent.getContext());
                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams
                        (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                p.addRule(RelativeLayout.CENTER_HORIZONTAL);
                tv.setTag(tagTv);
                tv.setLayoutParams(p);
                tv.setPadding(UIHelper.getPixel(60), UIHelper.getPixel(20), UIHelper.getPixel(60), UIHelper.getPixel(20));
                tv.setGravity(Gravity.CENTER);

                Decorator deco = AnimationSetUpHelper.getInstance().buildAnimatedDeco(parent.getContext(), 5, 3, 6, ColorHelper.COLOR_3);
                FrameDecorator frame = new FrameDecorator(tv, deco);

                return new TextViewHolder(frame);
            }
            case TYPE_IV: {
                ImageView iv = new ImageView(parent.getContext());
                iv.setLayoutParams(new RelativeLayout.LayoutParams
                        (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                iv.setImageResource(R.mipmap.ic_launcher);
                iv.setPadding(UIHelper.getPixel(60), UIHelper.getPixel(20), UIHelper.getPixel(60), UIHelper.getPixel(20));
                iv.setScaleType(ImageView.ScaleType.CENTER);
                iv.setTag(tagIv);

                Decorator deco = AnimationSetUpHelper.getInstance().buildAnimatedDeco(parent.getContext(), 10, 1, 2, ColorHelper.COLOR_1);
                FrameDecorator frame = new FrameDecorator(iv, deco);

                return new ImageViewHolder(frame);
            }
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        DEMO model = busModels.get(position);

        if (holder instanceof TextViewHolder){
            ((TextViewHolder) holder).tvTitleId.setText(model.text);
        }

        if (holder instanceof ImageViewHolder){
            ((ImageViewHolder) holder).ivImageView.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public int getItemCount() {
        if (busModels == null){
            return 0;
        }

        return busModels.size();
    }

}
