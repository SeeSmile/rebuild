package com.a360ads.eshare.activitys;

import android.os.Bundle;
import com.a360ads.eshare.base.BaseActivity;
import com.a360ads.eshare.data.EshareSharedPreferences;
import com.a360ads.eshare.utils.Elog;

/**
 * 程序最开始启动的界面
 */
public class FirstActivity extends BaseActivity {

    private boolean islog = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EshareSharedPreferences shared = new EshareSharedPreferences(FirstActivity.this);
        Class<?> toClass;
        //是否第一次使用
        if(!shared.isOnceLoad()) {
            Elog.i(islog, "第一次使用");
            toClass = GuideActivity.class;
        } else {
            //是否有广告要加载
            if(shared.getValueByKey(EshareSharedPreferences.KEY_AD) != null) {
                Elog.i(islog, "弹出广告");
                toClass = AdActivity.class;
            } else {
                //进入登录界面
                Elog.i(islog, "进入登录");
                toClass = LoginActivity.class;
            }
        }

        goActivity(toClass);
        finish();
    }
}
