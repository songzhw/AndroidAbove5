package cn.six.sup.rv.card_stack;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({CardStackConstant.LEFT, CardStackConstant.RIGHT, CardStackConstant.NONE})
@Retention(RetentionPolicy.SOURCE)
public @interface CardsDirection {
}