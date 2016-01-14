package com.zhicall.hax.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;
import com.zhicall.hax.bean.MedicalCategory;
import com.zhicall.hax.bean.Medicine;
import com.zhicall.hax.common.CommonAdapter;
import com.zhicall.hax.common.CommonViewHolder;
import java.util.List;

/**
 * Created by Xingchen on 2016/1/14.
 * Email:huangjinxin@zhicall.cn
 */
public class MedicineListActivity extends BaseActivity {
  private final  int PAGE_SIZE=30;
  private int  currentPage=1;
  private int MEDICINE_CATEGORY_ID=0;
  @Bind(R.id.lstv_medicine) PullToRefreshListView mPullToRefreshListView;
  private MedicalCategory mMedicalCategory;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_medicine_list);
    initActionbar(true, false, "MedicineList");
  }

  @Override public void initView() {
      mMedicalCategory=
          (MedicalCategory) getIntent().getExtras().getSerializable("medicineCategory");
    if (mMedicalCategory==null){
      TextView label=new TextView(this);
      label.setText("暂无数据");
      mPullToRefreshListView.setEmptyView(mPullToRefreshListView);
    }else {
      MEDICINE_CATEGORY_ID=mMedicalCategory.getId();
    }
  }
  public void getData(boolean isRefresh){
    if (isRefresh)
      currentPage=1;
    else
      currentPage++;
  }
  public class MedicineListAdapter extends CommonAdapter<Medicine>{

    public MedicineListAdapter(Context context, List<Medicine> list, int resID) {
      super(context, list, resID);
    }

    @Override public void initView(Medicine medicine, int position, CommonViewHolder viewHolder) {
          
    }
  }
}
