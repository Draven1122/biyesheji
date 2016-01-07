package com.zhicall.hax;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

/**
 * Created by Xingchen on 2016/1/7.
 * Email:huangjinxin@zhicall.cn
 */
public abstract class BaseActivity extends Activity {
  public ProgressDialog mProgressDialog = null;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    init();
  }

  /**
   * 在setContentView之前做一些初始化数据的操作
   */
  public abstract void init();

  public void showProgressdialog(String message) {
    if (mProgressDialog == null) mProgressDialog = new ProgressDialog(BaseActivity.this);
    mProgressDialog.setMessage(message);
    mProgressDialog.show();
  }

  public void showProgressdialog() {
    showProgressdialog("");
  }

  public void dissmissProgressDialog() {
    if (mProgressDialog != null) mProgressDialog.dismiss();
  }
}
