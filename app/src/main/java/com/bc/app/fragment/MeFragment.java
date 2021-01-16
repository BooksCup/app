package com.bc.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bc.app.R;
import com.bc.app.adapter.MeTabAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我
 *
 * @author zhou
 */
public class MeFragment extends BaseFragment {

    @BindView(R.id.tl_me_tab)
    TabLayout mMeTabTl;

    @BindView(R.id.vp_me_tab)
    ViewPager mMeTabVp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MeTabAdapter adapter = new MeTabAdapter(getChildFragmentManager());
        mMeTabVp.setAdapter(adapter);
        mMeTabTl.setupWithViewPager(mMeTabVp);
        mMeTabVp.setCurrentItem(0);
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
