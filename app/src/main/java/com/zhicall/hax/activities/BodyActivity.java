package com.zhicall.hax.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import butterknife.Bind;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;
import com.zhicall.hax.bean.Body;
import com.zhicall.hax.net.Data;
import com.zhicall.hax.net.IMedicalService;
import com.zhicall.hax.utils.ToastManager;
import java.util.ArrayList;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xingchen on 2016/1/19.
 * Email:huangjinxin@zhicall.cn
 */
public class BodyActivity extends BaseActivity {
  private List<Body> mBodyList = new ArrayList<>();
  @Bind(R.id.lstv_body) PullToRefreshExpandableListView mPullToRefreshExpandableListView;
  private ExpandableListView mExpandableListView;
  private BodyAdapter mBodyAdapter;
  private LayoutInflater mLayoutInflater;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_body);
    initActionbar(true, false, "身体部位");
    initView();
  }

  @Override public void initView() {
    mLayoutInflater = LayoutInflater.from(this);
    mBodyAdapter = new BodyAdapter();
    mExpandableListView = mPullToRefreshExpandableListView.getRefreshableView();
    mExpandableListView.setAdapter(mBodyAdapter);
    Data.tianGouService(IMedicalService.class)
        .body()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .map(result -> result.getTngou())
        .doOnSubscribe(() -> showProgressdialog("正在获取身体部位列表..."))
        .finallyDo(this::dissmissProgressDialog)
        .subscribe(list -> {
          mBodyList.addAll(list);
          mBodyAdapter.notifyDataSetChanged();
        }, Data.errorHanlder());
    mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
      @Override public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
          int childPosition, long id) {
        ToastManager.showToast(
            "你选择了:" + mBodyList.get(groupPosition).getPlaces().get(childPosition).getName());
        return false;
      }
    });
  }

  public class BodyAdapter extends BaseExpandableListAdapter {
    public BodyAdapter() {
    }

    @Override public int getGroupCount() {
      return mBodyList.size();
    }

    @Override public int getChildrenCount(int groupPosition) {
      return mBodyList.get(groupPosition).getPlaces().size();
    }

    @Override public Object getGroup(int groupPosition) {
      return mBodyList.get(groupPosition);
    }

    @Override public Object getChild(int groupPosition, int childPosition) {
      return mBodyList.get(groupPosition).getPlaces().get(childPosition);
    }

    @Override public long getGroupId(int groupPosition) {
      return groupPosition;
    }

    @Override public long getChildId(int groupPosition, int childPosition) {
      return mBodyList.get(groupPosition).getPlaces().get(childPosition).getId();
    }

    @Override public boolean hasStableIds() {
      return false;
    }

    @Override public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
        ViewGroup parent) {
      ViewHolder mHeaderViewHolder;
      TextView mTextView;
      if (convertView == null) {
        convertView = mLayoutInflater.inflate(R.layout.layout_body_list_item_header, null);
        mTextView = (TextView) convertView.findViewById(R.id.tv_body_header_name);
        mHeaderViewHolder = new ViewHolder();
        mHeaderViewHolder.mTextView = mTextView;
        convertView.setTag(mHeaderViewHolder);
      }
      mHeaderViewHolder = (ViewHolder) convertView.getTag();
      mTextView = mHeaderViewHolder.mTextView;
      mTextView.setText(mBodyList.get(groupPosition).getName());
      return convertView;
    }

    @Override public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
        View convertView, ViewGroup parent) {
      ViewHolder mHeaderViewHolder;
      TextView mTextView;
      if (convertView == null) {
        convertView = mLayoutInflater.inflate(R.layout.layout_body_list_item, null);
        mTextView = (TextView) convertView.findViewById(R.id.tv_body_item_name);
        mHeaderViewHolder = new ViewHolder();
        mHeaderViewHolder.mTextView = mTextView;
        convertView.setTag(mHeaderViewHolder);
      }
      mHeaderViewHolder = (ViewHolder) convertView.getTag();
      mTextView = mHeaderViewHolder.mTextView;
      mTextView.setText(mBodyList.get(groupPosition).getPlaces().get(childPosition).getName());
      return convertView;
    }

    @Override public boolean isChildSelectable(int groupPosition, int childPosition) {
      return true;
    }
  }

  public class ViewHolder {
    TextView mTextView;
  }
}
