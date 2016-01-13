package com.zhicall.hax.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;

import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.MyApplication;
import com.zhicall.hax.R;
import com.zhicall.hax.bmob.bean.User;
import com.zhicall.hax.net.Data;
import com.zhicall.hax.net.IMedicalService;
import com.zhicall.hax.utils.ToastManager;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initActionbar(true, false, "µÇÂ½");
    }
    @OnClick(R.id.btn_exit)
    public void onExitButtonClicked(){
        onBackPressed();
    }
    @OnClick(R.id.btn_login)
    public void onLoginButtonClicked(View view) {
        this.showProgressdialog("Login...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        String userName = mUserNameTextViews.getText().toString().trim();
        String password = mPasswordTextView.getText().toString().toString();
        if (isNotBlank(userName)&&isNotBlank(password)){
            BmobUser.loginByAccount(this, userName, password, new LogInListener<User>() {
                @Override
                public void done(User user, BmobException e) {
                    if (user!=null){
                        dissmissProgressDialog();
                        MyApplication.setUser(user);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        LoginActivity.this.finish();
                    }else {
                        dissmissProgressDialog();
                        ToastManager.showToast("ÓÃ»§Ãû»òÃÜÂë´íÎó");
                    }
                }
            });
        }

    }

    @Override
    public void initData() {

    }

    public boolean isNotBlank(String str) {
        return str.trim().length() > 0;
    }

    @Override
    public void onBackPressed() {
        AlertDialog dialog = new AlertDialog.Builder(this).setMessage("Are u sure to exit App?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        LoginActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
