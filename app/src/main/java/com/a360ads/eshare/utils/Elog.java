package com.a360ads.eshare.utils;

import android.util.Log;

/**
 * 说明：日志打印
 * Created by FuPei
 * on 2016/1/19 at 10:25
 */
public class Elog {

    private static final boolean isLog = true;
    private static final String mTag = "Flog";

    public static void i(String text) {
        if(isLog) {
            if(text == null) {
                text = "";
            }
            Log.i(mTag, text);
        }
    }

    public static void i(boolean canlog, String text) {
        if(isLog && canlog) {
            if(text == null) {
                text = "";
            }
            Log.i(mTag, text);
        }
    }

    public static void e(String text) {
        if(isLog) {
            if(text == null) {
                text = "";
            }
            Log.e(mTag, text);
        }
    }
}
