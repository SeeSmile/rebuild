package com.a360ads.eshare.base;

import com.a360ads.eshare.utils.Elog;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;

/**
 * 说明：最基本的服务器返回实体
 * Created by FuPei
 * on 2016/1/22 at 17:51
 */
public abstract class BaseEntity {

    public String data;
    public String code;
    public String msg;

    public BaseEntity() {
        Elog.i("baseEntity()");
        Gson gson = new Gson();
        BaseEntity entity = gson.fromJson(getJson(), BaseEntity.class);
        this.data = entity.data;
        this.code = entity.code;
        this.msg = entity.msg;
    }

    public abstract String getJson();
}
