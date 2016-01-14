package com.zhicall.hax.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;
import com.zhicall.hax.bean.MedicalCategory;
import com.zhicall.hax.bean.Medicine;
import com.zhicall.hax.common.CommonAdapter;
import com.zhicall.hax.common.CommonViewHolder;
import com.zhicall.hax.net.Data;
import com.zhicall.hax.net.IMedicalService;
import com.zhicall.hax.utils.ToastManager;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xingchen on 2016/1/14.
 * Email:huangjinxin@zhicall.cn
 */
public class MedicineListActivity extends BaseActivity {
  private boolean haxNextPage = true;
  private final int PAGE_SIZE = 30;
  private int currentPage = 1;
  private int MEDICINE_CATEGORY_ID = 0;
  @Bind(R.id.lstv_medicine) PullToRefreshListView mPullToRefreshListView;
  private List<Medicine> mMedicineList = new ArrayList<>();
  private MedicalCategory mMedicalCategory;
  private MedicineListAdapter mMedicineListAdapter = null;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_medicine_list);
    initActionbar(true, false, "MedicineList");
    initView();
  }

  @Override public void initView() {
    mMedicalCategory =
        (MedicalCategory) getIntent().getExtras().getSerializable("medicineCategory");
    mMedicineListAdapter =
        new MedicineListAdapter(this, mMedicineList, R.layout.layout_medicine_list_item);
    mPullToRefreshListView.setAdapter(mMedicineListAdapter);
    mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
    mPullToRefreshListView.setOnRefreshListener(
        new PullToRefreshBase.OnRefreshListener2<ListView>() {
          @Override public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
            getData(true);
          }

          @Override public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            if (!haxNextPage) {
              ToastManager.showToast("没有更多了...");
              return;
            }
            getData(false);
          }
        });
    if (mMedicalCategory == null) {
      TextView label = new TextView(this);
      label.setText("暂无内容");
      mPullToRefreshListView.setEmptyView(mPullToRefreshListView);
    } else {
      MEDICINE_CATEGORY_ID = mMedicalCategory.getId();
      Data.tianGouService(IMedicalService.class)
          .medicineList(MEDICINE_CATEGORY_ID, currentPage, PAGE_SIZE)
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .map(reslute -> reslute.getTngou())
          .doOnSubscribe(() -> showProgressdialog("正在获取药品列表..."))
          .finallyDo(() -> {
            dissmissProgressDialog();
            mPullToRefreshListView.onRefreshComplete();
          })
          .subscribe(list -> {
            if (false) {
              mMedicineList.clear();
              mMedicineList.addAll(list);
            } else {
              mMedicineList.addAll(list);
            }
            mMedicineListAdapter.notifyDataSetChanged();
          });
    }
  }

  public void getData(boolean isRefresh) {
    if (isRefresh) {
      currentPage = 1;
    } else {
      currentPage++;
    }
    Subscription subscription = Data.tianGouService(IMedicalService.class)
        .medicineList(MEDICINE_CATEGORY_ID, currentPage, PAGE_SIZE)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .map(reslute -> reslute.getTngou())
        .finallyDo(() -> {
          dissmissProgressDialog();
          mPullToRefreshListView.onRefreshComplete();
        })
        .subscribe(list -> {
          if (list.size() < PAGE_SIZE) haxNextPage = false;
          if (isRefresh) {
            ToastManager.showToast("刷新成功!");
            mMedicineList.clear();
            mMedicineList.addAll(list);
          } else {
            mMedicineList.addAll(list);
          }
          mMedicineListAdapter.notifyDataSetChanged();
        });
    mSubscriptionSet.add(subscription);
  }

  public class MedicineListAdapter extends CommonAdapter<Medicine> {

    public MedicineListAdapter(Context context, List<Medicine> list, int resID) {
      super(context, list, resID);
    }

    @Override public void initView(Medicine medicine, int position, CommonViewHolder viewHolder) {
      ImageView icon = viewHolder.getView(R.id.img_icon);
      TextView medicineName = viewHolder.getView(R.id.tv_medicine_name);
      TextView medicineDesc = viewHolder.getView(R.id.tv_medicine_desc);
      medicineName.setText(medicine.getName());
      medicineDesc.setText(medicine.getDescription());
      String url = "http://tnfs.tngou.net/img" + medicine.getImg();
      Picasso.with(MedicineListActivity.this).load(url).into(icon);
    }
  }
}
