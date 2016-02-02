package com.a360ads.eshare.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.a360ads.eshare.R;
import com.a360ads.eshare.base.BaseActivity;
import com.a360ads.eshare.fragments.GuideFragment;
import com.a360ads.eshare.views.GuideProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * 说明：向导界面
 * Created by FuPei
 * on 2016/1/19 at 10:50
 */
public class GuideActivity extends BaseActivity {

    /**
     * 引导页图片资源
     */
    private final int[] IMAGE_GUIDE = {R.mipmap.guide01, R.mipmap.guide02, R.mipmap.guide03};
    /**
     * 背景显示page
     */
    @InjectView(R.id.guide_vp)
    public ViewPager mPage;
    /**
     * 底部导航进度条
     */
    @InjectView(R.id.guide_gpv)
    public GuideProgressView mGpv;

    private FragmentPagerAdapter mAdapter;
    /**
     * 导航界面列表
     */
    private List<GuideFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        setContentView(R.layout.activity_guide);
    }

    private void initData() {
        fragments = new ArrayList<>();
        //初始化页面数据
        for (int source : IMAGE_GUIDE) {
            GuideFragment fragment = new GuideFragment();
            fragment.setImage(source);
            fragments.add(fragment);
        }
        //设置最后一页显示登陆按钮
        int size = fragments.size();
        fragments.get(size - 1).setCanLogin(true);
        mGpv.setAllPage(size);
        //初始化适配
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        };
        //设置界面UI
        mPage.setAdapter(mAdapter);
        mPage.setCurrentItem(0);
    }

    private void initListener() {
        mPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //改变底部进度条UI
                mGpv.setCurrentIndex(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

