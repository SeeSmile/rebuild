package com.a360ads.eshare.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a360ads.eshare.interfaces.ToolbarListener;

/**
 * 说明：
 * note：
 * Created by FuPei
 * on 2016/1/21 at 15:57
 */
public class ToolbarView extends LinearLayout {

    public static final int STYLE_DEFAULT = 0;
    public static final int STYLE_ONLY_BACK = 1;
    public static final int STYLE_TEXT_BACK = 2;
    public static final int STYLE_ONLY_ICON = 3;

    private TextView tv_back;
    private ImageView iv_back;
    private ImageView iv_icon;
    private LinearLayout ly_back;

    private ToolbarListener mListener;

    public ToolbarView(Context context) {
        this(context, null);
    }

    public ToolbarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToolbarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initListener();
    }

    private void initView(Context context) {
        setOrientation(HORIZONTAL);
        tv_back = new TextView(context);
        iv_back = new ImageView(context);
        iv_icon = new ImageView(context);
        ly_back = new LinearLayout(context);
        addView(tv_back);
        setStyle(STYLE_TEXT_BACK);
    }

    private void initListener() {
        ly_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null) {
                    mListener.onBackClick();
                }
            }
        });

        iv_icon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onIconClick();
                }
            }
        });
    }

    public void setStyle(int style) {
        if(style == STYLE_ONLY_BACK) {
            quickSet(true, false, false);
        } else if(style == STYLE_TEXT_BACK) {
            quickSet(true, true, false);
        } else if(style == STYLE_ONLY_ICON) {
            quickSet(false, false, true);
        } else {
            quickSet(false, false, false);
        }
    }

    public void setToolbarListener(ToolbarListener l) {
        this.mListener = l;
    }

    @SuppressWarnings("ResourceType")
    private void quickSet(boolean a, boolean b, boolean c) {
        iv_back.setVisibility(getViewVisible(a));
        tv_back.setVisibility(getViewVisible(b));
        iv_icon.setVisibility(getViewVisible(c));
    }

    private int getViewVisible(boolean b) {
        if(b) {
            return View.VISIBLE;
        } else {
            return View.GONE;
        }
    }
}
