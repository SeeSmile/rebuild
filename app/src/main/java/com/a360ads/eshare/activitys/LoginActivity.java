package com.a360ads.eshare.activitys;

import android.content.Intent;
import android.os.Bundle;
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
import com.a360ads.eshare.entitys.ConfigEntity;
import com.a360ads.eshare.interfaces.ApiListener;
import com.a360ads.eshare.utils.EToastUtil;
import com.a360ads.eshare.utils.Elog;
import com.a360ads.eshare.utils.EwebUtil;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

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
        EwebUtil.getInstance().doGet(Constant.SERVER_URL_CONFIG, null, new ApiListener() {
            @Override
            public void onSuccess(String result) {
                EshareApplication.getInstance().setmConfigEntity(ConfigEntity.parseEntity(result));
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
        Elog.i("loginWX");
        Platform wechat= ShareSDK.getPlatform(LoginActivity.this, Wechat.NAME);
        wechat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Elog.i("onComplete");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Elog.i("onError");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Elog.i("onCancel");
            }
        });
        wechat.authorize();
//        SendAuth.Req req = new SendAuth.Req();
//        req.scope = "snsapi_userinfo";
//        req.state = "wechat_sdk_demo";
//        EshareApplication.getInstance().getApi(LoginActivity.this).sendReq(req);
    }

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
                    if(judgeLoginResult(LoginActivity.this, entity.code)) {
                        finish();
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

}
