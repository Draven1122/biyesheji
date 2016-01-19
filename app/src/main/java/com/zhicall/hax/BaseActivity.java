package com.zhicall.hax;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import rx.Observable;
import rx.Subscription;

/**
 * Activity通用父类
 * Created by Xingchen on 2016/1/7.
 * Email:huangjinxin@zhicall.cn
 * qq:328674568
 */
public abstract class BaseActivity extends AppCompatActivity {
  public ProgressDialog mProgressDialog = null;
  public ActionBar mActionBar = null;
  public TextView mTitleTextView = null;
  public ImageView mBackImageView = null;
  public RelativeLayout mRightContainer;
  protected final Set<Subscription> mSubscriptionSet =
      Collections.newSetFromMap(new WeakHashMap<>());

  public void showProgressdialog(String message) {
    if (mProgressDialog == null) mProgressDialog = new ProgressDialog(BaseActivity.this);
    mProgressDialog.setMessage(message);
    mProgressDialog.show();
  }

  public abstract void initData();

  public void showProgressdialog() {
    showProgressdialog("");
  }

  public void dissmissProgressDialog() {
    if (mProgressDialog != null) mProgressDialog.dismiss();
  }

  public void initActionbar(boolean left, boolean right, String title) {
    android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
    if (supportActionBar != null) {
      supportActionBar.setCustomView(R.layout.layout_actionar);
      supportActionBar.setDisplayShowCustomEnabled(true);
    }
    View customView = null;
    if (supportActionBar != null) {
      customView = supportActionBar.getCustomView();
    }

    //在support-v7中，自定义的custom actionbar的宽度不会铺满屏幕，得做如下处理.
    Toolbar parent = (Toolbar) (customView != null ? customView.getParent() : null);
    if (parent != null) {
      parent.setContentInsetsAbsolute(0, 0);
      ViewGroup.LayoutParams lp = parent.getLayoutParams();
      lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
      parent.setLayoutParams(lp);
    }
    mRightContainer =
        (RelativeLayout)getSupportActionBar().getCustomView().findViewById(R.id.rlayout_right_container);
    mRightContainer.setVisibility(right ? View.VISIBLE : View.GONE);
    ImageView mBackImageView=
        (ImageView) getSupportActionBar().getCustomView().findViewById(R.id.img_back);
    mBackImageView.setOnClickListener((view)->onBackPressed());


    ButterKnife.bind(this);
  }

  public void onBackPressed() {
    this.finish();
  }

  public void initActionbar(boolean left, boolean right) {
    initActionbar(left, right, "");
  }

  public void initActionbar() {
    initActionbar(true, false, "");
  }

  @Override protected void onDestroy() {
    ButterKnife.unbind(this);
    if (mProgressDialog != null) {
      mProgressDialog.dismiss();
      mProgressDialog.cancel();
    }
    mProgressDialog = null;
    Observable.from(mSubscriptionSet)
        .filter(subscription -> subscription != null && !subscription.isUnsubscribed())
        .subscribe(Subscription::unsubscribe);
    super.onDestroy();
  }
}
