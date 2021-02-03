package com.bc.app.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.bc.app.R;
import com.bc.app.utils.PreferencesUtil;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class SplashActivity extends FragmentActivity {

    Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_splash);

        mHandler = new Handler();
        mHandler.postDelayed(() -> {
            Intent intent;
            if (PreferencesUtil.getInstance().isLogin()) {
                // 已登录
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                // 未登录
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }

            startActivity(intent);
            finish();
        }, 1500);
    }

}
