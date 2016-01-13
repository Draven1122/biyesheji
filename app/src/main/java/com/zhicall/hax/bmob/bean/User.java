package com.zhicall.hax.bmob.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by draven on 2016/1/13.
 * E-mail:draven1122@163.com
 * Bmob后台的User表
 */
public class User extends BmobUser{
    private boolean isVip;

    public boolean isVip() {
        return isVip;
    }
}
