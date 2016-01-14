package com.zhicall.hax.utils;

import android.util.Log;

/**
 * Created by Xingchen on 2016/1/14.
 * Email:huangjinxin@zhicall.cn
 */
public class LogManager {
  public static void showLog(String str) {
    Log.i("draven", str);
  }

  public static void showLog(int i) {
    showLog(i + "");
  }
}
