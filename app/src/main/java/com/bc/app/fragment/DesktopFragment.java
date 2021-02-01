package com.bc.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bc.app.R;
import com.bc.app.adapter.AppAdapter;
import com.bc.app.entity.App;
import com.chad.library.adapter.base.BaseQuickAdapter;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * 桌面
 *
 * @author zhou
 */
public class DesktopFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.OnItemClickListener, View.OnClickListener, BaseQuickAdapter.OnItemLongClickListener {

    private RecyclerView mAppRv;
    private AppAdapter mAppAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_desktop, container, false);
    }

    private void initView() {
        List<App> list = new ArrayList<>();
        App application = new App();
        application.setName("张三");
        App application2 = new App();
        application2.setName("李四");
        App application3 = new App();
        application3.setName("王五");
        App application4 = new App();
        application4.setName("周六");
        App application5 = new App();
        application5.setName("赵七");
        list.add(application);
        list.add(application2);
        list.add(application3);
        list.add(application4);
        list.add(application5);

        mAppAdapter = new AppAdapter(R.layout.item_app, list);
        mAppRv = getView().findViewById(R.id.rv_app);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        mAppRv.setLayoutManager(manager);
        mAppRv.setAdapter(mAppAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        return false;
    }
}
