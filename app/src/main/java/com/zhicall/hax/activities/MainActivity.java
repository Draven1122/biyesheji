package com.zhicall.hax.activities;

import android.os.Bundle;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;

/**
 * Created by Xingchen on 2016/1/7.
 * Email:huangjinxin@zhicall.cn
 */
public class MainActivity extends BaseActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initActionbar(true,false,"首页");
  }
}
