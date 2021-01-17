package com.bc.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.bc.app.R;
import com.bc.app.activity.SettingActivity;
import com.bc.app.cons.Constant;
import com.bc.app.entity.User;
import com.bc.app.utils.PreferencesUtil;
import com.bc.app.utils.VolleyUtil;
import com.bc.app.widget.view.BusinessCardView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人中心
 *
 * @author zhou
 */
public class PersonalFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.view_business_card)
    BusinessCardView mBusinessCardView;

    @BindView(R.id.srl_me)
    SwipeRefreshLayout mMeSrl;

    private VolleyUtil mVolleyUtil;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mVolleyUtil = VolleyUtil.getInstance(getActivity());
        User user = PreferencesUtil.getInstance().getUser();
        mBusinessCardView.setDate(user);
        mMeSrl.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        User user = PreferencesUtil.getInstance().getUser();
        getUserInfo(user.getId());
    }

    private void getUserInfo(final String userId) {
        String url = Constant.BASE_URL + "user/" + userId;

        mVolleyUtil.get(url, response -> {
            final User user = JSON.parseObject(response, User.class);
            mBusinessCardView.setDate(user);
            PreferencesUtil.getInstance().setUser(user);
            mMeSrl.setRefreshing(false);
        }, volleyError -> {
            mMeSrl.setRefreshing(false);
        });
    }

    @OnClick({R.id.ll_setting})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_setting:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
