package com.bc.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bc.app.R;
import com.bc.app.adapter.DiscoverTapAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 发现
 *
 * @author zhou
 */
public class DiscoverFragment extends Fragment {

    @BindView(R.id.tl_discover_tab)
    TabLayout mDiscoverTabTl;

    @BindView(R.id.vp_discover_tab)
    ViewPager mDiscoverTabVp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
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
        DiscoverTapAdapter adapter = new DiscoverTapAdapter(getChildFragmentManager());
        mDiscoverTabVp.setAdapter(adapter);
        mDiscoverTabTl.setupWithViewPager(mDiscoverTabVp);
        mDiscoverTabVp.setCurrentItem(0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
