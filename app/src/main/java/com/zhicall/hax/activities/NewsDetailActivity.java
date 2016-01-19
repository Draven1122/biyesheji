package com.zhicall.hax.activities;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import com.squareup.picasso.Picasso;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.DravenException;
import com.zhicall.hax.R;
import com.zhicall.hax.bean.NewsSummary;
import com.zhicall.hax.net.Data;
import com.zhicall.hax.net.INewsService;
import java.text.SimpleDateFormat;
import java.util.Date;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xingchen on 2016/1/12.
 * Email:huangjinxin@zhicall.cn
 */
public class NewsDetailActivity extends BaseActivity {
  @Bind(R.id.tv_news_title) TextView mTitleTextView;
  @Bind(R.id.tv_date) TextView mDateTextViews;
  @Bind(R.id.tv_news_detail) TextView mNewsDetailTextView;
  @Bind(R.id.img_icon) ImageView mIconImageView;
  private NewsSummary mNewsSummary;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news_detail);
    initActionbar(true, false, "Detail");
    mNewsSummary = (NewsSummary) getIntent().getExtras().getSerializable("newsSummary");
    Subscription subscription = Data.tianGouService(INewsService.class)
        .getNewsDetail(mNewsSummary.getId())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .doOnSubscribe(()->showProgressdialog("Loading...")).finallyDo(()->dissmissProgressDialog()).subscribe(result -> {
          if (!result.isSuccess()) {
            throw new DravenException("Server is connected but no data back");
          }
          mTitleTextView.setText(result.getTitle());
          mDateTextViews.setText(longToDate(result.getTime()));
         Spanned mSpanned= Html.fromHtml(result.getMessage());
          mNewsDetailTextView.setText(mSpanned);
          String url = "http://tnfs.tngou.net/img" + result.getImg();
          Picasso.with(NewsDetailActivity.this).load(url).into(mIconImageView);
        });
    mSubscriptionSet.add(subscription);
  }

  @Override public void initData() {

  }
  public static String longToDate(long lo) {
    Date date = new Date(lo);
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sd.format(date);
  }
}
