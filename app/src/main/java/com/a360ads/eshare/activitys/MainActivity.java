package com.a360ads.eshare.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.a360ads.eshare.EshareApplication;
import com.a360ads.eshare.R;
import com.a360ads.eshare.base.BaseActivity;
import com.a360ads.eshare.fragments.FindFragment;
import com.a360ads.eshare.fragments.FirstFragment;
import com.a360ads.eshare.fragments.MoneyFragment;
import com.a360ads.eshare.utils.Elog;

import java.security.Signature;

import butterknife.InjectView;

/**
 * 说明：主界面
 * Created by FuPei
 * on 2016/1/23 at 14:10
 */
public class MainActivity extends BaseActivity {

    private FirstFragment frg_first;
    private FindFragment frg_find;
    private MoneyFragment frg_money;

    @InjectView(R.id.rb_first)
    public RadioButton rb_first;
    @InjectView(R.id.rb_find)
    public RadioButton rb_find;
    @InjectView(R.id.rb_money)
    public RadioButton rb_money;
    /**
     * 当前按back键是否可以退出
     */
    private boolean canExit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        setTitleStyle(STYLE_BOTH_TI);
        setTitle("互动");
        setIconImage(R.mipmap.icon_sign);
    }

    private void initData() {
        frg_first = new FirstFragment();
        frg_find = new FindFragment();
        frg_money = new MoneyFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ly_main, frg_first).commit();
    }

    private void initListener() {
        rb_find.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setTitle("发现");
                    setTitleStyle(STYLE_ONLY_TITLE);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.ly_main, frg_find).commit();
                }
            }
        });

        rb_first.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setTitle("互动");
                    setTitleStyle(STYLE_BOTH_TI);
                    setIconImage(R.mipmap.icon_sign);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.ly_main, frg_first).commit();

                }
            }
        });

        rb_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rb_money.isChecked()) {
                    setTitleStyle(STYLE_BOTH_TI);
                    setTitle("财富");
                    setIconImage(R.mipmap.icon_message);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.ly_main, frg_money).commit();
                }
            }
        });

        setOnIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rb_first.isChecked()) {
                    goActivity(SignActivity.class);
                } else {
                    goActivity(MessageActivity.class);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(canExit) {
            EshareApplication.getInstance().exitApp();
        } else {
            prepareToExit();
        }
    }

    /**
     * 准备退出
     */
    private void prepareToExit() {
        toast("再按一次退出");
        new Thread(new Runnable() {
            @Override
            public void run() {
                canExit = true;
                try {
                    Thread.sleep(2 * 1000);
                    canExit = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
