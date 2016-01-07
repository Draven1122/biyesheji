// Generated code from Butter Knife. Do not modify!
package com.zhicall.hax.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CourseListActivity$$ViewBinder<T extends com.zhicall.hax.activities.CourseListActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492981, "field 'mListView'");
    target.mListView = finder.castView(view, 2131492981, "field 'mListView'");
  }

  @Override public void unbind(T target) {
    target.mListView = null;
  }
}
