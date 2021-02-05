package com.bc.app.adapter;

import android.view.ViewGroup;


import com.bc.app.fragment.BaseFragment;
import com.bc.app.fragment.InfoFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class DiscoverTapAdapter extends FragmentPagerAdapter {

    private final String[] mTitles = new String[]{"资讯", "商品", "人才"};
    private final BaseFragment[] mBaseFragments = new BaseFragment[mTitles.length];

    public DiscoverTapAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (mBaseFragments[position] == null) {
            BaseFragment fragment;
            if (position == 0) {
                fragment = new InfoFragment();
            } else if (position == 1) {
                return new Fragment();
            } else {
                return new Fragment();
            }
            mBaseFragments[position] = fragment;
        }
        return mBaseFragments[position];
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }
}
