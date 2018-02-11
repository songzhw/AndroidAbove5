package cn.six.sup.rv.dragdrop.groups_albe;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.six.sup.R;

import static cn.six.sup.rv.dragdrop.groups.Company.Country_CHINA;
import static cn.six.sup.rv.dragdrop.groups.Company.TYPE_TITLE;


public class DragDropRvAdapter3 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<Company3> data;

    public DragDropRvAdapter3(List<Company3> results) {
        this.data = results;
    }

    @Override
    public int getItemViewType(int position) {
        Company3 company = data.get(position);
        return company.type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TITLE) {
            int layoutResId = R.layout.item_simple_tv;
            View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
            return new TitleViewHolder(itemView);
        } else {
            int layoutResId = R.layout.item_rv_drag_drop;
            View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
            return new ContentViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_TITLE) {
            TitleViewHolder vh = (TitleViewHolder) holder;
            vh.tvTitle.setText(data.get(position).name);
        } else {
            ContentViewHolder vh = (ContentViewHolder) holder;
            vh.iv.setImageResource(R.drawable.ic_launcher);
            vh.tv.setText(data.get(position).name + " ; "+ (data.get(position).country == Country_CHINA ? "China" : "US"));
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ContentViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public ImageView iv;

        public ContentViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tvDDRvItem);
            iv = (ImageView) itemView.findViewById(R.id.ivDDRvItem);
        }
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;

        public TitleViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvGroupTitle);
        }
    }
}
