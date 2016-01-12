package com.zhicall.hax.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.zhicall.hax.DravenException;
import com.zhicall.hax.R;
import com.zhicall.hax.bean.NewsSummary;
import com.zhicall.hax.common.CommonAdapter;
import com.zhicall.hax.common.CommonViewHolder;
import com.zhicall.hax.net.Data;
import com.zhicall.hax.net.INewsService;
import com.zhicall.hax.utils.ToastManager;
import java.util.ArrayList;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xingchen on 2016/1/11.
 * Email:huangjinxin@zhicall.cn
 */
public class InfoFragment extends Fragment {

  private boolean isFresh = true;
  public final int PAGE_SIZE = 15;
  public int mCurrentPage = 1;
  public final int NEWS_CATEGORY_DRUG = 4;
  public final int NEWS_CATEGORY_DISEASE = 7;
  public final int NEWS_CATEGORY_MEDICAL = 2;
  public final int NEWS_CATEGORY_TIPS = 3;
  public int mCurrentCategory = NEWS_CATEGORY_DRUG;
  private NewsSummartAdapter mNewsSummartAdapter;
  private View view;
  @Bind(R.id.lstv_news) PullToRefreshListView mPullToRefreshListView;
  @Bind(R.id.v_line1) View mLineView1;
  @Bind(R.id.v_line2) View mLineView2;
  @Bind(R.id.v_line3) View mLineView3;
  @Bind(R.id.v_line4) View mLineView4;
  private List<View> lineList = new ArrayList<View>();
  private List<NewsSummary> mNewsSummaryList = new ArrayList<NewsSummary>();

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  public InfoFragment() {
    super();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (view == null) view = inflater.inflate(R.layout.fragment_info, null);
    ButterKnife.bind(this, view);
    lineList.add(mLineView1);
    lineList.add(mLineView2);
    lineList.add(mLineView3);
    lineList.add(mLineView4);
    mNewsSummartAdapter =
        new NewsSummartAdapter(getActivity(), mNewsSummaryList, R.layout.layout_news_summary);
    mPullToRefreshListView.setAdapter(mNewsSummartAdapter);
    if (isFresh) {
      Data.tianGouService(INewsService.class)
          .getNewsSummary(0, mCurrentPage, PAGE_SIZE)
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(result -> {
            if (result.isSuccess()) {
              mNewsSummaryList.addAll(result.getTngou());
              mNewsSummartAdapter.notifyDataSetChanged();
              isFresh = false;
            } else {
              throw new DravenException("请求访问有误");
            }
          }, Data.errorHanlder());
      mPullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          ToastManager.showToast("点了");
        }
      });
      mPullToRefreshListView.setOnRefreshListener(
          new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
              getDate(true);
            }

            @Override public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
              getDate(false);
            }
          });
    }
    return view;
  }

  private void getDate(boolean isFresh) {
    if (isFresh) {
      mCurrentPage = 1;
    } else {
      mCurrentPage++;
    }

    Data.tianGouService(INewsService.class)
        .getNewsSummary(mCurrentCategory, mCurrentPage, PAGE_SIZE)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .finallyDo(() -> mPullToRefreshListView.onRefreshComplete())
        .subscribe(result -> {
          if (!result.isSuccess()) {
            throw new DravenException("Server is connected but no data back!");
          }
          if (isFresh)
          mNewsSummartAdapter.setList(result.getTngou());
          else
          mNewsSummartAdapter.addList(result.getTngou());
          mNewsSummartAdapter.notifyDataSetChanged();
        }, Data.errorHanlder());

  }

  public void toggleLine(int position) {
    for (View view : lineList) {
      view.setVisibility(View.INVISIBLE);
    }
    lineList.get(position).setVisibility(View.VISIBLE);
  }

  @Override public void onDestroyView() {
    ButterKnife.unbind(this);
    super.onDestroyView();
  }

  public class NewsSummartAdapter extends CommonAdapter<NewsSummary> {

    public NewsSummartAdapter(Context context, List<NewsSummary> list, int resID) {
      super(context, list, resID);
    }

    @Override
    public void initView(NewsSummary newsSummary, int position, CommonViewHolder viewHolder) {
      TextView mTitleTextView = viewHolder.getView(R.id.tv_newsTitle);
      TextView mSummaryTextView = viewHolder.getView(R.id.tv_news_summary);
      ImageView mIconImageView = viewHolder.getView(R.id.img_icon);
      mTitleTextView.setText(newsSummary.getTitle());
      String str = newsSummary.getDescription();
      if (newsSummary.getDescription().length() > 23) {
        str = newsSummary.getDescription().substring(0, 22) + "...";
        mSummaryTextView.setText(str);
        String url = "http://tnfs.tngou.net/img" + newsSummary.getImg();
        Picasso.with(getActivity()).load(url).into(mIconImageView);
      }
    }
  }
}
