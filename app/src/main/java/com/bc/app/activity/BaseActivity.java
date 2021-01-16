package com.bc.app.activity;


import com.bc.app.widget.dialog.LoadingDialog;

import androidx.fragment.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {

    private LoadingDialog mLoadingDialog;

    /**
     * 显示加载对话框
     */
    public void showLoading() {
        if (null != mLoadingDialog) {
            return;
        }
        mLoadingDialog = LoadingDialog.getInstance(this);
        mLoadingDialog.show();
    }

    /**
     * 隐藏加载对话框
     */
    public void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.hide();
            mLoadingDialog = null;
        }
    }

}
