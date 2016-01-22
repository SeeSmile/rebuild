package com.a360ads.eshare.entitys;

import com.a360ads.eshare.base.BaseEntity;
import com.a360ads.eshare.utils.Elog;
import com.google.gson.Gson;

/**
 * 说明：登录返回的实体类
 * Created by FuPei
 * on 2016/1/22 at 18:05
 */
public class LoginEntity extends BaseEntity {

    private String text;

    public LoginEntity(String jsontext) {
        Elog.i("LoginEntity");
        text = jsontext;
    }

    @Override
    public String getJson() {
        return text;
    }
}
