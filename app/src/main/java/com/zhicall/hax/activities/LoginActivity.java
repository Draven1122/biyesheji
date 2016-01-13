package com.zhicall.hax.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;

import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;
import com.zhicall.hax.bmob.bean.User;
import com.zhicall.hax.net.Data;
import com.zhicall.hax.net.IMedicalService;
import com.zhicall.hax.utils.ToastManager;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ×¢²áÒ³Ãæ
 * Created by Xingchen on 2016/1/8.
 * Email:huangjinxin@zhicall.cn
 * qq:328674568
 */
public class LoginActivity extends BaseActivity {
    @Bind(R.id.et_user_name)
    TextView mUserNameTextViews;
    @Bind(R.id.et_password)
    TextView mPasswordTextView;
    public User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initActionbar(true, false, "×¢²á");
    }

    @OnClick(R.id.btn_login)
    public void onLoginButtonClicked(View view) {
        ToastManager.showToast("login...");
    }

    @Override
    public void initView() {

    }
}
