package com.a360ads.eshare.base;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.Toast;

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

    /**
     * toast一条信息
     * @param text 信息
     */
    public void toast(String text) {
        if(!TextUtils.isEmpty(text)){
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        }
    }
}
