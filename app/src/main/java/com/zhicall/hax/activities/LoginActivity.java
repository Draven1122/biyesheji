package com.zhicall.hax.activities;

import android.os.Bundle;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;

/**
 * Created by Xingchen on 2016/1/8.
 * Email:huangjinxin@zhicall.cn
 * qq:328674568
 */
public class LoginActivity extends BaseActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    initActionbar(true,false,"登录");

  }
}
