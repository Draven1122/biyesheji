package com.zhicall.hax.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.zhicall.hax.R;
import com.zhicall.hax.bean.Bean;
import com.zhicall.hax.common.CommonAdapter;
import com.zhicall.hax.common.CommonViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xingchen on 2015/12/14.
 * Email:huangjinxin@zhicall.cn
 */
public class PullToLoadActivity extends Activity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pulltoload);
    ButterKnife.bind(this);
    List<Bean> data = new ArrayList<Bean>();
    for (int i = 0; i < 10; i++) {
      Bean bean = new Bean("2015-12-01", i + "");
      data.add(bean);
    }
  }

  public class PullToLoadListViewAdapter extends CommonAdapter<Bean> {

    public PullToLoadListViewAdapter(Context context, List<Bean> list, int resID) {
      super(context, list, resID);
    }

    @Override public void initView(Bean bean, int position, CommonViewHolder viewHolder) {

    }
  }
}
