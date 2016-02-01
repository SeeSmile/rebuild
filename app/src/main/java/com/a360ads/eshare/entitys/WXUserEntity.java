package com.a360ads.eshare.entitys;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * 说明：
 * note：
 * Created by FuPei
 * on 2016/1/29 at 13:56
 */
public class WXUserEntity implements Serializable{

    /**
     * openid : o_uGjxBqrCT1kk_8vrhOkUz1X4_o
     * nickname : SeeSmlie
     * sex : 1
     * language : zh_CN
     * city : Chengdu
     * province : Sichuan
     * country : CN
     * headimgurl : http://wx.qlogo.cn/mmopen/AqVMchic84PIcEWickVmQrMpxEoVjxpqabrOUmwOPzyD8nSxLXoPYF0WE5IwHNFUXsN7HiajBC7ul6PmSQKqgksGGeM3ppyOn8V/0
     * privilege : []
     * unionid : o0a-LtzfxYtJFgQvJhOL_Q0q5iXI
     */

    private String openid;
    private String nickname;
    private int sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getOpenid() {
        return openid;
    }

    public String getNickname() {
        return nickname;
    }

    public int getSex() {
        return sex;
    }

    public String getLanguage() {
        return language;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
