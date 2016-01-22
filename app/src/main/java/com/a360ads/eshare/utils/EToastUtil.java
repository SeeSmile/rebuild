package com.a360ads.eshare.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 说明：toast工具类
 * Created by FuPei
 * on 2016/1/19 at 10:29
 */
public class EToastUtil {

    public static void show(Context context, String text) {
        if(text == null) {
            text = "";
        }
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
