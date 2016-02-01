package com.a360ads.eshare.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.a360ads.eshare.EshareApplication;
import com.a360ads.eshare.data.Constant;
import com.a360ads.eshare.entitys.WXEntity;
import com.a360ads.eshare.entitys.WXUserEntity;
import com.a360ads.eshare.interfaces.ApiListener;
import com.a360ads.eshare.utils.Elog;
import com.a360ads.eshare.utils.EwebUtil;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.SendAuth;

/**
 * 说明：微信接口
 * Created by FuPei
 * on 2016/1/27 at 10:13
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler{

    /**
     * 登陆广播
     */
    public static final String BRO_LOGIN = "com.a360ads.share.loginSuccess";
    /**
     * 广播中传输数据的KEY
     */
    public static final String KEY_LOGIN = "login";
    /**
     * 登陆成功
     */
    public static final String CODE_LOGIN_SUCCESS = "1";
    /**
     * 登陆失败
     */
    public static final String CODE_LOGIN_FAIL = "2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EshareApplication.getInstance().createWXApi().handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        //用户同意授权
        SendAuth.Resp resp = (SendAuth.Resp)baseResp;
        String state = EshareApplication.getInstance().getState_login();
        //判断是否为微信登陆返回的信息
        if(state != null && resp.state.equals(state)) {
            if(baseResp.errCode == BaseResp.ErrCode.ERR_OK) {
                getWXToken(resp.token);
            } else {
                onLoginResult(CODE_LOGIN_FAIL);
            }
        }
    }

    /**
     * 获取微信token
     * @param token
     */
    private void getWXToken(String token) {
        RequestParams params = new RequestParams();
        params.add("appid", Constant.APP_ID);
        params.add("secret", Constant.SECRET);
        params.add("code", token);
        params.add("grant_type", "authorization_code");
        EwebUtil.getInstance().doGetNormal(Constant.URL_WEIXIN_TOKEN, params, new ApiListener() {
            @Override
            public void onSuccess(String result) {
                WXEntity entity = new Gson().fromJson(result, WXEntity.class);
                getWXUserInfo(entity);
            }

            @Override
            public void onFail() {
                onLoginResult(CODE_LOGIN_FAIL);
            }
        });
    }

    /**
     * 获取微信用户的信息
     * @param entity
     */
    private void getWXUserInfo(final WXEntity entity) {
        RequestParams params = new RequestParams();
        params.add("access_token", entity.getAccess_token());
        params.add("openid", entity.getOpenid());
        EwebUtil.getInstance().doGetNormal(Constant.URL_WEIXIN_INFO, params, new ApiListener() {
            @Override
            public void onSuccess(String result) {
                WXUserEntity user = new Gson().fromJson(result, WXUserEntity.class);
                EshareApplication.getInstance().setWxUserinfo(user);
                Elog.i("保存了微信用户的信息:" + user.toString());
                onLoginResult(CODE_LOGIN_SUCCESS);
            }

            @Override
            public void onFail() {
                onLoginResult(CODE_LOGIN_FAIL);
            }
        });
    }

    /**
     * 发送微信授权登陆的结果
     * @param code CODE_LOGIN_SUCCESS, CODE_LOGIN_FAIL
     */
    private void onLoginResult(String code) {
        Intent intent = new Intent();
        intent.putExtra(KEY_LOGIN, code);
        intent.setAction(BRO_LOGIN);
        sendBroadcast(intent);
        finish();
    }
}
