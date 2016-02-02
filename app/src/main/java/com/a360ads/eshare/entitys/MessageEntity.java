package com.a360ads.eshare.entitys;

/**
 * 说明：单个消息
 * Created by FuPei
 * on 2016/2/2 at 14:59
 */
public class MessageEntity {

    /**
     * id : 24549
     * uid : 93367
     * title : 积分兑换商品取消订单
     * content : 您取消了订单，订单【88积分】退还积分账户！
     * datetime : 2016-02-02 14:11:43
     * isread : 1
     * type : goods
     * isurl : 0
     * content_url : null
     */

    private String id;
    private String uid;
    private String title;
    private String content;
    private String datetime;
    private String isread;
    private String type;
    private String isurl;
    private Object content_url;

    public void setId(String id) {
        this.id = id;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIsurl(String isurl) {
        this.isurl = isurl;
    }

    public void setContent_url(Object content_url) {
        this.content_url = content_url;
    }

    public String getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getIsread() {
        return isread;
    }

    public String getType() {
        return type;
    }

    public String getIsurl() {
        return isurl;
    }

    public Object getContent_url() {
        return content_url;
    }
}
