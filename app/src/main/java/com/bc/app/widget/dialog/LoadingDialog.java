package com.bc.app.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.bc.app.R;

/**
 * 加载对话框
 *
 * @author zhou
 */
public class LoadingDialog extends AlertDialog {

    protected static LoadingDialog mLoadingDialog;

    protected LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static LoadingDialog getInstance(Context context) {
        mLoadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_loading);
    }


}
