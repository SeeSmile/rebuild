package com.a360ads.eshare.data;

/**
 * 说明：静态文件配置
 * Created by FuPei
 * on 2016/1/20 at 14:26
 */
public class Constant {

    /**
     * 加密key
     */
    public static final String DES_KEY = "ooCZc5DIoVpUrsJ7JKiJFKb5OZmLw2Ob";

    /**
     * 微信ID
     */
    public static final String APP_ID = "wx2d5039f859b5a469";

    /**
     * 微信SECRET
     */
    public static final String SECRET = "5b36a017aa903def68fbf5a3d8f99325";

    /**
     * 微信获取token
     */
    public static final String URL_WEIXIN_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * 获取微信用户信息
     */
    public static final String URL_WEIXIN_INFO = "https://api.weixin.qq.com/sns/userinfo";

    /**
     * 获取服务器各种接口的信息
     */
    public static final String SERVER_URL_CONFIG = "http://t.360netnews.com/config.php";

    //服务器配置
    public static final String SERVER_TWO_DEBUG = "http://192.168.0.111:9012/api.php/Cmd/";
    public static final String SERVER_TWO_OFFICIAL = "http://www.xiangexia.com/api.php/Cmd/";
    public static final String SERVER_TWO = SERVER_TWO_DEBUG;

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
     * 个人端手机登录
     */
    public static final String URL_USER_LOGIN = SERVER_ONE + "UserLogin";

    public static final String URL_USER_BALANCE = SERVER_TWO + "getUserBalance";

    /**
     * 获取指定列表信息
     */
    public static final String URL_PROJECT_LIST = SERVER_TWO + "getProjectList";

    /**
     * 个人端微信登陆
     */
    public static final String URL_LOGIN_WX = SERVER_TWO + "login";

    /**
     * 设置用户签到
     */
    public static final String URL_USER_SIGN = SERVER_ONE + "UserSignin";

    /**
     * 获取用户签到信息
     */
    public static final String URL_SIGN_INFO = SERVER_ONE + "SigninInfos";

    /**
     * 获取消息列表
     */
    public static final String URL_MSG_LIST = SERVER_ONE + "MessageList";

}
