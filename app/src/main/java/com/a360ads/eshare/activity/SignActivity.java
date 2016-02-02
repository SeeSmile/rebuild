package com.a360ads.eshare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.a360ads.eshare.EshareApplication;
import com.a360ads.eshare.R;
import com.a360ads.eshare.base.BaseActivity;
import com.a360ads.eshare.data.Constant;
import com.a360ads.eshare.entitys.SignEntity;
import com.a360ads.eshare.interfaces.ApiListener;
import com.a360ads.eshare.utils.Elog;
import com.a360ads.eshare.utils.EwebUtil;
import com.google.gson.Gson;
import com.seesmile.demos.ecalendar.ECalendarView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.InjectView;

/**
 * 说明：签到界面
 * Created by FuPei
 * on 2016/1/25 at 14:19
 */
public class SignActivity extends BaseActivity {

    private final boolean islog = true;
    @InjectView(R.id.sign_calendar)
    public ECalendarView cv_sign;
    @InjectView(R.id.sign_tv_sign)
    public TextView tv_sign;
    @InjectView(R.id.sign_tv_score)
    public TextView tv_score;

    /**
     * 签到数据
     */
    private List<SignEntity.ScoreinfoEntity> data_sign;

    /**
     * 签到说明url
     */
    private String url_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getSignData();
        initListener();
    }

    /**
     * 获取签到信息
     */
    private void getSignData() {
        Map<String, String> map = new HashMap<>();
        String uid = EshareApplication.getInstance().getUserInfo().getUid();
        map.put("uid", uid);
        EwebUtil.getInstance().doSafeGet(Constant.URL_SIGN_INFO, map, new ApiListener.JsonRequest() {
            @Override
            public void onJsonLoad(JSONObject json) {
                if (json.optString("code").equals("1")) {
                    setSignUI(new Gson().fromJson(json.optString("data"), SignEntity.class));
                } else {
                    toast("获取签到信息失败");
                }
            }

            @Override
            public void onJsonFail() {
                toast("获取签到信息失败");
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

        tv_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cv_sign.isSign()) {
                    toast("今天已签到");
                } else {
                    setSignToday();
                }
            }
        });
    }

    private void initView() {
        setContentView(R.layout.activity_sign);
        setTitleStyle(STYLE_BOTH_TB);
        setTitle("签到");
    }

    /**
     * 设置签到的UI
     */
    private void setSignUI(SignEntity entity) {

        Elog.i("signEntity\n" + entity.toString());
        if(judgeSignToday(Long.valueOf(entity.getCurrenttime()) * 1000, entity)) {
            Elog.i(islog, "今天已签到");
            cv_sign.setSignSuccess();
            tv_sign.setBackgroundResource(R.drawable.shape_btn_sign_press);
        }

        //获取签到帮助的url
        url_help = entity.getSignedhelpurl();
        setTitleStyle(STYLE_BOTH_ALL);
        setIconImage(R.mipmap.icon_open_web);
        setOnIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignActivity.this, EwebActivity.class);
                intent.putExtra(EwebActivity.EXTRA_URL, url_help);
                intent.putExtra(EwebActivity.EXTRA_TITLE, "签到说明");
                startActivity(intent);
            }
        });

        tv_score.setText(entity.getScore());

        //初始化日历显示
        cv_sign.setcurrTime(Long.valueOf(entity.getCurrenttime()) * 1000);
        Date date = new Date();
        for (SignEntity.ScoreinfoEntity data : entity.getScoreinfo()) {
            date.setTime(Long.valueOf(data.getDatetime_stamp()) * 1000);
            cv_sign.signAtday(date);
        }
    }
    /**
     * 判断今天是否签到
     * @return
     */
    private boolean judgeSignToday(long time_today, SignEntity entity) {
        Elog.i(islog, "time_today = " + time_today);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        date.setTime(time_today);
        String str_time = format.format(date);
        String str_sign = "";
        Elog.i(islog, "str_time = " + str_time);
        for(SignEntity.ScoreinfoEntity data : entity.getScoreinfo()) {
            str_sign = data.getDatetime().substring(0, 10);
            Elog.i(islog, "str_sign = " + str_sign);
            if(str_time.equals(str_sign)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 设置今天签到
     */
    private void setSignToday() {
        Map<String, String> map = new HashMap<>();
        String uid = EshareApplication.getInstance().getUserInfo().getUid();
        map.put("uid", uid);
        EwebUtil.getInstance().doSafePost(Constant.URL_USER_SIGN, map, new ApiListener.JsonRequest() {
            @Override
            public void onJsonLoad(JSONObject json) {
                cv_sign.setSignSuccess();
                getSignData();
            }

            @Override
            public void onJsonFail() {
                toast("签到失败");
            }
        });
    }

}
