package cn.six.sup.rv.load_more.mock;

import android.os.AsyncTask;
import android.os.SystemClock;

/**
 * Created by songzhw on 2016-08-22
 */
public class MockTask extends AsyncTask<Integer, Void, MockInfo> {
    private IPost listener;

    public MockTask(IPost listener) {
        this.listener = listener;
    }

    @Override
    protected MockInfo doInBackground(Integer... params) {
        SystemClock.sleep(1000);
        MockInfo ret = MockLoadMore.getData(params[0]);
        return ret;
    }

    @Override
    protected void onPostExecute(MockInfo mockInfo) {
        listener.onResp(mockInfo);
    }

    public interface IPost {
        void onResp(MockInfo info);
    }
}
