package com.zhicall.hax;

import android.app.Application;
import android.content.Context;

/**
 * Created by Xingchen on 2015/12/21.
 * Email:huangjinxin@zhicall.cn
 */
public class MyApplication extends Application {
  private static Context mContext;

  @Override public void onCreate() {
    super.onCreate();
    mContext = this;
  }

  public static Context getContext() {
    return mContext;
  }
}
