package com.a360ads.eshare.entitys;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * 说明：
 * note：
 * Created by FuPei
 * on 2016/1/29 at 14:32
 */
public class UserEntity implements Serializable {

    /**
     * uid : 77
     * username : 培培
     * level : 1
     * imageurl : http://wx.qlogo.cn/mmopen/LySgjn21ptEUOlq02CxHysTQEd2UIlNDlib6476SN3KNu1WA6xseAbxyf8fqHJmexWypaibKStDS1T4HdDbuJdiaVXxHFX6ibWRc/0
     * weixin : o0a-LtzfxYtJFgQvJhOL_Q0q5iXI
     * hotnum : 1136
     * state : 1
     * on_phone : 15208358151
     * token : NWRlNjNkMWFhYThjODVlYTRmMTZmN2M5OGM0ZjdhZDY=
     */

    private String uid;
    private String username;
    private String level;
    private String imageurl;
    private String weixin;
    private String hotnum;
    private String state;
    private String on_phone;
    private String token;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public void setHotnum(String hotnum) {
        this.hotnum = hotnum;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setOn_phone(String on_phone) {
        this.on_phone = on_phone;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getLevel() {
        return level;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getWeixin() {
        return weixin;
    }

    public String getHotnum() {
        return hotnum;
    }

    public String getState() {
        return state;
    }

    public String getOn_phone() {
        return on_phone;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
