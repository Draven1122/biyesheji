// Generated code from Butter Knife. Do not modify!
package com.zhicall.hax.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PullToRefreshActvity$$ViewBinder<T extends com.zhicall.hax.activities.PullToRefreshActvity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492988, "field 'lstv_test'");
    target.lstv_test = finder.castView(view, 2131492988, "field 'lstv_test'");
  }

  @Override public void unbind(T target) {
    target.lstv_test = null;
  }
}
