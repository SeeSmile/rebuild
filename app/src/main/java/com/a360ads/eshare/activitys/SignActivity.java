package com.a360ads.eshare.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a360ads.eshare.R;
import com.a360ads.eshare.base.BaseActivity;
import com.a360ads.eshare.interfaces.ApiListener;
import com.a360ads.eshare.utils.EwebUtil;
import com.loopj.android.http.RequestParams;
import com.seesmile.demos.ecalendar.ECalendarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;

/**
 * 说明：
 * Created by FuPei
 * on 2016/1/25 at 14:19
 */
public class SignActivity extends BaseActivity {

    @InjectView(R.id.sign_calendar)
    public ECalendarView cv;
    @InjectView(R.id.sign_tv_sign)
    public TextView tv_sign;
    @InjectView(R.id.sign_tv_score)
    public TextView tv_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getSignData();
        initListener();
    }

    private void getSignData() {
        RequestParams params = new RequestParams();
        EwebUtil.getInstance().doSafeGet("", null, new ApiListener() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFail() {

            }
        });
    }

    private void initListener() {
        setOnbackclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        setContentView(R.layout.activity_sign);
        setTitleStyle(STYLE_BOTH_TB);
        setTitle("签到");
    }

}
