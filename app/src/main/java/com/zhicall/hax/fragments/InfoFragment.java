package com.zhicall.hax.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.zhicall.hax.R;
import com.zhicall.hax.net.Data;
import com.zhicall.hax.net.INewsService;
import com.zhicall.hax.utils.ToastManager;

/**
 * Created by Xingchen on 2016/1/11.
 * Email:huangjinxin@zhicall.cn
 */
public class InfoFragment extends Fragment {
  @Bind(R.id.btn_test) Button mTestButton;
  private View view;

  public InfoFragment() {
    super();
  }

  @OnClick(R.id.btn_test) public void onTestButtonClicked() {
    Data.service(INewsService.class)
        .getNewsCategory()
        .subscribe(reselut -> ToastManager.showToast(reselut.getStatus() + ""));
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (view == null) view = inflater.inflate(R.layout.fragment_info, null);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onDestroyView() {
    ButterKnife.unbind(this);
    super.onDestroyView();
  }
}
