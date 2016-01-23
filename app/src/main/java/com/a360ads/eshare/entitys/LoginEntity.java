package com.a360ads.eshare.entitys;

import android.text.TextUtils;
import android.util.Log;

import com.a360ads.eshare.base.BaseActivity;
import com.a360ads.eshare.base.BaseEntity;
import com.a360ads.eshare.data.ConstantCode;
import com.a360ads.eshare.utils.Elog;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * 说明：登录返回的实体类
 * Created by FuPei
 * on 2016/1/22 at 18:05
 */
public class LoginEntity extends BaseEntity {

    /**
     * uid : 93363
     * test : 0
     * profession :
     * score : 0
     * parentid : 0
     * withdraw_no : 0
     * app_bate : null
     * pushnum : 0
     * city :
     * balance : 0
     * username :
     * level : 1
     * isjpush : 1
     * fannum : 0
     * on_phone : 15208358151
     * regdate : 2016-01-15 09:56:48
     * age : 0
     * province :
     * gender :
     * openid :
     * relaynum : 0
     * earn_money : 0
     * form : android
     * miss_money : 0
     * uc_uid : 1340
     * batch_money : 0
     * isunus : 0
     * withdraw_ok : 0
     * channel_id :
     * lose_uid : 0
     * screenshotnum : 0
     * anps_source : 1
     * logindate : 2016-01-23 10:21:07
     * yao_money : 0
     * state : 1
     * gongz_openid : null
     * area :
     * weixin :
     * freeze_money : 0
     * is_accept : null
     * imageurl :
     * yjtime : null
     * channel_name :
     * hotnum : 10
     * lose : 0
     * tokenweixin :
     * source :
     * jpushid : 00033eb572d
     * channel :
     * on_pwd : 6c30590317567a465d99913cba01d663
     */
    private String uid;
    private String test;
    private String profession;
    private String score;
    private String parentid;
    private String withdraw_no;
    private Object app_bate;
    private String pushnum;
    private String city;
    private String balance;
    private String username;
    private String level;
    private String isjpush;
    private String fannum;
    private String on_phone;
    private String regdate;
    private String age;
    private String province;
    private String gender;
    private String openid;
    private String relaynum;
    private String earn_money;
    private String form;
    private String miss_money;
    private String uc_uid;
    private String batch_money;
    private String isunus;
    private String withdraw_ok;
    private String channel_id;
    private String lose_uid;
    private String screenshotnum;
    private String anps_source;
    private String logindate;
    private String yao_money;
    private String state;
    private Object gongz_openid;
    private String area;
    private String weixin;
    private String freeze_money;
    private Object is_accept;
    private String imageurl;
    private Object yjtime;
    private String channel_name;
    private String hotnum;
    private String lose;
    private String tokenweixin;
    private String source;
    private String jpushid;
    private String channel;
    private String on_pwd;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public void setWithdraw_no(String withdraw_no) {
        this.withdraw_no = withdraw_no;
    }

    public void setApp_bate(Object app_bate) {
        this.app_bate = app_bate;
    }

