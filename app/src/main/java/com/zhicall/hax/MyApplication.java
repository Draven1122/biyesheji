package com.zhicall.hax;

import android.app.Application;
import android.content.Context;

import com.zhicall.hax.bmob.bean.User;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

/**
 * Created by Xingchen on 2015/12/21.
 * Email:huangjinxin@zhicall.cn
 */
public class MyApplication extends Application {
  private static Context mContext;
  public static User user;
  public static User getUser() {
    return user;
  }

  public static void setUser(User user) {
    MyApplication.user = user;
  }

  @Override public void onCreate() {
    super.onCreate();
    mContext = this;
    //初始化bmob
    Bmob.initialize(this, getResources().getString(R.string.bmob_application_key ));

    BmobInstallation.getCurrentInstallation(this).save();
    // 启动推送服务
    BmobPush.startWork(this, getResources().getString(R.string.bmob_application_key ));

  }

  public static Context getContext() {
    return mContext;
  }
  public static boolean isLogin(){
    return user!=null;
  }

}
