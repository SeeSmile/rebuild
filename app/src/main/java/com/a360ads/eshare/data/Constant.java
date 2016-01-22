package com.a360ads.eshare.data;

/**
 * 说明：静态文件配置
 * Created by FuPei
 * on 2016/1/20 at 14:26
 */
public class Constant {

    /**
     * 获取服务器各种接口的信息
     */
    public static final String SERVER_URL_CONFIG = "http://t.360netnews.com/config.php";
    //服务器配置
    public static final String SERVER_DEBUG = "";
    public static final String SERVER_OFFICIAL = "";
    public static final String SERVER_CURRENT = SERVER_DEBUG;

    /**
     * 服务器连接一
     */
    public static final String SERVER_ONE_DEBUG = "http://192.168.0.111:9012/api.php/App/";
    public static final String SERVER_ONE_OFFICIAL = "http://www.xiangexia.com/api.php/App/";
    public static final String SERVER_ONE = SERVER_ONE_DEBUG;

    /**
     * 判断手机号码
     */
    public static final String URL_JUDGE_PHONE = SERVER_ONE + "UserIsPhone";

    /**
     * 个人端用户登录
     */
    public static final String URL_USER_LOGIN = SERVER_ONE + "UserLogin";

    /**
     * 加密key
     */
    public static final String DES_KEY = "ooCZc5DIoVpUrsJ7JKiJFKb5OZmLw2Ob";
}
