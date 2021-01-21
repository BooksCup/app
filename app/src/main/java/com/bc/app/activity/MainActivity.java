package com.bc.app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bc.app.R;
import com.bc.app.fragment.CalendarFragment;
import com.bc.app.fragment.ChatsFragment;
import com.bc.app.fragment.DesktopFragment;
import com.bc.app.fragment.DiscoverFragment;
import com.bc.app.fragment.MeFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {

    private Fragment[] mFragments;
    private ChatsFragment mChatsFragment;
    private DesktopFragment mDesktopFragment;
    private DiscoverFragment mDiscoverFragment;
    private CalendarFragment mCalendarFragment;
    private MeFragment mMeFragment;

    private ImageView[] mMainButtonIvs;
    private TextView[] mMainButtonTvs;

    private int mIndex;
    // 当前fragment的index
    private int mCurrentTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mChatsFragment = new ChatsFragment();
        mDesktopFragment = new DesktopFragment();
        mDiscoverFragment = new DiscoverFragment();
        mCalendarFragment = new CalendarFragment();
        mMeFragment = new MeFragment();

        mFragments = new Fragment[]{mChatsFragment, mDesktopFragment,
                mDiscoverFragment, mCalendarFragment, mMeFragment};

        mMainButtonIvs = new ImageView[mFragments.length];
        mMainButtonIvs[0] = findViewById(R.id.iv_chats);
        mMainButtonIvs[1] = findViewById(R.id.iv_desktop);
        mMainButtonIvs[2] = findViewById(R.id.iv_discover);
        mMainButtonIvs[3] = findViewById(R.id.iv_calendar);
        mMainButtonIvs[4] = findViewById(R.id.iv_me);

        mMainButtonIvs[0].setSelected(true);
        mMainButtonTvs = new TextView[mFragments.length];
        mMainButtonTvs[0] = findViewById(R.id.tv_chats);
        mMainButtonTvs[1] = findViewById(R.id.tv_desktop);
        mMainButtonTvs[2] = findViewById(R.id.tv_discover);
        mMainButtonTvs[3] = findViewById(R.id.tv_calendar);
        mMainButtonTvs[4] = findViewById(R.id.tv_me);
        mMainButtonTvs[0].setTextColor(getColor(R.color.main_activity_btn_press));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rl_fragment_container, mChatsFragment)
                .add(R.id.rl_fragment_container, mDesktopFragment)
                .add(R.id.rl_fragment_container, mDiscoverFragment)
                .add(R.id.rl_fragment_container, mCalendarFragment)
                .add(R.id.rl_fragment_container, mMeFragment)
                .hide(mDesktopFragment).hide(mDiscoverFragment).hide(mCalendarFragment).hide(mMeFragment)
                .show(mChatsFragment).commit();

    }

    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_chats:
                mIndex = 0;
                break;
            case R.id.rl_desktop:
                mIndex = 1;
                break;
            case R.id.rl_discover:
                mIndex = 2;
                break;
            case R.id.rl_calendar:
                mIndex = 3;
                break;
            case R.id.rl_me:
                mIndex = 4;
                break;
        }

        if (mCurrentTabIndex != mIndex) {
            FragmentTransaction trx = getSupportFragmentManager()
                    .beginTransaction();
            trx.hide(mFragments[mCurrentTabIndex]);
            if (!mFragments[mIndex].isAdded()) {
                trx.add(R.id.rl_fragment_container, mFragments[mIndex]);
            }
            trx.show(mFragments[mIndex]).commit();
        }
        mMainButtonIvs[mCurrentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mMainButtonIvs[mIndex].setSelected(true);
        mMainButtonTvs[mCurrentTabIndex].setTextColor(getColor(R.color.main_activity_btn_default));
        mMainButtonTvs[mIndex].setTextColor(getColor(R.color.main_activity_btn_press));
        mCurrentTabIndex = mIndex;
    }
}