package com.bc.app.widget.view;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bc.app.R;
import com.bc.app.activity.info.WeavePriceActivity;
import com.bc.app.entity.info.HotExchange;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExchangeRateView extends RelativeLayout {

    @BindView(R.id.tv_title)
    TextView mTitleTv;

    @BindView(R.id.tv_current_price)
    TextView mCurrentPriceTv;

    @BindView(R.id.tv_change)
    TextView mChangeTv;

    @BindView(R.id.tv_create_time)
    TextView mCreateTimeTv;

    public ExchangeRateView(Context context) {
        super(context);
        initView();
    }

    public ExchangeRateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ExchangeRateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_exchange_rate, this, false);
        ButterKnife.bind(this, view);
        this.addView(view);
    }

    public void setDate(HotExchange hotExchange) {
        mTitleTv.setText(hotExchange.getTitle());
        mCurrentPriceTv.setText(Html.fromHtml(hotExchange.getCurrentPrice()));
        mChangeTv.setText(Html.fromHtml(hotExchange.getChange()));
        mCreateTimeTv.setText(hotExchange.getCreateTime());
    }

    @OnClick({R.id.ll_header})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_header:
                Intent intent = new Intent(getContext(), WeavePriceActivity.class);
                getContext().startActivity(intent);
                break;
        }
    }

}
