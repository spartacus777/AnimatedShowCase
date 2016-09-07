package kizema.anton.animateviewshowcase;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import kizema.anton.animateviewshowcase.decorators.animcircle.AnimatedCircleDeco;
import kizema.anton.animateviewshowcase.helpers.ColorHelper;
import kizema.anton.animateviewshowcase.helpers.UIHelper;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.BaseViewHolder> {

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

    public static class BaseViewHolder extends RecyclerView.ViewHolder {

        public AnimatedCircleDeco deco;

        public BaseViewHolder(View itemView, AnimatedCircleDeco deco) {
            super(itemView);

            this.deco = deco;
        }
    }

    public static class ImageViewHolder extends BaseViewHolder {

        public ImageView ivImageView;

        public ImageViewHolder(View itemView, AnimatedCircleDeco deco) {
            super(itemView, deco);

            ivImageView = (ImageView) itemView.findViewWithTag(tagIv);
        }
    }

    public static class TextViewHolder extends BaseViewHolder {

        public TextView tvTitleId;

        public TextViewHolder(View itemView, AnimatedCircleDeco deco) {
            super(itemView, deco);

            tvTitleId = (TextView) itemView.findViewWithTag(tagTv);
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

                AnimatedCircleDeco deco = (AnimatedCircleDeco) AnimationSetUpHelper.getInstance().buildAnimatedDeco(parent.getContext(), 5, 3, 6, ColorHelper.COLOR_3);
                FrameDecorator frame = new FrameDecorator(tv, deco);

                return new TextViewHolder(frame, deco);
            }
            case TYPE_IV: {
                ImageView iv = new ImageView(parent.getContext());
                iv.setLayoutParams(new RelativeLayout.LayoutParams
                        (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                iv.setImageResource(R.mipmap.ic_launcher);
                iv.setPadding(UIHelper.getPixel(60), UIHelper.getPixel(20), UIHelper.getPixel(60), UIHelper.getPixel(20));
                iv.setScaleType(ImageView.ScaleType.CENTER);
                iv.setTag(tagIv);

                AnimatedCircleDeco deco = (AnimatedCircleDeco)
                        AnimationSetUpHelper.getInstance().buildAnimatedDeco(parent.getContext(),
                                10, 1, 15, ColorHelper.COLOR_1);
                FrameDecorator frame = new FrameDecorator(iv, deco, FrameDecorator.AnimationMode.BACK);

                return new ImageViewHolder(frame, deco);
            }
        }

        return null;
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.deco.forceStartAnimation();
    }

    @Override
    public void onViewDetachedFromWindow(BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.deco.forceStopAnimation();
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {

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
