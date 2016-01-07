package com.zhicall.hax.activities;

import android.app.Activity;
import android.os.Bundle;
import butterknife.Bind;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhicall.hax.R;

/**
 * Created by Xingchen on 2015/12/15.
 * Email:huangjinxin@zhicall.cn
 */
public class PullToRefreshActvity extends Activity {
  @Bind(R.id.lstv_pulltorefresh) PullToRefreshListView lstv_test;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pulltorefresh);
  }
}
