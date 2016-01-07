package com.zhicall.hax.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Xingchen on 2015/12/24.
 * Email:huangjinxin@zhicall.cn
 */
public class MyScrollView extends ViewGroup {
  public MyScrollView(Context context) {
    super(context);
  }

  public MyScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    for (int i = 0; i < getChildCount(); i++) {
      View childView = getChildAt(i);
      measureChild(childView,widthMeasureSpec,heightMeasureSpec);
    }
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    int childCount=getChildCount();
    MarginLayoutParams lp= (MarginLayoutParams) getLayoutParams();
      }
}