    public void setPushnum(String pushnum) {
        this.pushnum = pushnum;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setIsjpush(String isjpush) {
        this.isjpush = isjpush;
    }

    public void setFannum(String fannum) {
        this.fannum = fannum;
    }

    public void setOn_phone(String on_phone) {
        this.on_phone = on_phone;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setRelaynum(String relaynum) {
        this.relaynum = relaynum;
    }

    public void setEarn_money(String earn_money) {
        this.earn_money = earn_money;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public void setMiss_money(String miss_money) {
        this.miss_money = miss_money;
    }

    public void setUc_uid(String uc_uid) {
        this.uc_uid = uc_uid;
    }

    public void setBatch_money(String batch_money) {
        this.batch_money = batch_money;
    }

    public void setIsunus(String isunus) {
        this.isunus = isunus;
    }

    public void setWithdraw_ok(String withdraw_ok) {
        this.withdraw_ok = withdraw_ok;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public void setLose_uid(String lose_uid) {
        this.lose_uid = lose_uid;
    }

    public void setScreenshotnum(String screenshotnum) {
        this.screenshotnum = screenshotnum;
    }

    public void setAnps_source(String anps_source) {
        this.anps_source = anps_source;
    }

    public void setLogindate(String logindate) {
        this.logindate = logindate;
    }

    public void setYao_money(String yao_money) {
        this.yao_money = yao_money;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setGongz_openid(Object gongz_openid) {
        this.gongz_openid = gongz_openid;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public void setFreeze_money(String freeze_money) {
        this.freeze_money = freeze_money;
    }

    public void setIs_accept(Object is_accept) {
        this.is_accept = is_accept;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setYjtime(Object yjtime) {
        this.yjtime = yjtime;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public void setHotnum(String hotnum) {
        this.hotnum = hotnum;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public void setTokenweixin(String tokenweixin) {
        this.tokenweixin = tokenweixin;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setJpushid(String jpushid) {
        this.jpushid = jpushid;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setOn_pwd(String on_pwd) {
        this.on_pwd = on_pwd;
    }

    public String getUid() {
        return uid;
    }

    public String getTest() {
        return test;
    }

    public String getProfession() {
        return profession;
    }

    public String getScore() {
        return score;
    }

    public String getParentid() {
        return parentid;
    }

    public String getWithdraw_no() {
        return withdraw_no;
    }

    public Object getApp_bate() {
        return app_bate;
    }

    public String getPushnum() {
        return pushnum;
    }

    public String getCity() {
        return city;
    }

    public String getBalance() {
        return balance;
    }

    public String getUsername() {
        return username;
    }

    public String getLevel() {
        return level;
    }

    public String getIsjpush() {
        return isjpush;
    }

    public String getFannum() {
        return fannum;
    }

    public String getOn_phone() {
        return on_phone;
    }

    public String getRegdate() {
        return regdate;
    }

    public String getAge() {
        return age;
    }

    public String getProvince() {
        return province;
    }

    public String getGender() {
        return gender;
    }

    public String getOpenid() {
        return openid;
    }

    public String getRelaynum() {
        return relaynum;
    }

    public String getEarn_money() {
        return earn_money;
    }

    public String getForm() {
        return form;
    }

    public String getMiss_money() {
        return miss_money;
    }

    public String getUc_uid() {
        return uc_uid;
    }

    public String getBatch_money() {
        return batch_money;
    }

    public String getIsunus() {
        return isunus;
    }

    public String getWithdraw_ok() {
        return withdraw_ok;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public String getLose_uid() {
        return lose_uid;
    }

    public String getScreenshotnum() {
        return screenshotnum;
    }

    public String getAnps_source() {
        return anps_source;
    }

    public String getLogindate() {
        return logindate;
    }

    public String getYao_money() {
        return yao_money;
    }

    public String getState() {
        return state;
    }

    public Object getGongz_openid() {
        return gongz_openid;
    }

    public String getArea() {
        return area;
    }

    public String getWeixin() {
        return weixin;
    }

    public String getFreeze_money() {
        return freeze_money;
    }

    public Object getIs_accept() {
        return is_accept;
    }

    public String getImageurl() {
        return imageurl;
    }

    public Object getYjtime() {
        return yjtime;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public String getHotnum() {
        return hotnum;
    }

    public String getLose() {
        return lose;
    }

    public String getTokenweixin() {
        return tokenweixin;
    }

    public String getSource() {
        return source;
    }

    public String getJpushid() {
        return jpushid;
    }

    public String getChannel() {
        return channel;
    }

    public String getOn_pwd() {
        return on_pwd;
    }

    public static LoginEntity parseEntity(JSONObject json) {
        BaseEntity baseEntity = BaseEntity.parseEntity(json);
        LoginEntity entity = new Gson().fromJson(json.optString("data"), LoginEntity.class);
        if(entity == null) {
            entity = new LoginEntity();
        }
        entity.code = baseEntity.code;
        entity.msg = baseEntity.msg;
        return entity;
    }

    /**
     * 判断登陆结果
     * @param act 上下文
     * @return true，成功； false，失败
     */
    public boolean judgeLoginResult(BaseActivity act) {
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
