package com.zhicall.hax;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
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
 * ActivityÕ®”√∏∏¿‡
 * Created by Xingchen on 2016/1/7.
 * Email:huangjinxin@zhicall.cn
 * qq:328674568
 */
public abstract class BaseActivity extends Activity {
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

  public abstract void initView();

  public void showProgressdialog() {
    showProgressdialog("");
  }

  public void dissmissProgressDialog() {
    if (mProgressDialog != null) mProgressDialog.dismiss();
  }

  public void initActionbar(boolean left, boolean right, String title) {

    mActionBar = getActionBar();
    mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
    mActionBar.setCustomView(R.layout.layout_actionar);
    mTitleTextView = (TextView) mActionBar.getCustomView().findViewById(R.id.tv_actionbar_title);
    mTitleTextView.setText(title);
    mBackImageView = (ImageView) mActionBar.getCustomView().findViewById(R.id.img_back);
    mBackImageView.setVisibility(left ? View.VISIBLE : View.GONE);
    mBackImageView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });
    mRightContainer =
        (RelativeLayout) mActionBar.getCustomView().findViewById(R.id.rlayout_right_container);
    mRightContainer.setVisibility(right ? View.VISIBLE : View.GONE);
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
