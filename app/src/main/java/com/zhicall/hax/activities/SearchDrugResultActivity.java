package com.zhicall.hax.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.DravenException;
import com.zhicall.hax.R;
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
 * Created by Xingchen on 2016/1/20.
 * Email:huangjinxin@zhicall.cn
 */
public class SearchDrugResultActivity extends BaseActivity {
  @Bind(R.id.lstv_search_result) PullToRefreshListView mPullToRefreshListView;
  private List<Medicine> mMedicineList = new ArrayList<>();
  private int currentPage = 1;
  private final int PAGE_SIZE = 15;
  private String keyword = "";
  private MedicineListAdapter2 mMedicineListAdapter2;
  private boolean haxNextPage = true;
  private Handler mHandler;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_drug_result);
    initActionbar(true, false, "SearchResult");
    initData();
  }

  @Override public void initData() {
    mHandler = new Handler() {
      @Override public void handleMessage(Message msg) {
        mPullToRefreshListView.onRefreshComplete();
        ToastManager.showToast("OK");
        super.handleMessage(msg);
      }
    };
    keyword = getIntent().getExtras().getString("keyword");
    mMedicineListAdapter2 =
        new MedicineListAdapter2(this, mMedicineList, R.layout.layout_medicine_list_item);
    mPullToRefreshListView.setOnRefreshListener(
        new PullToRefreshBase.OnRefreshListener2<ListView>() {
          @Override public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

          }

          @Override public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            getData();
          }
        });
    mPullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Medicine medicine = mMedicineList.get(position - 1);
        Bundle bundle = new Bundle();
        bundle.putSerializable("medicine", medicine);
        Intent intent = new Intent(SearchDrugResultActivity.this, MedicineDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
      }
    });
    mPullToRefreshListView.setAdapter(mMedicineListAdapter2);
    mMedicineList.addAll((List<Medicine>) getIntent().getExtras().getSerializable("medicineList"));
    mMedicineListAdapter2.notifyDataSetChanged();
  }

  private void getData() {
    currentPage++;
    Subscription subscription = Data.tianGouService(IMedicalService.class)
        .searchDrug("drug", keyword, currentPage, PAGE_SIZE)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .map(result -> result.getTngou())
        .doOnSubscribe(() -> {
          if (!haxNextPage) {
            mHandler.sendEmptyMessageDelayed(1111, 5000);
            throw new DravenException("no more data...");
          }
        })
        .finallyDo(() -> mPullToRefreshListView.onRefreshComplete())
        .subscribe(list -> {
          if (list.size() < 15) haxNextPage = false;
          mMedicineList.addAll(list);
          mMedicineListAdapter2.notifyDataSetChanged();
        }, Data.errorHanlder());
    mSubscriptionSet.add(subscription);
  }

  public class MedicineListAdapter2 extends CommonAdapter<Medicine> {

    public MedicineListAdapter2(Context context, List<Medicine> list, int resID) {
      super(context, list, resID);
    }

    @Override public void initView(Medicine medicine, int position, CommonViewHolder viewHolder) {
      ImageView icon = viewHolder.getView(R.id.img_icon);
      TextView medicineName = viewHolder.getView(R.id.tv_medicine_name);
      TextView medicineDesc = viewHolder.getView(R.id.tv_medicine_desc);
      medicineName.setText(medicine.getName());
      medicineDesc.setText(medicine.getDescription());
      String url = medicine.getImg();
      Picasso.with(SearchDrugResultActivity.this).load(url).into(icon);
    }
  }
}
