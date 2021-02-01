package com.bc.app.adapter;

import com.bc.app.R;
import com.bc.app.entity.App;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.util.List;

import androidx.annotation.Nullable;

public class AppAdapter extends BaseQuickAdapter<App, BaseViewHolder> {

    public AppAdapter(int layoutResId, @Nullable List<App> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, App item) {
        helper.setText(R.id.tv_name, item.getName());
    }
}
