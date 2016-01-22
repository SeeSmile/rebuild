package com.a360ads.eshare.dialogs;

import android.app.DialogFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a360ads.eshare.R;

/**
 * 说明：普通等待条
 * Created by FuPei
 * on 2016/1/21 at 9:34
 */
public class EloadDialog extends DialogFragment {

    private String text;
    private TextView tv_text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_load, container);
        tv_text = (TextView) view.findViewById(R.id.dialog_tv);
//        tv_text.setText("");
        if(TextUtils.isEmpty(text)) {
            tv_text.setVisibility(View.GONE);
        } else {
            tv_text.setText(text);
            tv_text.setVisibility(View.VISIBLE);
        }
        return view;
    }

    /**
     * 设置进度条文本
     * @param text
     */
    public void setText(String text) {
        this.text = text;
//        tv_text.setText(text);
    }

    @Override
    public void onStart() {
        super.onStart();
        setCancelable(false);
    }
}
