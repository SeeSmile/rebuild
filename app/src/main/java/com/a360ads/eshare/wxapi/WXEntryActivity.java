package com.a360ads.eshare.wxapi;

import android.os.Bundle;
import com.a360ads.eshare.utils.Elog;
import cn.sharesdk.wechat.utils.WXMediaMessage;
import cn.sharesdk.wechat.utils.WechatHandlerActivity;

/**
 * 说明：微信接口
 * Created by FuPei
 * on 2016/1/27 at 10:13
 */
public class WXEntryActivity extends WechatHandlerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Elog.i("onCreate");

    }

    @Override
    public void onShowMessageFromWXReq(WXMediaMessage msg) {
        super.onShowMessageFromWXReq(msg);
        Elog.i("onshow");
    }

    @Override
    public void onGetMessageFromWXReq(WXMediaMessage msg) {
        super.onGetMessageFromWXReq(msg);
        Elog.i("onget");
    }
}
