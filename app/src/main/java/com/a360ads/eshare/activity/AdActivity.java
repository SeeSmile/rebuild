package com.a360ads.eshare.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import com.a360ads.eshare.R;
import com.a360ads.eshare.base.BaseActivity;

/**
 * 说明：广告界面
 * Created by FuPei
 * on 2016/1/19 at 10:49
 */
public class AdActivity extends BaseActivity {
    
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_ad);
    }
}
