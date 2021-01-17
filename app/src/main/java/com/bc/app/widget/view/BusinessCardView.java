package com.bc.app.widget.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bc.app.R;
import com.bc.app.entity.User;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BusinessCardView extends RelativeLayout {

    @BindView(R.id.sdv_avatar)
    SimpleDraweeView mAvatarSdv;

    @BindView(R.id.tv_name)
    TextView mNameTv;

    @BindView(R.id.tv_job_name)
    TextView mJobNameTv;

    @BindView(R.id.tv_phone)
    TextView mPhoneTv;

    @BindView(R.id.tv_email)
    TextView mEmailTv;

    @BindView(R.id.tv_enterprise_name)
    TextView mEnterpriseNameTv;

    @BindView(R.id.tv_enterprise_address)
    TextView mEnterpriseAddressTv;

    public BusinessCardView(Context context) {
        super(context);
        addView();
    }

    public BusinessCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addView();
    }

    public BusinessCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addView();
    }

    private void addView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_business_card, this, false);
        ButterKnife.bind(this, view);
        this.addView(view);
    }

    public void setDate(User user) {
        if (!TextUtils.isEmpty(user.getAvatar())) {
            mAvatarSdv.setImageURI(Uri.parse(user.getAvatar()));
        }

        mNameTv.setText(user.getName());
        mJobNameTv.setText(user.getJobName());
        mPhoneTv.setText(user.getPhone());
        mEmailTv.setText(user.getEmail());
        mEnterpriseNameTv.setText(user.getEnterpriseName());
        mEnterpriseAddressTv.setText(user.getEnterpriseAddress());
    }

}
