package com.zhicall.hax.utils;

import android.widget.Toast;
import com.zhicall.hax.MyApplication;

/**
 * Created by Xingchen on 2015/12/21.
 * Email:huangjinxin@zhicall.cn
 */
public class ToastManager {
  public static void showToast(String str) {
    Toast.makeText(MyApplication.getContext(), str, Toast.LENGTH_LONG).show();
  }

  public static void showToast(int arg0) {
    showToast(arg0 + "");
  }
}
