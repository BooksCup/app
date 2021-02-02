package com.bc.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.bc.app.R;
import com.bc.app.adapter.AppAdapter;
import com.bc.app.cons.Constant;
import com.bc.app.entity.App;
import com.bc.app.utils.VolleyUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 桌面
 *
 * @author zhou
 */
public class DesktopFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.OnItemClickListener, View.OnClickListener, BaseQuickAdapter.OnItemLongClickListener {

    @BindView(R.id.rv_app)
    RecyclerView mAppRv;

    @BindView(R.id.srl_app)
    SwipeRefreshLayout mAppSrl;

    private AppAdapter mAppAdapter;
    private VolleyUtil mVolleyUtil;
    private List<App> mAppList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_desktop, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void initView() {
        mVolleyUtil = VolleyUtil.getInstance(getActivity());
        mAppAdapter = new AppAdapter(R.layout.item_app, mAppList);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        mAppRv.setLayoutManager(manager);
        mAppRv.setAdapter(mAppAdapter);

        mAppSrl.setOnRefreshListener(this);
        mAppSrl.setRefreshing(true);
        getAppList();
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
        mAppSrl.setRefreshing(true);
        getAppList();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        return false;
    }

    private void getAppList() {

        String url = Constant.BASE_URL + "app";

        mVolleyUtil.get(url, response -> {
            final List<App> appList = JSONArray.parseArray(response, App.class);
            mAppAdapter.setNewData(appList);
            mAppSrl.setRefreshing(false);
        }, volleyError -> {
            mAppSrl.setRefreshing(false);
        });
    }
}
