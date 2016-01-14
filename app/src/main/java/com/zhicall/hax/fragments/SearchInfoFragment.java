package com.zhicall.hax.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.zhicall.hax.R;
import com.zhicall.hax.activities.MedicalCategoryActivity;
import com.zhicall.hax.bean.MedicalCategory;
import java.util.List;

/**
 * Created by Xingchen on 2016/1/14.
 * Email:huangjinxin@zhicall.cn
 */
public class SearchInfoFragment extends Fragment {
  private View view;
  private boolean isFresh = true;
  List<MedicalCategory> mMedicalCategoryList;

  public SearchInfoFragment() {
    super();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (view == null) view = inflater.inflate(R.layout.fragment_search_info, null);
    if (isFresh) {
      ButterKnife.bind(this, view);
    }
    return view;
  }

  @OnClick(R.id.rlayout_drug_database) public void onDrugDataBaseRelativelayoutCliked() {
    Intent intent = new Intent(getActivity(), MedicalCategoryActivity.class);
    startActivity(intent);
  }
}
