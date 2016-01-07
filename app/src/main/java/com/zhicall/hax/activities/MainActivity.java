package com.zhicall.hax.activities;

import android.os.Bundle;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    ButterKnife.bind(this);
  }

  @Override public void init() {

  }

  @OnClick(R.id.btn_show) public void onShowClicked(View view) {
    this.showProgressdialog("Loading...");
  }

  @OnClick(R.id.btn_dismiss) public void onDismissClicked(View view) {
    this.dissmissProgressDialog();
  }
}
