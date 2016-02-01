package com.a360ads.eshare;

import android.app.Activity;
import android.app.Application;

import com.a360ads.eshare.data.Constant;
import com.a360ads.eshare.entitys.ConfigEntity;
import com.a360ads.eshare.entitys.UserEntity;
import com.a360ads.eshare.entitys.WXEntity;
import com.a360ads.eshare.entitys.WXUserEntity;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

/**
 * 说明：享e下Application
 * Created by FuPei
 * on 2016/1/19 at 9:44
 */
public class EshareApplication extends Application {

    /**
     * 界面链式数据
     */
    private List<Activity> activityList = new LinkedList<>();
    /**
     * 单例实例
     */
    private static EshareApplication instance;
    /**
     * 接口默认的配置信息
     */
    private ConfigEntity configEntity;
    /**
     * 公共的参数
     */
    private Map<String, String> publicParams;
    /**
     * 微信登陆识别码
     */
    private String state_login;

    private WXUserEntity wxUserinfo;

    private UserEntity userInfo;

    public WXUserEntity getWxUserinfo() {
        return wxUserinfo;
    }

    public void setWxUserinfo(WXUserEntity wxUserinfo) {
        this.wxUserinfo = wxUserinfo;
    }

    public Map<String, String> getPublicParams() {
        return publicParams;
    }

    //数据的保存和读取
    public String getState_login() {
        return state_login;
    }

    public void setState_login(String state_login) {
        this.state_login = state_login;
    }

    public ConfigEntity getmConfigEntity() {
        return configEntity;
    }

    public void setConfigEntity(ConfigEntity configEntity) {
        this.configEntity = configEntity;
    }

    public UserEntity getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserEntity userInfo) {
        this.userInfo = userInfo;
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

    public void initData() {
        ShareSDK.initSDK(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        IWXAPI api = WXAPIFactory.createWXAPI(this, Constant.APP_ID, true);
        api.registerApp(Constant.APP_ID);
    }

    public IWXAPI createWXApi() {
        IWXAPI api = WXAPIFactory.createWXAPI(this, Constant.APP_ID, true);
        api.registerApp(Constant.APP_ID);
        return api;
    }

    public void initPublicParams() {

    }



}
