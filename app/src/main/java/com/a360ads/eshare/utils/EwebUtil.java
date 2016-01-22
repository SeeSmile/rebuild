package com.a360ads.eshare.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.JsonToken;

import com.a360ads.eshare.EshareApplication;
import com.a360ads.eshare.interfaces.ApiListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * 说明：网络连接接口
 * Created by FuPei
 * on 2016/1/20 at 15:31
 */
public class EwebUtil {

    private final static String DATA = "data";
    private static EwebUtil instance;
    private static AsyncHttpClient mClient;
    private RequestParams mParams;

    private EwebUtil() {

    }

    public static EwebUtil getInstance() {
        if(instance == null) {
            instance = new EwebUtil();
            mClient = new AsyncHttpClient();
        }
        return instance;
    }

    /**
     * 停止所有请求
     * @param context
     */
    public void stopAllRequest(Context context) {
        if(mClient != null) {
            mClient.cancelRequests(context, true);
        }
    }

    public void doGet(String url, final ApiListener listener) {
        mClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(null == responseBody) {
                    listener.onFail();
                } else {
                    String info = new String(responseBody);
                    if(TextUtils.isEmpty(info)) {
                        listener.onFail();
                    } else {
                        listener.onSuccess(info);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                listener.onFail();
            }

        });
    }

    public void doSafeGet(String url, RequestParams params, ApiListener listener) {

    }

    public void doSafePost(String url, RequestParams params, ApiListener listener) {
        Map<String, String> map = new HashMap<>();
        JSONObject json = EshareApplication.getInstance().getmPublicParams().toJsonObj();
        map.putAll(jsonToMap(json));
        map.putAll(jsonToMap(paramToJson(params)));
        json = new JSONObject(map);
        mParams = new RequestParams();
        mParams.put(DATA, textToDes(json.toString()));
        doPost(url, mParams, listener);
    }

    private void doPost(String url, RequestParams params, final ApiListener listener) {
        mClient.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (null == responseBody) {
                    listener.onFail();
                } else {
                    String info = new String(responseBody);
                    if (TextUtils.isEmpty(info)) {
                        listener.onFail();
                    } else {
                        String jsontext = desToText(info);
                        listener.onSuccess(jsontext);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                listener.onFail();
            }

        });
    }

    /**
     * 将参数转jsonObj
     * @param params
     * @return
     */
    private JSONObject paramToJson(RequestParams params) {
        int pst;
        String key;
        String value;
        JSONObject json = new JSONObject();
        String[] texts = params.toString().split("&");
        for(String text : texts) {
            pst = text.indexOf("=");
            key = text.substring(0, pst);
            value = text.substring(pst + 1, text.length());
            try {
                json.put(key, value);
            } catch (JSONException e) {
                Elog.e("将参数转json错误");
            }
        }
        return json;
    }

    private String textToDes(String text) {
        return DESUtils.ebotongEncrypto(text);
    }

    private String desToText(String text) {
        return DESUtils.ebotongDecrypto(text);
    }

    private HashMap<String, String> jsonToMap(JSONObject jsonObject) {
        HashMap<String, String> data = new HashMap<>();
        Iterator it = jsonObject.keys();
        // 遍历jsonObject数据，添加到Map对象
        while (it.hasNext())
        {
            String key = String.valueOf(it.next());
            String value = jsonObject.optString(key);
            data.put(key, value);
        }
        return data;
    }
}
