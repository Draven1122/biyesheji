package com.zhicall.hax.activities;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.OnClick;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;
import com.zhicall.hax.fragments.HomeFragment;
import com.zhicall.hax.fragments.InfoFragment;
import com.zhicall.hax.fragments.SearchInfoFragment;
import com.zhicall.hax.utils.ToastManager;

/**
 * APP棣栭〉
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

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initActionbar(true, false, "Home");
    initView();
  }

  @Override public void initView() {
    mCurrentButton = mHomeButton;
    mCurrentButton.setSelected(true);
    mCurrentButton.setClickable(false);
    FragmentManager fm = getFragmentManager();
    FragmentTransaction transaction = fm.beginTransaction();
    mHomeFragment = new HomeFragment();
    transaction.add(R.id.rlayout_fragmengt_container, mHomeFragment);
    transaction.commit();
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

  @OnClick(R.id.btn_info) public void onInfoButtonClicked(View view) {
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

  @OnClick(R.id.btn_profle) public void onProfileButtonCliked() {
    ToastManager.showToast("功能建设中...");
    return;
  }

  @Override public void onBackPressed() {
    AlertDialog dialog = new AlertDialog.Builder(this).setMessage("Are u sure to exit App?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {
            MainActivity.super.onBackPressed();
          }
        })
        .setNegativeButton("No", null)
        .show();
  }
}
