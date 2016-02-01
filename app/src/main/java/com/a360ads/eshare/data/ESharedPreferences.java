package com.a360ads.eshare.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.a360ads.eshare.utils.Elog;

/**
 * 说明：持久化存储
 * Created by FuPei
 * on 2016/1/19 at 9:49
 */
public class ESharedPreferences {

    /**
     * 是否加载了一次导航界面
     */
    public static final String KEY_ONCE = "once";
    public static final String KEY_AD = "ad";
    public static final String KEY_CHANNEL = "channel";
    public static final String KEY_UID = "uid";
    public static final String KEY_TOKEN = "token";

    private final String SP_USER = "user";

    private SharedPreferences mSPferences;
    private SharedPreferences.Editor mEditor;
    private Context mContext;
    private final boolean islog = false;

    public ESharedPreferences(Context context) {
        mContext = context;
        mSPferences = context.getSharedPreferences(SP_USER, context.MODE_PRIVATE);
        mEditor = mSPferences.edit();
    }

    /**
     * 是否加载了一次导航界面
     *
     * @return true, 是；false：否
     */
    public boolean isOnceLoad() {
        return mSPferences.getBoolean(KEY_ONCE + getVersion(), false);
    }

    /**
     * 设置已经加载了一次导航
     */
    public void setHaveOnceLoad() {
        mEditor.putBoolean(KEY_ONCE + getVersion(), true);
        mEditor.commit();
    }

    public String getVersion() {
        PackageManager manager = mContext.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(mContext.getPackageName(), 0);
            Elog.i(islog, "获取的版本信息：" + info.versionName);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Elog.i(islog, "查看是否第一次，报异常");
            return "";
        }
    }

    /**
     * 设置指定key的值
     *
     * @param key   名字
     * @param value 值
     */
    public void setValueByKey(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public void setValueByKey(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    /**
     * 获取指定key的值
     *
     * @param key
     * @return
     */
    public String getValueByKey(String key) {
        return mSPferences.getString(key, null);
    }

    /**
     * 写入渠道号
     */
    public void setChannel() {
        ApplicationInfo appInfo;
        try {
            appInfo = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(),
                    PackageManager.GET_META_DATA);
            String msg = appInfo.metaData.getString("UMENG_CHANNEL_VALUE");
            if(msg == null) {
                Elog.i("msg为null");
            } else {
                Elog.i(islog, "setChannel():\n msg = " + msg);
                setValueByKey(KEY_CHANNEL, msg);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Elog.e("保存渠道号失败");
        }
    }

}
