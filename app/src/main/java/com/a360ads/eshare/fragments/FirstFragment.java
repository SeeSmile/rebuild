package com.a360ads.eshare.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.a360ads.eshare.R;
import com.a360ads.eshare.base.BaseFragment;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 说明：
 * note：
 * Created by FuPei
 * on 2016/1/23 at 14:39
 */
public class FirstFragment extends BaseFragment {

    @InjectView(R.id.lv_data)
    public PullToRefreshListView lv_data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, null);
        ButterKnife.inject(view);
        return view;
    }


}
