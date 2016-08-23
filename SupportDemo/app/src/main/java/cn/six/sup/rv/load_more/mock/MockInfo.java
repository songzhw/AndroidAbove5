package cn.six.sup.rv.load_more.mock;

import java.util.List;

/**
 * Created by songzhw on 2016-08-22
 */
public class MockInfo {
    public List<String> data;
    public boolean hasMore;

    public MockInfo(List<String> data, boolean hasMore) {
        this.data = data;
        this.hasMore = hasMore;
    }
}
