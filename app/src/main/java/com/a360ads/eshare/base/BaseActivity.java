package com.a360ads.eshare.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.a360ads.eshare.EshareApplication;
import com.a360ads.eshare.R;
import com.a360ads.eshare.dialogs.EloadDialog;
import com.a360ads.eshare.utils.EwebUtil;
import com.a360ads.eshare.views.ToolbarView;

import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

/**
 * 说明：基础活动类
 * Created by FuPei
 * on 2016/1/19 at 10:47
 */
public class BaseActivity extends AppCompatActivity {

    private final String TAG_DIALOG = "diaog";

    public final int STYLE_ONLY_TITLE = 1;
    public final int STYLE_BOTH_TB = 2;
    public final int STYLE_BOTH_ALL = 3;
    public final int STYLE_BOTH_TI = 4;

    private EloadDialog mDialog;
    private boolean isDialogShowing;
    private TextView tv_title;
    private ImageView iv_icon;
    private LinearLayout ly_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBaseData();
    }

    /**
     * 初始化数据
     */
    protected void initBaseData() {
        EshareApplication.getInstance().addActivity(this);
        mDialog = new EloadDialog();
        isDialogShowing = false;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
    }

    /**
     * 跳转界面
     * @param toClass
     */
    public void goActivity(Class<?> toClass) {
        startActivity(new Intent(this, toClass));
    }

    @Override
    protected void onStop() {
        super.onStop();
        EwebUtil.getInstance().stopAllRequest(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    /**
     * 显示加载框
     * @param text
     */
    public void showDialog(String text) {
        if(!isDialogShowing) {
            mDialog.setText(text);
            mDialog.show(getFragmentManager(), TAG_DIALOG);
            isDialogShowing = true;
        }
    }

    /**
     * 移除加载框
     */
    public void dismissDialog() {
        if(isDialogShowing) {
            mDialog.dismiss();
            isDialogShowing = false;
        }
    }

    /**
     * 设置标题主题样式
     * @param style
     */
    public void setTitleStyle(int style) {
        tv_title = (TextView) findViewById(R.id.tv_title);
        ly_back = (LinearLayout) findViewById(R.id.ly_back);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        switch (style){
            case STYLE_BOTH_ALL:
                tv_title.setVisibility(View.VISIBLE);
                ly_back.setVisibility(View.VISIBLE);
                iv_icon.setVisibility(View.VISIBLE);
                break;
            case STYLE_BOTH_TB:
                tv_title.setVisibility(View.VISIBLE);
                ly_back.setVisibility(View.VISIBLE);
                iv_icon.setVisibility(View.GONE);
                break;
            case STYLE_BOTH_TI:
                tv_title.setVisibility(View.VISIBLE);
                ly_back.setVisibility(View.GONE);
                iv_icon.setVisibility(View.VISIBLE);
                break;
            case STYLE_ONLY_TITLE:
                tv_title.setVisibility(View.VISIBLE);
                ly_back.setVisibility(View.GONE);
                iv_icon.setVisibility(View.GONE);
                break;
        }
    }

    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void setTitle(String title) {
        tv_title.setText(title);
    }

    public void setOnbackclick(View.OnClickListener listener) {
        ly_back.setOnClickListener(listener);
    }

    public void setOnIconClick(View.OnClickListener listener) {
        iv_icon.setOnClickListener(listener);
    }

    public void setIconImage(int source) {
        iv_icon.setImageResource(source);
    }
}
