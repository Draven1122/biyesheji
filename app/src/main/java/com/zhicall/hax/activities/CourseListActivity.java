package com.zhicall.hax.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.zhicall.hax.R;
import com.zhicall.hax.bean.Course;
import com.zhicall.hax.bean.Result;
import com.zhicall.hax.common.CommonAdapter;
import com.zhicall.hax.common.CommonViewHolder;
import com.zhicall.hax.net.Http;
import com.zhicall.hax.net.ImageLoader;
import java.util.List;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Xingchen on 2015/12/8.
 * Email:huangjinxin@zhicall.cn
 */
public class CourseListActivity extends Activity implements AbsListView.OnScrollListener {
  @Bind(R.id.lstv_courselist) ListView mListView;
  private ImageLoader imageLoader = null;
  private int mStart;
  private int mEnd;
  private boolean flag = true;    //当前是否为预加载
  public static String[] URLs = null;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_courselist);
    ButterKnife.bind(this);
    init();
  }

  private void init() {
    ob(Http.courseService().getCourseList(4, 30)).subscribe(new Action1<Result<Course>>() {
      @Override public void call(Result<Course> courseResult) {
        List<Course> data = courseResult.getData();
        mListView.setAdapter(
            new CourseListAdapter(CourseListActivity.this, data, R.layout.layout_courselist_item));
        mListView.setOnScrollListener(CourseListActivity.this);
      }
    });
  }

  public <T> Observable<T> ob(Observable<T> observable) {
    return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
  }

  @Override public void onScrollStateChanged(AbsListView view, int scrollState) {
    //滚动停止的时候,加载可见项
    if (scrollState == SCROLL_STATE_IDLE) {
      imageLoader.loadImages(mStart, mEnd);
    } else {
      imageLoader.cancelAllTasks();
    }
  }

  @Override public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount,
      int totalItemCount) {
    this.mStart = firstVisibleItem;
    this.mEnd = mStart + visibleItemCount;
    if (flag && visibleItemCount > 0) {
      Toast.makeText(CourseListActivity.this,visibleItemCount+"",Toast.LENGTH_LONG).show();
      imageLoader.loadImages(mStart, mEnd);
      flag = false;
    }
  }

  public class CourseListAdapter extends CommonAdapter<Course> {

    public CourseListAdapter(Context context, List<Course> list, int resID) {
      super(context, list, resID);
      imageLoader = new ImageLoader(mListView);
      URLs = new String[list.size()];
      for (int i = 0; i < list.size(); i++) {
        URLs[i] = list.get(i).getPicSmall();
      }
    }

    @Override
    public void initView(Course course, int position, CommonViewHolder viewHolder) {
      TextView tv_title = viewHolder.getView(R.id.tv_title);
      tv_title.setText(course.getName());
      TextView tv_desc = viewHolder.getView(R.id.tv_desc);
      tv_desc.setText(course.getDescription());
      ImageView iv_icon = viewHolder.getView(R.id.iv_icon);
      iv_icon.setImageResource(R.mipmap.ic_launcher);
      iv_icon.setTag(course.getPicSmall());
      //imageLoader.showImageByAsyncTask(iv_icon, course.getPicSmall());
    }
  }
}
