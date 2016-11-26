package cn.six.sup.rv.option_chain;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseBindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getDataBinder(viewType).createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        getItems().get(position).bindViewHolder(viewHolder);
    }

    @Override
    public final int getItemCount() {
        return getItems().size();
    }

    @Override
    public final int getItemViewType(int position) {
        return getItems().get(position).getViewType();
    }

    private <T extends BaseBinder> T getDataBinder(int viewType) {
        for (BaseBinder binder : getItems()) {
            if (binder.getViewType() == viewType) {
                return (T) binder;
            }
        }
        return null;
    }

    public abstract List<BaseBinder> getItems();

}