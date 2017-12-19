package cn.six.sup.rv.dragdrop.simple;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.six.sup.R;


public class DragDropRvAdapter extends RecyclerView.Adapter<DragDropRvAdapter.ViewHolder> {

    //    private Context context;
    private List<String> results;

    public DragDropRvAdapter(List<String> results) {
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        this.context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_drag_drop, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.iv.setImageResource(R.drawable.ic_launcher);
        holder.tv.setText(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

//
//    @Override
//    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        return 0;
//    }
//
//    @Override
//    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//        int fromPosition = viewHolder.getAdapterPosition();
//        int toPosition = target.getAdapterPosition();
//        if (fromPosition==results.size()-1 || toPosition==results.size()-1){
//            return true;
//        }
//        if (fromPosition < toPosition) {
//            for (int i = fromPosition; i < toPosition; i++) {
//                Collections.swap(results, i, i + 1);
//            }
//        } else {
//            for (int i = fromPosition; i > toPosition; i--) {
//                Collections.swap(results, i, i - 1);
//            }
//        }
//        notifyItemMoved(fromPosition, toPosition);
//        return true;
//    }
//
//    @Override
//    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//        int position = viewHolder.getAdapterPosition();
//        results.remove(position);
//        notifyItemRemoved(position);
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;
        public ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tvDDRvItem);
            iv = (ImageView) itemView.findViewById(R.id.ivDDRvItem);
        }
    }
}