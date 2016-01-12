package com.zhicall.hax.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhicall.hax.DravenException;
import com.zhicall.hax.R;
import com.zhicall.hax.bean.NewsSummary;
import com.zhicall.hax.common.CommonAdapter;
import com.zhicall.hax.common.CommonViewHolder;
import com.zhicall.hax.net.Data;
import com.zhicall.hax.net.INewsService;
import java.util.ArrayList;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xingchen on 2016/1/11.
 * Email:huangjinxin@zhicall.cn
 */
public class InfoFragment extends Fragment {
  public final int PAGE_SIZE = 10;
  public int CurrentPage = 1;
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
    mNewsSummartAdapter=new NewsSummartAdapter(getActivity(),mNewsSummaryList,R.layout.layout_news_summary);
    mPullToRefreshListView.setAdapter(mNewsSummartAdapter);
    Data.service(INewsService.class)
        .getNewsSummary(0, CurrentPage, PAGE_SIZE)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(result -> {
          if (result.isSuccess()){
          mNewsSummaryList.addAll(result.getTngou());
          mNewsSummartAdapter.notifyDataSetChanged();}
          else {
            throw new DravenException("请求访问有误");
          }
        },Data.errorHanlder());
    return view;
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
      mTitleTextView.setText(newsSummary.getTitle());
      mSummaryTextView.setText(newsSummary.getDescription());
    }
  }
}
