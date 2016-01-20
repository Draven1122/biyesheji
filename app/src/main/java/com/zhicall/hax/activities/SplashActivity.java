package com.zhicall.hax.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.zhicall.hax.R;


/**
 * Created by draven on 2016/1/13.
 * E-mail:draven1122@163.com
 */
public class SplashActivity extends Activity {
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                SplashActivity.this.finish();
            }
        };
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message msg=Message.obtain();
                mHandler.handleMessage(msg);
            }
        },3000);
    }
}
