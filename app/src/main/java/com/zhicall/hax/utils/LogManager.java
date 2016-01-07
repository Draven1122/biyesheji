package com.zhicall.hax.utils;

import android.util.Log;

/**
 * Created by Xingchen on 2015/12/21.
 * Email:huangjinxin@zhicall.cn
 * phone:157571770**
 */
public class LogManager {
  public static void showLog(String str) {
    Log.i("Draven", str);
  }

  public static void showLog(int arg0) {
    showLog(arg0 + "");
  }
}
