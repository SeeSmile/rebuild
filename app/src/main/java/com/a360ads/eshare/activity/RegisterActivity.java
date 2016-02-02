package com.a360ads.eshare.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.a360ads.eshare.R;
import com.a360ads.eshare.base.CodeActivity;

import butterknife.InjectView;

/**
 * 说明：注册界面
 * Created by FuPei
 * on 2016/1/22 at 17:23
 */
public class RegisterActivity extends CodeActivity {

    @InjectView(R.id.et_phone)
    public EditText et_phone;
    @InjectView(R.id.et_code)
    public EditText et_code;
    @InjectView(R.id.tv_getcode)
    public TextView tv_getcode;
    @InjectView(R.id.et_password)
    public EditText et_password;
    @InjectView(R.id.btn_submit)
    public Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public TYPE getType() {
        return TYPE.REGISTER;
    }

    @Override
    public EditText getEtPhone() {
        return et_phone;
    }

    @Override
    public EditText getEtcode() {
        return et_code;
    }

    @Override
    public View getVGetcode() {
        return tv_getcode;
    }
}
