package cn.six.sup.rv.group;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;


public class OldRvGroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_CONTENT = 0;
    private static final int TYPE_TITLE = 1;
    private List<String> data = new ArrayList<>();
    private Context ctx;


    public OldRvGroupAdapter(Context mContext, List<String> mData) {
        this.ctx = mContext;
        this.data = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(ctx);
        switch (viewType) {
            case TYPE_CONTENT:
                ViewGroup vImage = (ViewGroup) mInflater.inflate(R.layout.item_rv_group_content, parent, false);
                ImageViewHolder vhImage = new ImageViewHolder(vImage);
                return vhImage;
            case TYPE_TITLE:
                ViewGroup vGroup = (ViewGroup) mInflater.inflate(R.layout.item_rv_group_title, parent, false);
                GroupViewHolder vhGroup = new GroupViewHolder(vGroup);
                return vhGroup;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_CONTENT:
                ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
                imageViewHolder.ivContent.setImageResource(R.drawable.ic_launcher);
                imageViewHolder.tvContent.setText(data.get(position));
                break;
            case TYPE_TITLE:
                GroupViewHolder groupViewHolder = (GroupViewHolder) holder;
                groupViewHolder.tvTitle.setText(data.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if (!isTitle(position)) {
            viewType = TYPE_CONTENT;
        } else {
            viewType = TYPE_TITLE;
        }
        return viewType;
    }


    private boolean isTitle(int pos) {
        if (data.get(pos).startsWith("this is title:")) {
            return true;
        }
        return false;
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        public GroupViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.text);
        }

    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent;
        ImageView ivContent;
        public ImageViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.text);
            ivContent = (ImageView) itemView.findViewById(R.id.image);
        }
    }

}  