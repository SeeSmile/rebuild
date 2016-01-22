package com.a360ads.eshare.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import butterknife.ButterKnife;

/**
 * 说明：
 * note：
 * Created by FuPei
 * on 2016/1/19 at 13:29
 */
public class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
