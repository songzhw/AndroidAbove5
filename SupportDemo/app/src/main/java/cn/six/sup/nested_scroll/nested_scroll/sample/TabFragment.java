package cn.six.sup.nested_scroll.nested_scroll.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.six.sup.R;
import cn.six.sup.rv.RvViewHolder;
import cn.six.sup.rv.one_adapter.OneAdapter;

public class TabFragment extends Fragment {
    public static final String TITLE = "title";
    private String title = "Defaut Value";
    private RecyclerView recyclerView;
    // private TextView mTextView;
    private List<String> dataList = new ArrayList<String>();

    public static TabFragment newInstance(String title) {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.id_stickynavlayout_innerscrollview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        for (int i = 0; i < 50; i++) {
            dataList.add(title + " -> " + i);
        }
        OneAdapter<String> adapter = new OneAdapter<String>(R.layout.item_nest_scroll) {
            @Override
            protected void apply(RvViewHolder vh, String s, int position) {
                vh.setText(R.id.id_info, s);
            }
        };
        adapter.data = dataList;
        recyclerView.setAdapter(adapter);
        return view;

    }

}