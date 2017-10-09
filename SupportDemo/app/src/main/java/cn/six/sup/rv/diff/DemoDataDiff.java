package cn.six.sup.rv.diff;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class DemoDataDiff extends DiffUtil.Callback {
    private List<String> oldData, newData;

    public DemoDataDiff(List<String> oldData, List<String> newData) {
        this.oldData = oldData;
        this.newData = newData;
    }

    @Override
    public int getOldListSize() {
        return oldData == null ? 0 : oldData.size();
    }

    @Override
    public int getNewListSize() {
        return newData == null ? 0 : newData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition).getClass().equals(
                newData.get(newItemPosition).getClass());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
    }
}
