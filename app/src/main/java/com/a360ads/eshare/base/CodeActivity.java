package com.a360ads.eshare.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.a360ads.eshare.data.Constant;
import com.a360ads.eshare.interfaces.ApiListener;
import com.a360ads.eshare.interfaces.EcodeHelper;
import com.a360ads.eshare.utils.Elog;
import com.a360ads.eshare.utils.EwebUtil;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明：
 * Created by FuPei
 * on 2016/1/21 at 17:37
 */
public abstract class CodeActivity extends BaseActivity implements EcodeHelper {

    private final String TYPE_REGISTER = "1";
    private final String TYPE_FOUND = "2";

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getVGetcode().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPhoneFormat()) {
                    judgePhoneByServer();
                }
            }
        });
    }

    public void getServerCode() {
        showDialog("获取中...");
    }

    /**
     * 检查手机号的格式
     *
     * @return
     */
    private boolean checkPhoneFormat() {
        String phone = getEtPhone().getText().toString();
        if (TextUtils.isEmpty(phone)) {
            toast("输入的手机号码不能为空");
            return false;
        }
        if (phone.length() != 11) {
            toast("手机位数不正确");
            return false;
        }
        Elog.i(phone.charAt(0) + "");
        if ('1' != phone.charAt(0)) {
            toast("手机号码格式不正确");
            return false;
        }
        return true;
    }

    /**
     * 连接服务器判断手机号码是否合法
     */
    private void judgePhoneByServer() {
        Map<String, String> params = new HashMap<>();
        params.put("onphone", getEtPhone().getText().toString());
        params.put("type", getCodeType());
        EwebUtil.getInstance().doSafePost(Constant.URL_JUDGE_PHONE, params, new ApiListener.JsonRequest() {

            @Override
            public void onJsonFail() {
                Elog.i("onjsonfail");
            }

            @Override
            public void onJsonLoad(JSONObject json) {
                Elog.i("jsong = " + json.toString());
            }
        });
    }

    private String getCodeType() {
        if(getType() == TYPE.FOUND) {
            return TYPE_FOUND;
        } else if(getType() == TYPE.REGISTER) {
            return TYPE_REGISTER;
        } else {
            return "";
        }
    }
}
