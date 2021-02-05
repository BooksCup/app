package com.bc.app.activity.info;

import android.os.Bundle;

import com.alibaba.fastjson.JSONArray;
import com.bc.app.R;
import com.bc.app.activity.BaseActivity;
import com.bc.app.adapter.info.WeavePriceAdapter;
import com.bc.app.cons.Constant;
import com.bc.app.entity.info.WeavePrice;
import com.bc.app.utils.VolleyUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 纺织品价格
 *
 * @author zhou
 */
public class WeavePriceActivity extends BaseActivity {

    @BindView(R.id.rv_weave_price)
    RecyclerView mWeavePriceRv;

    private WeavePriceAdapter mAdapter;

    List<WeavePrice> mWeavePriceList = new ArrayList<>();
    private VolleyUtil mVolleyUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weave_price);
        ButterKnife.bind(this);
        mVolleyUtil = VolleyUtil.getInstance(this);
        initView();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mWeavePriceRv.setLayoutManager(layoutManager);
        mAdapter = new WeavePriceAdapter(R.layout.item_weave_price, mWeavePriceList);
        mWeavePriceRv.setAdapter(mAdapter);

        getWeavePriceList();
    }

    private void getWeavePriceList() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String url = Constant.CRAWLER_URL + "weavePrice/list?date=" + sdf.format(new Date());

        mVolleyUtil.get(url, response -> {
            final List<WeavePrice> weavePriceList = JSONArray.parseArray(response, WeavePrice.class);
            mAdapter.setNewData(weavePriceList);
        }, volleyError -> {
        });
    }

}
