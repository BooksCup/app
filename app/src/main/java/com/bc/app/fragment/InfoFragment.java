package com.bc.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.bc.app.R;
import com.bc.app.adapter.WeavePriceAdapter;
import com.bc.app.cons.Constant;
import com.bc.app.entity.info.DataProfile;
import com.bc.app.entity.info.WeavePrice;
import com.bc.app.utils.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 资讯
 *
 * @author zhou
 */
public class InfoFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.srl_info)
    SwipeRefreshLayout mInfoSrl;

    @BindView(R.id.rv_info)
    RecyclerView mInfoRv;

    private List<WeavePrice> mWeavePriceList = new ArrayList<>();
    private WeavePriceAdapter mAdapter;

    private VolleyUtil mVolleyUtil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initView() {
        mVolleyUtil = VolleyUtil.getInstance(getActivity());

        mInfoSrl.setOnRefreshListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mInfoRv.setLayoutManager(manager);

        mAdapter = new WeavePriceAdapter(R.layout.item_weave_price, mWeavePriceList);
//        mAdapter.setOnItemClickListener(this);
        mInfoRv.setAdapter(mAdapter);
        getData();
    }

    private void getData() {
        String url = Constant.CRAWLER_URL + "dataProfile/v2";

        mVolleyUtil.get(url, response -> {
            final DataProfile dataProfile = JSONArray.parseObject(response, DataProfile.class);
            List<WeavePrice> weavePriceList = dataProfile.getWeavePriceList();
            mAdapter.setNewData(weavePriceList);
            mInfoSrl.setRefreshing(false);
        }, volleyError -> {
            mInfoSrl.setRefreshing(false);
        });
    }

    @Override
    public void onRefresh() {
        getData();
    }
}
