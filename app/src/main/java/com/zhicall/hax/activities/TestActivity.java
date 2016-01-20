package com.zhicall.hax.activities;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;
import com.zhicall.hax.net.Data;
import com.zhicall.hax.net.TestService;
import com.zhicall.hax.utils.ToastManager;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by draven on 2016/1/13.
 * E-mail:draven1122@163.com
 */
public class TestActivity extends BaseActivity {
    @Bind(R.id.tv_test)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initActionbar();
    }

    @OnClick(R.id.btn_test)
    public void onTestButtonClicked() {
        Data.testService(TestService.class).getTest("test").observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(result -> ToastManager.showToast(result.getId()), Data.errorHanlder());

    }

    @Override
    public void initData() {

    }
}
