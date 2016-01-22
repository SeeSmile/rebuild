package com.a360ads.eshare.data;

import com.a360ads.eshare.entitys.ConfigEntity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 说明：
 * note：
 * Created by FuPei
 * on 2016/1/22 at 13:22
 */
public class PublicParams {
    /**
     * 设备 唯一id  (有就传)
     */
    private String public_uuid;
    /**
     * 渠道 (必有的)
     */
    private String public_score;
    /**
     * 设备类型  (必有的) 1:ios
     */
    private String public_deviceType;
    /**
     * 当前版本(必有的)
     */
    private String public_version;
    /**
     * :微信id (有就传)
     */
    private String public_weiXinId;
    /**
     * : 网络类型 1:非wifi  2:wifi
     */
    private String public_reachabilityType;
    /**
     * : 网络类型字符串 notWifi、wifi
     */
    private String public_reachability;
    /**
     * ：当前 ip
     */
    private String public_Ip;
    /**
     * : 当前个人端用户id
     */
    private String public_uid;

    public PublicParams(PublicParamsInterFace face) {
        this.public_deviceType = face.getPublic_deviceType();
        this.public_Ip = face.getPublic_Ip();
        this.public_reachability = face.getPublic_reachability();
        this.public_reachabilityType = face.getPublic_reachabilityType();
        this.public_score = face.getPublic_score();
        this.public_uid = face.getPublic_uid();
        this.public_version = face.getPublic_version();
        this.public_weiXinId = face.getPublic_weiXinId();
        this.public_uuid = face.getPublic_uuid();
    }

    public interface PublicParamsInterFace {

        String getPublic_uuid();

        String getPublic_score();

        String getPublic_deviceType();

        String getPublic_version();

        String getPublic_weiXinId();

        String getPublic_reachabilityType();

        String getPublic_reachability();

        String getPublic_Ip();

        String getPublic_uid();
    }

    public JSONObject toJsonObj() {
        Gson gson = new Gson();
        String text = gson.toJson(this);
        try {
            return new JSONObject(text);
        } catch (JSONException e) {
            return null;
        }
    }

}
