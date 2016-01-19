package com.zhicall.hax.activities;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.push.BmobPush;
import cn.bmob.v3.BmobInstallation;
import de.greenrobot.event.EventBus;

import com.squareup.otto.Subscribe;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.MyApplication;
import com.zhicall.hax.R;
import com.zhicall.hax.bmob.bean.User;
import com.zhicall.hax.bmob.receivers.BmobPushReceiver;
import com.zhicall.hax.event.BmobPushEvent;
import com.zhicall.hax.fragments.HomeFragment;
import com.zhicall.hax.fragments.InfoFragment;
import com.zhicall.hax.fragments.SearchInfoFragment;
import com.zhicall.hax.utils.ToastManager;

/**
 * APP首页
 * Created by Xingchen on 2016/1/7.
 * Email:huangjinxin@zhicall.cn
 */
public class MainActivity extends BaseActivity {

  @Bind(R.id.rlayout_fragmengt_container) RelativeLayout mFragmentContainer;
  @Bind(R.id.btn_home) Button mHomeButton;
  private Fragment mHomeFragment;
  private Fragment mInfoFragment;
  private Fragment mSearchFragment;
  private Button mCurrentButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionbar(true, false, "Home");
        initData();
        User user = MyApplication.getUser();
        if (user.isVip())
            ToastManager.showToast("欢迎使用，你的使用权限是：Vip");
        else
            ToastManager.showToast("欢迎使用，你的使用权限是：普通用户");
        EventBus.getDefault().register(this);
    }

    @Override
    public void initData() {
        mCurrentButton = mHomeButton;
        mCurrentButton.setSelected(true);
        mCurrentButton.setClickable(false);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mHomeFragment = new HomeFragment();
        transaction.add(R.id.rlayout_fragmengt_container, mHomeFragment);
        transaction.commit();
    }
    @OnClick(R.id.btn_info)
    public void onInfoButtonClicked(View view) {
        mCurrentButton.setSelected(false);
        mCurrentButton.setClickable(true);
        mCurrentButton = (Button) view;
        mCurrentButton.setSelected(true);
        view.setClickable(false);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (mInfoFragment == null) mInfoFragment = new InfoFragment();
        transaction.replace(R.id.rlayout_fragmengt_container, mInfoFragment);
        transaction.commit();
    }

    @OnClick(R.id.btn_profle)
    public void onProfileButtonCliked() {
        ToastManager.showToast("功能建设中...");
        return;
    }

    @Override
    public void onBackPressed() {
        AlertDialog dialog = new AlertDialog.Builder(this).setMessage("确定退出App吗?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
    public void onEvent(BmobPushEvent event) {
        ToastManager.showToast("Activity收到");
        showNotification2("面朝大海", event.getMsg());
    }

    protected void showNotification2(String title, String content) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(MainActivity.this);
        PendingIntent contentIndent = PendingIntent.getActivity(MainActivity.this, 0, new Intent(MainActivity.this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIndent).setSmallIcon(R.mipmap.ic_launcher)//设置状态栏里面的图标（小图标） 　　　　　　　　　　　　　　　　　　　　.setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.i5))//下拉下拉列表里面的图标（大图标） 　　　　　　　.setTicker("this is bitch!") //设置状态栏的显示的信息
                .setWhen(System.currentTimeMillis())//设置时间发生时间
                .setAutoCancel(true)//设置可以清除
                .setContentTitle(title)//设置下拉列表里的标题
                .setContentText(content);//设置上下文内容
        Notification notification = builder.getNotification();
        //加i是为了显示多条Notification
        notificationManager.notify(1, notification);
    }

  @OnClick(R.id.btn_home) public void onHomeButtonCliked(View view) {
    mCurrentButton.setSelected(false);
    mCurrentButton.setClickable(true);
    mCurrentButton = (Button) view;
    mCurrentButton.setSelected(true);
    view.setClickable(false);
    FragmentManager fm = getFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();
    if (mHomeFragment == null) mHomeFragment = new HomeFragment();
    transaction.replace(R.id.rlayout_fragmengt_container, mHomeFragment);
    transaction.commit();
  }

  @OnClick(R.id.btn_query) public void onQueryButtonClicked(View view) {
    mCurrentButton.setSelected(false);
    mCurrentButton.setClickable(true);
    mCurrentButton = (Button) view;
    mCurrentButton.setSelected(true);
    view.setClickable(false);
    FragmentManager fm = getFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();
    if (mSearchFragment == null) mSearchFragment = new SearchInfoFragment();
    transaction.replace(R.id.rlayout_fragmengt_container, mSearchFragment);
    transaction.commit();
  }

}
