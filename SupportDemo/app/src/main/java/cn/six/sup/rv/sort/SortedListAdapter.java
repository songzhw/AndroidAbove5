package cn.six.sup.rv.sort;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.six.sup.R;

public class SortedListAdapter extends RecyclerView.Adapter<TodoViewHolder> {
    final LayoutInflater layoutInflater;
    SortedList<TaskStatus> data;

    public SortedListAdapter(LayoutInflater layoutInflater, TaskStatus... items) {
        this.layoutInflater = layoutInflater;
        data = new SortedList<>(TaskStatus.class, new SortedListAdapterCallback<TaskStatus>(this) {
            @Override
            public int compare(TaskStatus t0, TaskStatus t1) {
                if (t0.isDone != t1.isDone) {
                    return t0.isDone ? 1 : -1;
                }
                int txtComp = t0.text.compareTo(t1.text);
                if (txtComp != 0) {
                    return txtComp;
                }
                if (t0.id < t1.id) {
                    return -1;
                } else if (t0.id > t1.id) {
                    return 1;
                }
                return 0;
            }

            @Override
            public boolean areContentsTheSame(TaskStatus oldItem, TaskStatus newItem) {
                return oldItem.text.equals(newItem.text);
            }

            @Override
            public boolean areItemsTheSame(TaskStatus item1, TaskStatus item2) {
                return item1.id == item2.id;
            }
        });
        for (TaskStatus item : items) {
            data.add(item);
        }
    }

    public void addItem(TaskStatus item) {
        data.add(item);
    }

    @Override
    public TodoViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_sorted_list, parent, false);
        return new TodoViewHolder(view) {
            @Override
            void onDoneChanged(boolean isDone) {
                int adapterPosition = getAdapterPosition();
                if (adapterPosition == RecyclerView.NO_POSITION) {
                    return;
                }
                boundItem.isDone = isDone;
                data.recalculatePositionOfItemAt(adapterPosition);
            }
        };
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        holder.bindTo(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

