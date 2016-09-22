package ca.six.and7.scoped_directory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ca.six.and7.R;

/**
 * @copyright six.ca
 * Created by Xiaolin on 2016-09-20.
 */

public class DirectorySpinnerAdapter extends ArrayAdapter<String>{
    private List<String> mItemResource;
    private Context mContext;

    public DirectorySpinnerAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        mItemResource = objects;
        mContext = context;
    }

    public void setDataSource(List<String> data) {
        mItemResource = data;
        notifyDataSetChanged();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        SpinnerViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new SpinnerViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.spinner_item, parent, false);
            viewHolder.tvItem = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SpinnerViewHolder) convertView.getTag();
        }
        viewHolder.tvItem.setText(mItemResource.get(position));

        return convertView;
    }

    private final class SpinnerViewHolder {
        TextView tvItem;
    }

}
