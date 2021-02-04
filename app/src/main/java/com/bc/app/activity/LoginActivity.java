package com.bc.app.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bc.app.R;
import com.bc.app.cons.Constant;
import com.bc.app.entity.User;
import com.bc.app.utils.MD5Util;
import com.bc.app.utils.PreferencesUtil;
import com.bc.app.utils.VolleyUtil;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录
 *
 * @author zhou
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText mPhoneEt;

    @BindView(R.id.et_password)
    EditText mPasswordEt;

    private VolleyUtil mVolleyUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mVolleyUtil = VolleyUtil.getInstance(this);
    }

    @OnClick({R.id.tv_login, R.id.tv_register})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                login();
                break;
            case R.id.tv_register:
                break;
        }
    }

    private void login() {
        showLoading();
        String phone = mPhoneEt.getText().toString().trim();
        String password = mPasswordEt.getText().toString().trim();
        password = MD5Util.md5Decode32(password);
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "手机号或密码不能为空!", Toast.LENGTH_SHORT).show();
        } else {
            String url = Constant.BASE_URL + "user/login";
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("phone", phone);
            paramMap.put("password", password);
            mVolleyUtil.post(url, paramMap, response -> {
                hideLoading();
                final User user = JSON.parseObject(response, User.class);
                // 登录成功后设置user和isLogin至sharedPreferences中
                PreferencesUtil.getInstance().setUser(user);
                PreferencesUtil.getInstance().setLogin(true);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }, volleyError -> {
                hideLoading();
                mVolleyUtil.handleCommonErrorResponse(LoginActivity.this, volleyError);
                int errorCode = volleyError.networkResponse.statusCode;
                switch (errorCode) {
                    case 400:
                        Toast.makeText(LoginActivity.this,
                                R.string.user_not_exists, Toast.LENGTH_SHORT)
                                .show();
                        break;
                }
            });
        }
    }

}
