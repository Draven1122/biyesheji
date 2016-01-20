package com.zhicall.hax.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.DravenException;
import com.zhicall.hax.R;
import com.zhicall.hax.bean.Body;
import com.zhicall.hax.net.Data;
import com.zhicall.hax.net.IMedicalService;
import com.zhicall.hax.utils.ToastManager;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xingchen on 2016/1/19.
 * Email:huangjinxin@zhicall.cn
 */
public class BodyActivity extends BaseActivity {
  @Bind(R.id.btn_seaserch) Button mSearchButton;
  @Bind(R.id.et_disease_keywords) EditText mSearchEditText;
  @Bind(R.id.lstv_body) PullToRefreshExpandableListView mPullToRefreshExpandableListView;
  private List<Body> mBodyList = new ArrayList<>();
  private ExpandableListView mExpandableListView;
  private BodyAdapter mBodyAdapter;
  private LayoutInflater mLayoutInflater;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_body);
    initActionbar(true, false, "Body");
    initData();
  }

  @OnClick(R.id.btn_seaserch) public void onSearchButtonCliked() {
    String keywords = mSearchEditText.getText().toString().trim();
    if (keywords == null || keywords.length() < 1) {
      ToastManager.showToast("关键字不能为空");
      return;
    }
    Data.tianGouService(IMedicalService.class)
        .searchDisease(keywords)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .doOnSubscribe(() -> showProgressdialog("Seaching..."))
        .finallyDo(this::dissmissProgressDialog)
        .subscribe(disease -> {
          if (disease.getStatus()) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("disease", disease);
            Intent intent = new Intent(BodyActivity.this, DiseaseDetailActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
          } else {
            throw new DravenException(disease.getMsg());
          }
        }, Data.errorHanlder(mProgressDialog));
  }

  @Override public void initData() {
    mLayoutInflater = LayoutInflater.from(this);
    mBodyAdapter = new BodyAdapter();
    mExpandableListView = mPullToRefreshExpandableListView.getRefreshableView();
    mExpandableListView.setAdapter(mBodyAdapter);
    Subscription mSubscription = Data.tianGouService(IMedicalService.class)
        .body()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .map(result -> result.getTngou())
        .doOnSubscribe(() -> showProgressdialog("Loading..."))
        .finallyDo(() -> {
          this.dissmissProgressDialog();
          if (mBodyList.size() > 1) mExpandableListView.expandGroup(0);
        })
        .subscribe(list -> {
          mBodyList.addAll(list);
          mBodyAdapter.notifyDataSetChanged();
        }, Data.errorHanlder());
    mSubscriptionSet.add(mSubscription);
    mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
      @Override public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
          int childPosition, long id) {
        Body mBody = mBodyList.get(groupPosition).getPlaces().get(childPosition);
        Intent mIntent = new Intent(BodyActivity.this, DiseaseListActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("body", mBody);
        mIntent.putExtras(mBundle);
        startActivity(mIntent);
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
