package com.a360ads.eshare.base;

import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * 说明：最基本的服务器返回实体
 * Created by FuPei
 * on 2016/1/22 at 17:51
 */
public class BaseEntity{

    public String code;
    public String msg;

    public static BaseEntity parseEntity(JSONObject json) {
        BaseEntity entity = new Gson().fromJson(json.toString(), BaseEntity.class);
        return entity;
    }
}
