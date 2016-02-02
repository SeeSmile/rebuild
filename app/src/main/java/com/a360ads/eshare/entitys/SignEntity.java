package com.a360ads.eshare.entitys;

import com.google.gson.Gson;

import java.util.List;

/**
 * 说明：签到实体类
 * Created by FuPei
 * on 2016/2/2 at 9:31
 */
public class SignEntity {

    /**
     * scoreinfo : [{"datetime":"2016-02-02 09:19:28","datetime_stamp":"1454375968"}]
     * score : 10
     * currentscore : 10
     * currenttime : 1454376670
     * signedhelpurl : http://www.xiangexia.com/html/signin.html
     */

    private String score;
    private String currentscore;
    private int currenttime;
    private String signedhelpurl;
    /**
     * datetime : 2016-02-02 09:19:28
     * datetime_stamp : 1454375968
     */

    private List<ScoreinfoEntity> scoreinfo;

    public void setScore(String score) {
        this.score = score;
    }

    public void setCurrentscore(String currentscore) {
        this.currentscore = currentscore;
    }

    public void setCurrenttime(int currenttime) {
        this.currenttime = currenttime;
    }

    public void setSignedhelpurl(String signedhelpurl) {
        this.signedhelpurl = signedhelpurl;
    }

    public void setScoreinfo(List<ScoreinfoEntity> scoreinfo) {
        this.scoreinfo = scoreinfo;
    }

    public String getScore() {
        return score;
    }

    public String getCurrentscore() {
        return currentscore;
    }

    public int getCurrenttime() {
        return currenttime;
    }

    public String getSignedhelpurl() {
        return signedhelpurl;
    }

    public List<ScoreinfoEntity> getScoreinfo() {
        return scoreinfo;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public static class ScoreinfoEntity {
        private String datetime;
        private String datetime_stamp;

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public void setDatetime_stamp(String datetime_stamp) {
            this.datetime_stamp = datetime_stamp;
        }

        public String getDatetime() {
            return datetime;
        }

        public String getDatetime_stamp() {
            return datetime_stamp;
        }

        @Override
        public String toString() {
            return new Gson().toJson(this);
        }
    }
}
