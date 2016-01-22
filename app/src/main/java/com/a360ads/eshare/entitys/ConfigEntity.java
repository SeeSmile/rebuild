package com.a360ads.eshare.entitys;

import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * 说明：
 * note：
 * Created by FuPei
 * on 2016/1/20 at 15:05
 */
public class ConfigEntity {

    /**
     * ver : dev
     * pro : {"yinsi":"http://jzz.360netnews.com/html/yinsi.html","tixian_help":"http://jzz.360netnews.com/html/tixian_help.html","fuwu":"http://jzz.360netnews.com/html/fuwu.html","time":1453273499}
     * dev : {"yinsi":"http://jzz.360netnews.com/html/yinsi.html","tixian_help":"http://jzz.360netnews.com/html/tixian_help.html","fuwu":"http://jzz.360netnews.com/html/fuwu.html","time":1453273499}
     */

    private String ver;
    /**
     * yinsi : http://jzz.360netnews.com/html/yinsi.html
     * tixian_help : http://jzz.360netnews.com/html/tixian_help.html
     * fuwu : http://jzz.360netnews.com/html/fuwu.html
     * time : 1453273499
     */

    private ProEntity pro;
    /**
     * yinsi : http://jzz.360netnews.com/html/yinsi.html
     * tixian_help : http://jzz.360netnews.com/html/tixian_help.html
     * fuwu : http://jzz.360netnews.com/html/fuwu.html
     * time : 1453273499
     */

    private DevEntity dev;

    public static ConfigEntity parseEntity(String text) {
        return new Gson().fromJson(text, ConfigEntity.class);
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public void setPro(ProEntity pro) {
        this.pro = pro;
    }

    public void setDev(DevEntity dev) {
        this.dev = dev;
    }

    public String getVer() {
        return ver;
    }

    public ProEntity getPro() {
        return pro;
    }

    public DevEntity getDev() {
        return dev;
    }

    public static class ProEntity {
        private String yinsi;
        private String tixian_help;
        private String fuwu;
        private int time;

        public void setYinsi(String yinsi) {
            this.yinsi = yinsi;
        }

        public void setTixian_help(String tixian_help) {
            this.tixian_help = tixian_help;
        }

        public void setFuwu(String fuwu) {
            this.fuwu = fuwu;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getYinsi() {
            return yinsi;
        }

        public String getTixian_help() {
            return tixian_help;
        }

        public String getFuwu() {
            return fuwu;
        }

        public int getTime() {
            return time;
        }
    }

    public static class DevEntity {
        private String yinsi;
        private String tixian_help;
        private String fuwu;
        private int time;

        public void setYinsi(String yinsi) {
            this.yinsi = yinsi;
        }

        public void setTixian_help(String tixian_help) {
            this.tixian_help = tixian_help;
        }

        public void setFuwu(String fuwu) {
            this.fuwu = fuwu;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getYinsi() {
            return yinsi;
        }

        public String getTixian_help() {
            return tixian_help;
        }

        public String getFuwu() {
            return fuwu;
        }

        public int getTime() {
            return time;
        }
    }
}
