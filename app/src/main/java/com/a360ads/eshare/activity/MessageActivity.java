package com.a360ads.eshare.activity;

import android.os.Bundle;
import android.view.View;

import com.a360ads.eshare.EshareApplication;
import com.a360ads.eshare.R;
import com.a360ads.eshare.adapter.MessageAdapter;
import com.a360ads.eshare.base.BaseActivity;
import com.a360ads.eshare.data.Constant;
import com.a360ads.eshare.entitys.MessageEntity;
import com.a360ads.eshare.interfaces.ApiListener;
import com.a360ads.eshare.utils.Elog;
import com.a360ads.eshare.utils.EwebUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;

/**
 * 说明：消息中心界面
 * Created by FuPei
 * on 2016/1/25 at 14:19
 */
public class MessageActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2{

    private final boolean islog = true;
    @InjectView(R.id.message_lv)
    public PullToRefreshListView mlistview;

    private MessageAdapter messageAdapter;
    private ArrayList<MessageEntity> mData;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_message);
        setTitleStyle(STYLE_BOTH_TB);
        setTitle("消息中心");
        mlistview.setMode(PullToRefreshBase.Mode.BOTH);
        mlistview.setOnRefreshListener(this);
    }

    private void initListener() {
        setOnbackclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        mData = new ArrayList<>();
        messageAdapter = new MessageAdapter(this, mData);
        mlistview.setAdapter(messageAdapter);
        currentPage = 1;
        getMsgData();
    }

    /**
     * 获取数据
     */
    private void getMsgData() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", EshareApplication.getInstance().getUserInfo().getUid());
        map.put("limit", "10");
        map.put("page", currentPage + "");
        EwebUtil.getInstance().doSafeGet(Constant.URL_MSG_LIST, map, new ApiListener.JsonRequest() {
            @Override
            public void onJsonLoad(JSONObject json) {
                if (json.optString("code").equals("1")) {
                    JSONArray array = json.optJSONArray("data");
                    Gson gson = new Gson();
                    if (array != null && array.length() > 0) {
                        for (int i = 0; i < array.length(); i++) {
                            mData.add(gson.fromJson(array.optJSONObject(i).toString(), MessageEntity.class));
                        }
                    }
                    messageAdapter.notifyDataSetChanged();
                } else {
                    Elog.i(islog, "获取的code不为1");
                }
                mlistview.onRefreshComplete();
            }

            @Override
            public void onJsonFail() {
                toast("没有信息");
                mlistview.onRefreshComplete();
            }
        });
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mData = new ArrayList<>();
        currentPage = 1;
        getMsgData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        currentPage++;
        getMsgData();
    }
}
