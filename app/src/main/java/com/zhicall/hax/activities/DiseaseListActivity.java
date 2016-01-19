package com.zhicall.hax.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;
import com.zhicall.hax.bean.Body;
import com.zhicall.hax.bean.Disease;
import com.zhicall.hax.common.CommonAdapter;
import com.zhicall.hax.common.CommonViewHolder;
import com.zhicall.hax.net.Data;
import com.zhicall.hax.net.IMedicalService;
import java.util.ArrayList;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Xingchen on 2016/1/19.
 * Email:huangjinxin@zhicall.cn
 */
public class DiseaseListActivity extends BaseActivity {
  private int mCurrentPage = 1;
  private final int PAGE_SIZE = 20;
  private Body mBody;
  private List<Disease> mDiseaseList = new ArrayList<>();
  private DiseaseListAdapter mDiseaseListAdapter;
  @Bind(R.id.lstv_disease) PullToRefreshListView mPullToRefreshListView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_disease_list);
    initActionbar(true, false, "疾病列表");
    initData();
  }

  @Override public void initData() {
    mDiseaseListAdapter =
        new DiseaseListAdapter(this, mDiseaseList, R.layout.layout_disease_list_item);
    mPullToRefreshListView.setAdapter(mDiseaseListAdapter);
    mBody = (Body) getIntent().getExtras().getSerializable("body");
    Data.tianGouService(IMedicalService.class)
        .diseaseByBody(mBody.getId(), mCurrentPage, PAGE_SIZE)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(AndroidSchedulers.mainThread())
        .map(resulet -> resulet.getList())
        .doOnSubscribe(() -> showProgressdialog("Loading..."))
        .finallyDo(this::dissmissProgressDialog)
        .subscribe(list -> {
          mDiseaseList.addAll(list);
          mDiseaseListAdapter.notifyDataSetChanged();
        }, Data.errorHanlder());
  }

  public class DiseaseListAdapter extends CommonAdapter<Disease> {

    public DiseaseListAdapter(Context context, List<Disease> list, int resID) {
      super(context, list, resID);
    }

    @Override public void initView(Disease disease, int position, CommonViewHolder viewHolder) {
      ImageView icon = viewHolder.getView(R.id.img_icon);
      TextView mDiseaseName = viewHolder.getView(R.id.tv_disease_name);
      TextView mDiseaseDesc = viewHolder.getView(R.id.tv_disease_desc);
      mDiseaseName.setText(mDiseaseList.get(position).getName());
      mDiseaseDesc.setText(mDiseaseList.get(position).getDescription());
      String url =
          getResources().getString(R.string.tiangou_img_url_prefix) + mDiseaseList.get(position)
              .getImg();
      Picasso.with(DiseaseListActivity.this).load(url).into(icon);
    }
  }
}
