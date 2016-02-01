package com.a360ads.eshare.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.a360ads.eshare.EshareApplication;
import com.a360ads.eshare.R;
import com.a360ads.eshare.base.BaseActivity;
import com.a360ads.eshare.data.Constant;
import com.a360ads.eshare.interfaces.ApiListener;
import com.a360ads.eshare.utils.Elog;
import com.a360ads.eshare.utils.EwebUtil;
import com.seesmile.demos.ecalendar.ECalendarView;
import java.util.HashMap;
import java.util.Map;
import butterknife.InjectView;

/**
 * 说明：签到界面
 * Created by FuPei
 * on 2016/1/25 at 14:19
 */
public class SignActivity extends BaseActivity {

    private final boolean canlog = true;
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
        Map<String, String> map = new HashMap<>();
        String uid = EshareApplication.getInstance().getUserInfo().getUid();
        map.put("uid", uid);
        Elog.i(canlog, "uid = " + uid);
        EwebUtil.getInstance().doSafePost(Constant.URL_USER_SIGN, map, new ApiListener() {
            @Override
            public void onSuccess(String result) {
                Elog.i(canlog, "onSuccess:" + result);
            }

            @Override
            public void onFail() {
                Elog.i(canlog, "onFail:");
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
