package com.bc.app.adapter.info;

import android.widget.TextView;

import com.bc.app.R;
import com.bc.app.entity.info.WeavePrice;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.NumberFormat;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class WeavePriceAdapter extends BaseQuickAdapter<WeavePrice, BaseViewHolder> {

    public WeavePriceAdapter(int layoutResId, @Nullable List<WeavePrice> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeavePrice item) {

        Number change;
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        try {
            change = numberFormat.parse(item.getChange());
        } catch (Exception e) {
            change = 0;
            e.printStackTrace();
        }

        TextView changeTv = helper.getView(R.id.tv_change);
        if (null != change) {
            if (change.floatValue() > 0) {
                changeTv.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
            } else if (change.floatValue() < 0) {
                changeTv.setTextColor(ContextCompat.getColor(mContext, R.color.colorStyleG));
            } else {
                changeTv.setTextColor(ContextCompat.getColor(mContext, R.color.color50));
            }
        }

        String date = "";
        if (item.getDate() != null && item.getDate().length() >= 10) {
            date = item.getDate().substring(5, 10);
        }

        helper.setText(R.id.tv_name, item.getName()).
                setText(R.id.tv_last_trade, item.getLastTrade()).
                setText(R.id.tv_unit, item.getUnit()).
                setText(R.id.tv_change, item.getChange()).
                setText(R.id.tv_date, date);
    }
}
