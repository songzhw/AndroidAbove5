package cn.six.sup.rv.composition;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class BaseRow {

    public int getViewType(){
        return 0;
    }

    public RecyclerView.ViewHolder getViewHolder(ViewGroup parent){
        return null;
    }

    public void bind(){

    }
}
