package com.zhicall.hax.bmob.receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.zhicall.hax.event.BmobPushEvent;
import com.zhicall.hax.utils.ToastManager;

import de.greenrobot.event.EventBus;

/**
 * Created by draven on 2016/1/13.
 * E-mail:draven1122@163.com
 */
public class BmobPushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("info","收到广播");
        String msg = intent.getStringExtra("msg");
        EventBus.getDefault().post(new BmobPushEvent(msg));
    }
}
