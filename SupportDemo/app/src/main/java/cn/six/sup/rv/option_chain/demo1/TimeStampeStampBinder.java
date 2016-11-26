package cn.six.sup.rv.option_chain.demo1;

import android.view.View;
import android.view.ViewGroup;



import java.text.SimpleDateFormat;
import java.util.Date;

import cn.six.sup.rv.option_chain.BaseBinder;

public class TimeStampeStampBinder extends BaseBinder<TimeStampViewHolder> {

    private final String prefix;

    public TimeStampeStampBinder(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public TimeStampViewHolder createViewHolder(ViewGroup parent) {
        View view = getView(TimeStampViewHolder.getLayoutId(), parent);
        return new TimeStampViewHolder(view);
    }

    @Override
    public void bindViewHolder(TimeStampViewHolder holder) {
        System.out.println("szw TimeStampe");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS(Z)"); // S : millisecond; Z time zone
        String str = format.format(new Date());
        holder.tvTimestamp.setText(prefix + str);
    }

}