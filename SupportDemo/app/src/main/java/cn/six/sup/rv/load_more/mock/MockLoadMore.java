package cn.six.sup.rv.load_more.mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songzhw on 2016-08-22
 */
public class MockLoadMore {
    public static boolean[] hasMoreArray = new boolean[]{
            true, true, false
    };

    public static MockInfo getData(int page) {
        if (page > 2) {
            return null;
        }

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("Item : " + page + "" + i);
        }

        MockInfo ret = new MockInfo(data, hasMoreArray[page]);
        return ret;
    }
}
