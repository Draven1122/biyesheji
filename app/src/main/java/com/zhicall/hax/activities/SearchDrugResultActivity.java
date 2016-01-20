package com.zhicall.hax.activities;

import android.os.Bundle;
import butterknife.Bind;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;
import com.zhicall.hax.bean.Medicine;
import com.zhicall.hax.net.Data;
import com.zhicall.hax.net.IMedicalService;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xingchen on 2016/1/20.
 * Email:huangjinxin@zhicall.cn
 */
public class SearchDrugResultActivity extends BaseActivity {
  @Bind(R.id.lstv_search_result) PullToRefreshListView mPullToRefreshListView;
  private List<Medicine> mMedicineList = new ArrayList<>();
  private int currentPage = 1;
  private final int PAGE_SIZE = 15;
  private String keyword = "";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_drug_result);
    initActionbar(true, false, "SearchResult");
    initData();
  }

  @Override public void initData() {
    mMedicineList = (List<Medicine>) getIntent().getExtras().getSerializable("medicineList");
  }

  private void getData() {
    currentPage++;
    Subscription subscription = Data.tianGouService(IMedicalService.class)
        .searchDrug("drug", keyword, currentPage, PAGE_SIZE)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .finallyDo(() -> mPullToRefreshListView.onRefreshComplete())
        .map(result -> result.getTngou())
        .subscribe(list->)
  }
}
