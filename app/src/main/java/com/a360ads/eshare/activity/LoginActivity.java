package com.a360ads.eshare.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a360ads.eshare.EshareApplication;
import com.a360ads.eshare.R;
import com.a360ads.eshare.base.BaseActivity;
import com.a360ads.eshare.base.BaseEntity;
import com.a360ads.eshare.data.Constant;
import com.a360ads.eshare.data.ConstantCode;
import com.a360ads.eshare.data.ESharedPreferences;
import com.a360ads.eshare.entitys.ConfigEntity;
import com.a360ads.eshare.entitys.UserEntity;
import com.a360ads.eshare.entitys.WXUserEntity;
import com.a360ads.eshare.interfaces.ApiListener;
import com.a360ads.eshare.utils.EToastUtil;
import com.a360ads.eshare.utils.Elog;
import com.a360ads.eshare.utils.EwebUtil;
import com.a360ads.eshare.wxapi.WXEntryActivity;
import com.google.gson.Gson;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendAuth;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 说明：登陆界面
 * Created by FuPei
 * on 2016/1/19 at 10:48
 */
public class LoginActivity extends BaseActivity {

    //点击组件的标记
    public final int TAG_SERVICE = 1;
    public final int TAG_PRICACY = 2;

    //注册组件
    @InjectView(R.id.et_phone)
    public EditText et_phone;
    @InjectView(R.id.et_passwd)
    public EditText et_passwd;
    @InjectView(R.id.btn_register)
    public Button btn_register;
    @InjectView(R.id.btn_login)
    public Button btn_login;
    @InjectView(R.id.tv_forget)
    public TextView tv_forget;
    @InjectView(R.id.iv_login_wx)
    public ImageView iv_login_wx;
    @InjectView(R.id.tv_service)
    public TextView tv_service;
    @InjectView(R.id.tv_privacy)
    public TextView tv_privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitleStyle(STYLE_ONLY_TITLE);
        setTitle("登陆");
        initData();
        registerLoginBro();
    }

    /**
     * 注册微信登陆广播
     */
    private void registerLoginBro() {
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String code = intent.getStringExtra(WXEntryActivity.KEY_LOGIN);
                if(code != null) {
                    if(code.equals(WXEntryActivity.CODE_LOGIN_FAIL)) {
                        toast("登陆失败");
                    } else if(code.equals(WXEntryActivity.CODE_LOGIN_SUCCESS)){
                        loginWxByServer();
                    }
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(WXEntryActivity.BRO_LOGIN);
        registerReceiver(receiver, filter);
    }

    @OnClick({R.id.btn_login, R.id.btn_register, R.id.iv_login_wx, R.id.tv_forget, R.id.tv_service,
            R.id.tv_privacy})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_register:
                goActivity(RegisterActivity.class);
                break;
            case R.id.tv_forget:
                goActivity(AlterPwdActivity.class);
                break;
            case R.id.tv_service:
                showWebInfo(TAG_SERVICE);
                break;
            case R.id.tv_privacy:
                showWebInfo(TAG_PRICACY);
                break;
            case R.id.iv_login_wx:
                loginByWx();
                break;
        }
    }

    private void initData() {
        setTitle("登陆");
    }

    /**
     * 启动网页界面显示信息
     *
     * @param tag 组件标志
     */
    public void showWebInfo(int tag) {
        showDialog("加载中...");
        if (existConfig()) {
            Intent intent = new Intent(LoginActivity.this, EwebActivity.class);
            if (tag == TAG_SERVICE) {
                intent.putExtra(EwebActivity.EXTRA_TITLE, getString(R.string.text_service));
                intent.putExtra(EwebActivity.EXTRA_URL,
                        EshareApplication.getInstance().getmConfigEntity().getPro().getFuwu());
            } else if (tag == TAG_PRICACY) {
                intent.putExtra(EwebActivity.EXTRA_TITLE, getString(R.string.text_pricacy));
                intent.putExtra(EwebActivity.EXTRA_URL,
                        EshareApplication.getInstance().getmConfigEntity().getPro().getYinsi());
            }
            dismissDialog();
            startActivity(intent);
        } else {
            setUrlConfig(tag);
        }
    }

    /**
     * 是否存在全局url的信息
     *
     * @return true：是； false:否
     */
    public boolean existConfig() {
        return EshareApplication.getInstance().getmConfigEntity() != null;
    }

    /**
     * 设置全局url配置信息
     */
    public void setUrlConfig(final int tag) {
        EwebUtil.getInstance().doGetNormal(Constant.SERVER_URL_CONFIG, null, new ApiListener() {
            @Override
            public void onSuccess(String result) {
                EshareApplication.getInstance().setConfigEntity(ConfigEntity.parseEntity(result));
                showWebInfo(tag);
            }

            @Override
            public void onFail() {
                dismissDialog();
                EToastUtil.show(LoginActivity.this, "加载失败");
            }
        });
    }

    /**
     * 调用微信登陆
     */
    private void loginByWx() {
        IWXAPI api = WXAPIFactory.createWXAPI(LoginActivity.this, Constant.APP_ID);
        //判断是否安装了微信客户端
        if(api.isWXAppInstalled()) {
            showDialog("跳转中...");
            //保存发送微信登陆的状态码，以供识别
            String state = System.currentTimeMillis() + "login";
            EshareApplication.getInstance().setState_login(state);
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = state;
            if(api.sendReq(req)) {
                dismissDialog();
            }
        } else {
            new AlertDialog.Builder(LoginActivity.this).setTitle("提示").setMessage("请先安装微信客户端")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        }
    }

    /**
     * 手机号登陆
     */
    private void login() {
        String phone = et_phone.getText().toString();
        String password = et_passwd.getText().toString();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            toast("请输入完整登录信息");
        } else {
            showDialog("登陆中...");
            Map<String, String> params = new HashMap<>();
            params.put("on_phone", phone);
            params.put("on_pwd", password);
            EwebUtil.getInstance().doSafePost(Constant.URL_USER_LOGIN, params, new ApiListener.JsonRequest() {
                @Override
                public void onJsonLoad(JSONObject json) {
                    dismissDialog();
                    BaseEntity entity = BaseEntity.parseEntity(json);
                    if (judgeLoginResult(LoginActivity.this, entity.code)) {
                        UserEntity user = new Gson().fromJson(entity.info, UserEntity.class);
                        EshareApplication.getInstance().setUserInfo(user);
                        Elog.i("user = " + user.toString());
                        goActivity(MainActivity.class);
                    }
                }

                @Override
                public void onJsonFail() {
                    toast("登陆失败。请检查网络");
                    dismissDialog();
                }
            });
        }
    }

    /**
     * 判断登陆结果
     * @param act 上下文
     * @return true，成功； false，失败
     */
    public boolean judgeLoginResult(BaseActivity act, String code) {
        if(code.equals(ConstantCode.CODE_ERR_PWD) || code.equals(ConstantCode.CODE_NO_USER)) {
            act.toast("用户名或秘密错误!");
        } else if(code.equals(ConstantCode.CODE_SUCCESS)) {
            return true;
        } else {
            act.toast("登陆发生未知错误!");
        }
        return false;
    }

    /**
     * 用户微信授权登陆成功后，调用此方法进行微信登陆
     */
    private void loginWxByServer() {
//        showDialog("登陆中...");
        Map<String, String> map = new HashMap<>();
        WXUserEntity entity = EshareApplication.getInstance().getWxUserinfo();
        map.put("platform","android");
        map.put("weixin", entity.getUnionid());
        map.put("imageurl", entity.getHeadimgurl());
        map.put("username", entity.getNickname());
        map.put("source", new ESharedPreferences(LoginActivity.this).getValueByKey(ESharedPreferences.KEY_CHANNEL));
        EwebUtil.getInstance().doSafePost(Constant.URL_LOGIN_WX, map, new ApiListener() {
            @Override
            public void onSuccess(String result) {
                Elog.i("调用服务器微信登陆成功:\n" + result);
                try {
                    JSONObject json = new JSONObject(result);
                    UserEntity entity = new Gson().fromJson(json.optString("data"), UserEntity.class);
                    Elog.i("保存了用户的信息:\n" + entity.toString());
                    EshareApplication.getInstance().setUserInfo(entity);
                    goActivity(MainActivity.class);
                } catch (JSONException e) {
                    Elog.i("微信登陆错误");
                }
            }

            @Override
            public void onFail() {
                Elog.i("调用服务器微信登陆失败了");
            }
        });
    }
}
