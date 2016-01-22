package com.a360ads.eshare.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import com.a360ads.eshare.EshareApplication;
import com.a360ads.eshare.R;
import com.a360ads.eshare.dialogs.EloadDialog;
import com.a360ads.eshare.utils.EwebUtil;
import com.a360ads.eshare.views.ToolbarView;

import butterknife.ButterKnife;

/**
 * 说明：基础活动类
 * Created by FuPei
 * on 2016/1/19 at 10:47
 */
public class BaseActivity extends AppCompatActivity {

    private final String TAG_DIALOG = "diaog";
    private EloadDialog mDialog;
    private boolean isDialogShowing;
    private Toolbar mToolbar;
    private ToolbarView mToolView;

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

    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolView = (ToolbarView) mToolbar.findViewById(R.id.ly_toolbar);
    }
}
