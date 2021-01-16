package com.bc.app.activity;

import com.bc.app.widget.LoadingDialog;

import androidx.fragment.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {

    private LoadingDialog mLoading;

    public void showLoading() {
        if (mLoading != null) {
            return;
        }
        mLoading = LoadingDialog.getInstance(this);
        mLoading.show();
    }

    public void hideLoading() {
        if (mLoading != null) {
            mLoading.hide();
            mLoading = null;
        }
    }

}
