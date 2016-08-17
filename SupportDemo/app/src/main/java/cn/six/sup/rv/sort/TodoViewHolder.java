package cn.six.sup.rv.sort;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public abstract class TodoViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
    final CheckBox checkBox;
    TaskStatus boundItem;

    public TodoViewHolder(View itemView) {
        super(itemView);
        checkBox = (CheckBox) itemView;
        checkBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (boundItem != null && isChecked != boundItem.isDone) {
            onDoneChanged(isChecked);
        }
    }

    public void bindTo(TaskStatus item) {
        boundItem = item;
        checkBox.setText(item.text);
        checkBox.setChecked(item.isDone);
    }

    abstract void onDoneChanged(boolean isChecked);
}