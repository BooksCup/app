package com.bc.app.adapter;

import android.view.ViewGroup;

import com.bc.app.fragment.BaseFragment;
import com.bc.app.fragment.PersonalFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * 我 - tab
 *
 * @author zhou
 */
public class MeTabAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"我"};

    private BaseFragment[] mBaseFragments = new BaseFragment[mTitles.length];

    public MeTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (mBaseFragments[position] == null) {
            BaseFragment fragment = null;
            if (position == 0) {
                fragment = new PersonalFragment();
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