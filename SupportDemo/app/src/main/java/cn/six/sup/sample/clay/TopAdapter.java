package cn.six.sup.sample.clay;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;

public class TopAdapter extends OneAdapter<ShowcaseItem> {

        public float scale = 1.0f, transitionFactor = 0.0f;
        private int transitionOffset = 0;

        public TopAdapter(int layoutResId) {
            super(layoutResId);
        }


        public void setAnimationFactor(float factor) {
            transitionFactor = factor;
            scale = 1.0f - (factor * 0.3f);
            notifyDataSetChanged();
        }

        @Override
        protected void apply(RvViewHolder vh, ShowcaseItem showcaseItem, int position) {
            Context ctx = vh.itemView.getContext();
            if(transitionOffset == 0){
                transitionOffset = (int)ctx.getResources().getDimension(R.dimen.anim_trans_y);
            }


            TextView tv = vh.getView(R.id.tv_top_name);
            ImageView iv = vh.getView(R.id.iv_top_icon);

            iv.setImageResource(showcaseItem.icon);
            iv.setScaleX(scale);
            iv.setScaleY(scale);
            iv.setTranslationY(transitionFactor * transitionOffset);

            tv.setText(showcaseItem.text);
            tv.setAlpha(1 - transitionFactor * 2f);
            tv.setTranslationY( -42 * transitionFactor);


            vh.setText(R.id.tv_top_name, showcaseItem.text);
            vh.setSrc(R.id.iv_top_icon, showcaseItem.icon);
        }

    }
