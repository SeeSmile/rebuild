package com.a360ads.eshare.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.a360ads.eshare.R;
import com.a360ads.eshare.base.BaseActivity;
import com.a360ads.eshare.fragments.FindFragment;
import com.a360ads.eshare.fragments.FirstFragment;
import com.a360ads.eshare.fragments.MoneyFragment;
import com.a360ads.eshare.utils.Elog;

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

    @InjectView(R.id.rg_main)
    public RadioGroup rg_main;

    @InjectView(R.id.rb_first)
    public RadioButton rb_first;
    @InjectView(R.id.rb_find)
    public RadioButton rb_find;
    @InjectView(R.id.rb_money)
    public RadioButton rb_money;

    private FragmentTransaction mTransaction;

    @InjectView(R.id.ly_main)
    RelativeLayout ly_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
    }

    private void initData() {
        mTransaction = getSupportFragmentManager().beginTransaction();
        frg_first = new FirstFragment();
        frg_find = new FindFragment();
        frg_money = new MoneyFragment();
//        mTransaction.add(R.id.ly_main, frg_first, "first").show(frg_first);
//        mTransaction.add(R.id.ly_main, frg_find, "find").hide(frg_find);
//        mTransaction.add(R.id.ly_main, frg_money, "money").hide(frg_money);
        mTransaction.add(R.id.ly_main, frg_first);
        mTransaction.commit();
    }

    private void initListener() {
        rb_find.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Elog.i("find");
                    mTransaction.replace(R.id.ly_main, frg_find);
                    mTransaction.commit();
//                    mTransaction.show(frg_find);
//                    mTransaction.hide(frg_first);
//                    mTransaction.hide(frg_money);
                }
            }
        });

        rb_first.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Elog.i("first");
                    mTransaction.replace(R.id.ly_main, frg_first);
                    mTransaction.commit();
//                    mTransaction.hide(frg_find);
//                    mTransaction.show(frg_first);
//                    mTransaction.hide(frg_money);
                }
            }
        });

        rb_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_money.isChecked()) {
                    Elog.i("money");
                    mTransaction.replace(R.id.ly_main, frg_money);
                    mTransaction.commit();
//                    mTransaction.hide(frg_find);
//                    mTransaction.hide(frg_first);
//                    mTransaction.show(frg_money);
                }
            }
        });
    }
}
