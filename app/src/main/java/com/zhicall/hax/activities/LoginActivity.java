package com.zhicall.hax.activities;

import android.os.Bundle;
import android.view.View;
import butterknife.OnClick;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;
import com.zhicall.hax.net.Data;
import com.zhicall.hax.net.IMedicalService;
import com.zhicall.hax.utils.ToastManager;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xingchen on 2016/1/8.
 * Email:huangjinxin@zhicall.cn
 * qq:328674568
 */
public class LoginActivity extends BaseActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    initActionbar(true, false, "Login");
  }

  @OnClick(R.id.btn_test) public void onTestButtonClicked(View view) {
    Subscription subscription = Data.service(IMedicalService.class)
        .category()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(this::showProgressdialog).finallyDo(this::dissmissProgressDialog)
        .subscribe(result -> ToastManager.showToast(result.getStatus()+""),
            Data.errorHanlder());
    mSubscriptionSet.add(subscription);
  }

  @Override public void initView() {

  }
}
