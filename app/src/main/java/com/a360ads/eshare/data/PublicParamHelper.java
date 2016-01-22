package com.a360ads.eshare.data;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 说明：
 * note：
 * Created by FuPei
 * on 2016/1/22 at 14:03
 */
public class PublicParamHelper implements PublicParams.PublicParamsInterFace {

    private Context mContext;

    public PublicParamHelper(Context context) {
        this.mContext = context;
    }

    @Override
    public String getPublic_uuid() {
        return "123uuid";
    }

    @Override
    public String getPublic_score() {
        return "score123";
    }

    @Override
    public String getPublic_deviceType() {
        return "2";
    }

    @Override
    public String getPublic_version() {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            return "未知";
        }
    }

    @Override
    public String getPublic_weiXinId() {
        return "weixinid";
    }

    @Override
    public String getPublic_reachabilityType() {
        return "reachtypea a a a ";
    }

    @Override
    public String getPublic_reachability() {
        return "reachbiity+++++++";
    }

    @Override
    public String getPublic_Ip() {
        return "ipipip";
    }

    @Override
    public String getPublic_uid() {
        return "uiduiduid";
    }
}
