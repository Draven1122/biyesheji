package com.zhicall.hax.utils;

import com.zhicall.hax.MyApplication;
import com.zhicall.hax.R;

/**
 * Created by Xingchen on 2016/1/20.
 * Email:huangjinxin@zhicall.cn
 */
public class TianGouUtils {
  public static String getImageUrl(String url) {
    String pre =
        MyApplication.getContext().getResources().getString(R.string.tiangou_img_url_prefix);
    return pre + url;
  }
}
