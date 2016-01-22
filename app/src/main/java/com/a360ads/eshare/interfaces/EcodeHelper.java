package com.a360ads.eshare.interfaces;

import android.view.View;
import android.widget.EditText;

/**
 * 说明：获取验证码辅助类
 * Created by FuPei
 * on 2016/1/21 at 17:41
 */
public interface EcodeHelper {
    enum TYPE{
        FOUND, REGISTER
    }

    /**
     * 获取验证码的type
     * @return
     */
    TYPE getType();

    /**
     *
     * @return
     */
    EditText getEtPhone();

    /**
     *
     * @return
     */
    EditText getEtcode();

    /**
     *
     * @return
     */
    View getVGetcode();
}
