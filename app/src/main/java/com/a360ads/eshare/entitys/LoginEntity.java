package com.a360ads.eshare.entitys;

import android.content.Context;
import android.text.TextUtils;

import com.a360ads.eshare.base.BaseActivity;
import com.a360ads.eshare.base.BaseEntity;
import com.a360ads.eshare.utils.Elog;
import com.google.gson.Gson;

/**
 * 说明：登录返回的实体类
 * Created by FuPei
 * on 2016/1/22 at 18:05
 */
public class LoginEntity extends BaseEntity {

    private final String CODE_NO_USER = "0";
    private final String CODE_ERR_PWD = "3";
    private final String CODE_SUCCESS = "1";

    private String data;

    public static LoginEntity parseEntity(String jsontext) {
        Gson gson = new Gson();
        return gson.fromJson(jsontext, LoginEntity.class);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * 判断登录结果
     * @param act
     * @return
     */
    public boolean judgeLoginResult(BaseActivity act) {
        if(!TextUtils.isEmpty(code)) {
            if(code.equals(CODE_ERR_PWD) || code.equals(CODE_NO_USER)) {
                act.toast("用户名或密码错误!");
            } else if(code.equals(CODE_SUCCESS)) {
                return true;
            }
        }
        return false;
    }
}
