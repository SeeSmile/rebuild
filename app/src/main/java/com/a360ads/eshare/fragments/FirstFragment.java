package com.a360ads.eshare.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a360ads.eshare.EshareApplication;
import com.a360ads.eshare.R;
import com.a360ads.eshare.activitys.ForecastActivity;
import com.a360ads.eshare.base.BaseEntity;
import com.a360ads.eshare.base.BaseFragment;
import com.a360ads.eshare.data.Constant;
import com.a360ads.eshare.data.ConstantCode;
import com.a360ads.eshare.interfaces.ApiListener;
import com.a360ads.eshare.utils.Elog;
import com.a360ads.eshare.utils.EwebUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
    @InjectView(R.id.tv_forecast)
    public TextView tv_forecast;
    /**
     * 当前列表请求的page
     */
    private int currentPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, null);
//        ButterKnife.inject(view, getActivity());
        currentPage = 1;
        tv_forecast = (TextView) view.findViewById(R.id.tv_forecast);
        getListData();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    /**
     * 获取互动接口的数据
     */
    private void getListData() {
        Map<String, String> params = new HashMap<>();
        params.put("uid", EshareApplication.getInstance().getUserInfo().getUid());
        params.put("token", EshareApplication.getInstance().getUserInfo().getToken());
        params.put("type", ConstantCode.TYPE_SELF_START);
        params.put("page", currentPage + "");
        EwebUtil.getInstance().doSafeGet(Constant.URL_PROJECT_LIST, params, new ApiListener() {
            @Override
            public void onSuccess(String result) {
                Elog.i("onSuccess:" + result);
                BaseEntity baseEntity = new Gson().fromJson(result, BaseEntity.class);
                String code = baseEntity.code;
                //没有预测的数据
                if("3".equals(code)) {
                    Elog.i("code = " + baseEntity.info);
                    tv_forecast.setVisibility(View.VISIBLE);
                    tv_forecast.setText(baseEntity.info);
                    tv_forecast.setOnClickListener(null);
                } else if("1".equals(code)) {
                    //如果数据是json格式，那说明此数据是互动的数据
                    try {
                        JSONObject json = new JSONObject(baseEntity.info);
                        tv_forecast.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        //不是互动的数据，是预测的字符串
                        tv_forecast.setVisibility(View.VISIBLE);
                        tv_forecast.setText(baseEntity.info);
                        tv_forecast.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getActivity().startActivity(new Intent(getActivity(),
                                        ForecastActivity.class));
                            }
                        });
                    }
                }
            }

            @Override
            public void onFail() {
                Elog.i("onFail:");
            }
        });
    }
}
