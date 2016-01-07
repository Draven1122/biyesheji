package com.zhicall.hax.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Xingchen on 2015/12/9.
 * Email:huangjinxin@zhicall.cn
 */
public class TestView extends View {
  private OnTestViewTouchLisener mOnTestViewTouchLisener;

  public TestView(Context context) {
    super(context);
  }

  public TestView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void setOnTestViewTouchLisener(OnTestViewTouchLisener onTestViewTouchLisener) {
    mOnTestViewTouchLisener = onTestViewTouchLisener;
  }

  @Override public boolean dispatchTouchEvent(MotionEvent event) {
    int action = event.getAction();
      if (mOnTestViewTouchLisener != null) {
        mOnTestViewTouchLisener.onTestViewTouch(event);
      }

    return false;
  }

  public interface OnTestViewTouchLisener {
    public void onTestViewTouch(MotionEvent event);
  }
}
