package com.bc.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bc.app.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 个人中心
 *
 * @author zhou
 */
public class PersonalFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        return view;
    }
}
