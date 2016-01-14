package com.zhicall.hax.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import butterknife.Bind;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;
import com.zhicall.hax.bean.MedicalCategory;
import com.zhicall.hax.net.Data;
import com.zhicall.hax.net.IMedicalService;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Xingchen on 2016/1/14.
 * Email:huangjinxin@zhicall.cn
 */
public class MedicalCategoryActivity extends BaseActivity {
  @Bind(R.id.lstv_medical_category) PullToRefreshListView mPullToRefreshListView;
  ArrayAdapter<String> mArrayAdapter;
  List<String> mMedicalCategoryList=new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_medical_category);
    initActionbar(true, false, "MedicalCategory");
    init();
  }

  public void init() {

      mArrayAdapter =
          new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mMedicalCategoryList);
      mPullToRefreshListView.setAdapter(mArrayAdapter);

    Subscription subscription = Data.tianGouService(IMedicalService.class)
        .category()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .map(result -> result.getTngou())
        .flatMap(new Func1<List<MedicalCategory>, Observable<MedicalCategory>>() {
          @Override
          public Observable<MedicalCategory> call(List<MedicalCategory> medicalCategories) {
            return Observable.from(medicalCategories);
          }
        })
        .map(new Func1<MedicalCategory, String>() {
          @Override public String call(MedicalCategory medicalCategory) {
            return medicalCategory.getTitle();
          }
        })
        .toList()
        .doOnSubscribe(() -> showProgressdialog("���ڻ�ȡҩƷ������Ϣ..."))
        .finallyDo(() -> dissmissProgressDialog())
        .subscribe(list -> {
          mMedicalCategoryList.addAll(list);
          mArrayAdapter.notifyDataSetChanged();
        }, Data.errorHanlder());
    mSubscriptionSet.add(subscription);
  }

  @Override public void initView() {

  }
}