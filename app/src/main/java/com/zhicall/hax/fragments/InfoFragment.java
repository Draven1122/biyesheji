package com.zhicall.hax.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhicall.hax.R;
import com.zhicall.hax.bean.NewsSummary;
import com.zhicall.hax.common.CommonAdapter;
import com.zhicall.hax.common.CommonViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xingchen on 2016/1/11.
 * Email:huangjinxin@zhicall.cn
 */
public class InfoFragment extends Fragment {

  private View view;
  @Bind(R.id.lstv_news) PullToRefreshListView mPullToRefreshListView;
  @Bind(R.id.v_line1) View mLineView1;
  @Bind(R.id.v_line2) View mLineView2;
  @Bind(R.id.v_line3) View mLineView3;
  @Bind(R.id.v_line4) View mLineView4;
  private List<View> lineList = new ArrayList<View>();

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

    return view;
  }

  @Override public void onDestroyView() {
    ButterKnife.unbind(this);
    super.onDestroyView();
  }

  public void toggleLine(int position) {
    for (View view : lineList) {
      view.setVisibility(View.INVISIBLE);
    }
    lineList.get(position).setVisibility(View.VISIBLE);
  }
  public class NewsSummartAdapter extends CommonAdapter<NewsSummary>{

    public NewsSummartAdapter(Context context, List<NewsSummary> list, int resID) {
      super(context, list, resID);
    }

    @Override
    public void initView(NewsSummary newsSummary, int position, CommonViewHolder viewHolder) {

      }
  }
}
