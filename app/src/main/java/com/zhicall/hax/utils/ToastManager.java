package com.zhicall.hax.utils;

import android.widget.Toast;
import com.zhicall.hax.MyApplication;

/**
 * Created by Xingchen on 2016/1/11.
 * Email:huangjinxin@zhicall.cn
 */
public class ToastManager {
  public static void showToast(String msg) {
    Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_LONG).show();
  }

  public static void showToast(int i) {
    showToast(i + "");
  }
}
