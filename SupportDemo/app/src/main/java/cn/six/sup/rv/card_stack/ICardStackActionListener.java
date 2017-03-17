package cn.six.sup.rv.card_stack;

import android.support.v7.widget.RecyclerView;

/**
 * Created by songzhw on 2017-03-16
 */

public interface ICardStackActionListener<T> {
    void onSwiping(RecyclerView.ViewHolder vh, float ratio, @CardsDirection int direction);
    void onSwiped(RecyclerView.ViewHolder vh, T t, @CardsDirection int direction);
    void onSwipedClear();
}
