package com.a360ads.eshare.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 说明：
 * note：
 * Created by FuPei
 * on 2016/1/20 at 15:33
 */
public interface ApiListener {

    void onSuccess(String result);
    void onFail();

    abstract class JsonRequest implements ApiListener{

        @Override
        public void onSuccess(String result) {
            try {
                onJsonLoad(new JSONObject(result));
            } catch (JSONException e) {
                System.out.println("结果转json失败");
            }
        }

        @Override
        public void onFail() {
            onJsonFail();
        }

        public abstract void onJsonFail();
        public abstract void onJsonLoad(JSONObject json);
    }

}
