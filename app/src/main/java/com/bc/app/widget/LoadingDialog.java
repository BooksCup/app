package com.bc.app.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.bc.app.R;


public class LoadingDialog extends AlertDialog {

    private static LoadingDialog loadingDialog;

    protected LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static LoadingDialog getInstance(Context context) {
        loadingDialog = new LoadingDialog(context, R.style.TransparentDialog);
        loadingDialog.setCancelable(false);
        loadingDialog.setCanceledOnTouchOutside(false);
        return loadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.loading);
    }


}
