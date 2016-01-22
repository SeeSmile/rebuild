package com.a360ads.eshare;

import android.app.Activity;
import android.app.Application;

import com.a360ads.eshare.data.EshareSharedPreferences;
import com.a360ads.eshare.data.PublicParamHelper;
import com.a360ads.eshare.data.PublicParams;
import com.a360ads.eshare.entitys.ConfigEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * 说明：享e下Application
 * Created by FuPei
 * on 2016/1/19 at 9:44
 */
public class EshareApplication extends Application {

    private List<Activity> activityList = new LinkedList<>();
    private static EshareApplication instance;
    private ConfigEntity mConfigEntity;
    private PublicParams mPublicParams;

    public PublicParams getmPublicParams() {
        return mPublicParams;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initData();
    }

    public static EshareApplication getInstance() {
        return instance;
    }

    /**
     * 添加活动
     * @param act
     */
    public void addActivity(Activity act) {
        activityList.add(act);
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        for(Activity act : activityList) {
            act.finish();
        }
        activityList.clear();
        System.exit(0);
    }

    public ConfigEntity getmConfigEntity() {
        return mConfigEntity;
    }

    public void setmConfigEntity(ConfigEntity mConfigEntity) {
        this.mConfigEntity = mConfigEntity;
    }

    public void initData() {
        mPublicParams = new PublicParams(new PublicParamHelper(getApplicationContext()));
    }
}
