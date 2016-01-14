package com.zhicall.hax.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.zhicall.hax.R;

/**
 * Created by Xingchen on 2016/1/11.
 * Email:huangjinxin@zhicall.cn
 */
public class HomeFragment extends Fragment {
  private boolean isFresh=true;
  private View view;
  public HomeFragment(){
    super();
  }
  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (view == null) view = inflater.inflate(R.layout.fragment_home, null);
    ButterKnife.bind(this,view);
    if (isFresh){

    }
    return view;
  }
}
