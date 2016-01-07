package com.zhicall.hax;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.zhicall.hax.bean.Bean;
import com.zhicall.hax.common.CommonAdapter;
import com.zhicall.hax.common.CommonViewHolder;
import java.util.ArrayList;
import java.util.List;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {
  WaveSwipeRefreshLayout mWaveSwipeRefreshLayout = null;
  ListView mListView = null;
  List<Bean> data = null;
  BeanAdapter mBeanAdapter = null;
  int CurrentIndex = 1;
  Handler mHandler = null;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mHandler = new Handler() {
      @Override public void handleMessage(Message msg) {
        Toast.makeText(MainActivity.this, "刷新成功", Toast.LENGTH_LONG).show();
        mBeanAdapter.notifyDataSetChanged();
        mWaveSwipeRefreshLayout.setRefreshing(false);
      }
    };
    data = new ArrayList<Bean>();
    for (int i = 0; i < 10; i++) {
      String name = "第 " + (i +1)+ " 个姓名";
      String time = "第 " + (i +1) + " 个时间";
      Bean bean = new Bean(time, name);
      data.add(bean);
    }
    CurrentIndex = data.size();
    mBeanAdapter = new BeanAdapter(this, data, R.layout.item_mlstv);
    mListView = (ListView) findViewById(R.id.main_list);
    mListView.setAdapter(mBeanAdapter);
    mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.main_swipe);
    mWaveSwipeRefreshLayout.setRefreshing(false);
    mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        // Do work to refresh the list here.
        new Thread(new Runnable() {
          @Override public void run() {
            try {
              Thread.sleep(3000);
              loadData();
              Message msg = Message.obtain();
              mHandler.sendMessage(msg);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }).start();
      }
    });
  }

  public class BeanAdapter extends CommonAdapter<Bean> {

    public BeanAdapter(Context context, List<Bean> list, int resID) {
      super(context, list, resID);
    }

    @Override public void initView(Bean bean, int position, CommonViewHolder viewHolder) {

      TextView tv_name = viewHolder.getView(R.id.tv_name);
      TextView tv_time = viewHolder.getView(R.id.tv_time);
      tv_name.setText(bean.getName());
      tv_time.setText(bean.getTime());
    }
  }

  private void loadData() {
      String name = "第 " + (CurrentIndex+1) + "个人";
      String time = "第 " + (CurrentIndex+1)+ " 个时间";
      Bean bean = new Bean(time, name);
      data.add(bean);
    CurrentIndex = data.size() + 1;
  }
}
