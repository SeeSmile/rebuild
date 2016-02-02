package com.a360ads.eshare.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.a360ads.eshare.R;
import com.a360ads.eshare.activity.LoginActivity;
import com.a360ads.eshare.base.BaseActivity;
import com.a360ads.eshare.base.BaseFragment;
import com.a360ads.eshare.data.ESharedPreferences;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 说明：向导页碎片
 * Created by FuPei
 * on 2016/1/19 at 13:28
 */
public class GuideFragment extends BaseFragment {

    /**
     * 背景图片
     */
    @InjectView(R.id.guide_iv)
    public ImageView iv;
    /**
     * 跳转登陆界面按钮
     */
    @InjectView(R.id.guide_btn)
    public Button btn_login;

    /**图片资源*/
    private int source;
    /**是否显示登陆按钮*/
    private boolean canLogin;

    public void setImage(int source) {
        this.source = source;
    }

    public void setCanLogin(boolean canLogin) {
        this.canLogin = canLogin;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, null);
        ButterKnife.inject(this, view);
        iv.setImageResource(source);
        if(canLogin) {
            btn_login.setVisibility(View.VISIBLE);
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity)getActivity()).goActivity(LoginActivity.class);
                    getActivity().finish();
                    ESharedPreferences shared = new ESharedPreferences(getActivity());
                    shared.setHaveOnceLoad();
                }
            });
        }
        return view;
    }
}
