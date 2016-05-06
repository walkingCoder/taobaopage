package com.page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by lsx on 16/5/6.
 */
public class TopFragment extends Fragment {

    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_top, container, false);
        mListView  = (ListView) v.findViewById(R.id.listviewTop);
        initData();
        return v;
    }

    private void initData(){

        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.footer, null);
        mListView.addFooterView(footerView);

        MyAdapter adapter = new MyAdapter(getContext());
        mListView.setAdapter(adapter);
    }
}
