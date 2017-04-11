package cn.six.sup.other.async_inflate;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.AsyncLayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.six.sup.R;

public class AsyncViewDemo extends Activity implements AsyncLayoutInflater.OnInflateFinishedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AsyncLayoutInflater inflater = new AsyncLayoutInflater(this);
        inflater.inflate(R.layout.activity_tv_btn, null, this);
    }

    @Override
    public void onInflateFinished(View view, int resid, ViewGroup parent) {
        System.out.println("szw async inflate succ");
        setContentView(view);
    }
}