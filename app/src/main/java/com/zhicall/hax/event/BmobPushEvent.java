package com.zhicall.hax.event;

/**
 * Created by draven on 2016/1/13.
 * E-mail:draven1122@163.com
 */
public class BmobPushEvent {
    String msg;

    public BmobPushEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
