package ca.six.and7.scoped_directory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.six.and7.R;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-09-20.
 */

public class DirectoryRecyclerAdapter extends RecyclerView.Adapter<DirectoryRecyclerAdapter.DirectoryViewHolder> {
    private List<String> mItems = new ArrayList<>();
    private Context mContext;

    public DirectoryRecyclerAdapter(Context context, List<String> items){
        mContext = context;
        mItems = items;
    }

    public void setDataSource(List<String> items){
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public DirectoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DirectoryViewHolder(LayoutInflater.from(mContext).inflate(R.layout.spinner_item, parent, false));
    }

    @Override
    public void onBindViewHolder(DirectoryViewHolder holder, int position) {
        holder.tvItem.setText(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    final class DirectoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        DirectoryViewHolder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
