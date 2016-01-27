package com.a360ads.eshare.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a360ads.eshare.R;
import com.a360ads.eshare.base.BaseFragment;
import com.a360ads.eshare.data.Constant;
import com.a360ads.eshare.interfaces.ApiListener;
import com.a360ads.eshare.utils.Elog;
import com.a360ads.eshare.utils.EwebUtil;

/**
 * 说明：
 * note：
 * Created by FuPei
 * on 2016/1/23 at 14:39
 */
public class MoneyFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_money, null);
        getMgsCount();
        getMoney();
        return view;
    }

    /**
     * 获取金额详情
     */
    private void getMoney() {
        EwebUtil.getInstance().doGet(Constant.URL_USER_BALANCE, null, new ApiListener() {
            @Override
            public void onSuccess(String result) {
                Elog.i("onSuccess :" + result);
            }

            @Override
            public void onFail() {
                Elog.i("onFail()");
            }
        });
    }

    /**
     * 获取未读消息数
     */
    private void getMgsCount() {

    }
}
