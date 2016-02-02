package com.a360ads.eshare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.a360ads.eshare.R;
import com.a360ads.eshare.entitys.MessageEntity;

import java.util.ArrayList;

/**
 * 说明：消息中心适配器
 * Created by FuPei
 * on 2016/2/2 at 14:43
 */
public class MessageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<MessageEntity> data;
    private LayoutInflater mInflater;

    public MessageAdapter(Context context, ArrayList<MessageEntity> data) {
        this.mContext = context;
        this.data = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MsgHold hold;
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.item_message, null);
            hold = new MsgHold();
            hold.tv_title = (TextView) convertView.findViewById(R.id.item_msg_title);
            hold.tv_time = (TextView) convertView.findViewById(R.id.item_msg_time);
            hold.tv_text = (TextView) convertView.findViewById(R.id.item_msg_text);
            hold.tv_look = (TextView) convertView.findViewById(R.id.item_msg_look);
            hold.iv_isread = (ImageView) convertView.findViewById(R.id.item_msg_isread);
            convertView.setTag(hold);
        } else {
            hold = (MsgHold) convertView.getTag();
        }
        MessageEntity bean = data.get(position);
        if(bean.getIsread().equals("1")) {
            hold.iv_isread.setBackgroundResource(R.drawable.shape_icon_msg_read);
        } else if(bean.getIsread().equals("0")){
            hold.iv_isread.setBackgroundResource(R.drawable.shape_icon_msg_normal);
        }
        final String title = bean.getTitle();
        final String content = bean.getContent();
        final String time = bean.getDatetime();
        final String id = bean.getId();
        hold.tv_title.setText(title);
        hold.tv_time.setText(time);
        hold.tv_text.setText(content);
        return convertView;
    }

    private class MsgHold {
        private TextView tv_title;
        private TextView tv_look;
        private TextView tv_time;
        private TextView tv_text;
        private ImageView iv_isread;
    }
}
