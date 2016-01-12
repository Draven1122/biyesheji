package com.zhicall.hax.activities;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;

/**
 * Created by Xingchen on 2016/1/12.
 * Email:huangjinxin@zhicall.cn
 */
public class NewsDetailActivity extends BaseActivity {
  @Bind(R.id.tv_news_title) TextView mTitleTextView;
  @Bind(R.id.tv_date) TextView mDateTextViews;
  @Bind(R.id.tv_news_detail) TextView mNewsDetailTextView;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news_detail);
    initActionbar(true,false,"Detail");

  }

  @Override public void initView() {

  }
}
