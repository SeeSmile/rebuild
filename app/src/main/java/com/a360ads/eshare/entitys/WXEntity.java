package com.a360ads.eshare.entitys;

/**
 * 说明：
 * note：
 * Created by FuPei
 * on 2016/1/29 at 13:00
 */
public class WXEntity {

    /**
     * access_token : OezXcEiiBSKSxW0eoylIeK5ruvxmV90Go_Dx7pPY7E7h-Fo3GNe53K3wMbXzfxg0pofI7kk7Vu1vfkAx397HTAlwSa-d7Iwu3y1zG1CyA3R_VZum2RHFvbXOK7VQPf3jTlhIwM0GAWkaF2EmoQd9pg
     * expires_in : 7200
     * refresh_token : OezXcEiiBSKSxW0eoylIeK5ruvxmV90Go_Dx7pPY7E7h-Fo3GNe53K3wMbXzfxg0JeZeFfDrbT7KucdK2eH-mXbRXXImBGFdf_fwmywM1TFN90G39sBN6A7qB3cqC-_UL4xk6rfet7Og5dXMc9Oihg
     * openid : o_uGjxBqrCT1kk_8vrhOkUz1X4_o
     * scope : snsapi_userinfo
     * unionid : o0a-LtzfxYtJFgQvJhOL_Q0q5iXI
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public String getScope() {
        return scope;
    }

    public String getUnionid() {
        return unionid;
    }
}
