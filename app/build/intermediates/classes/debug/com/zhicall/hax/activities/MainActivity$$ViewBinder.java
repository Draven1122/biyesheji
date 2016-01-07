// Generated code from Butter Knife. Do not modify!
package com.zhicall.hax.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.zhicall.hax.activities.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492983, "method 'onShowClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onShowClicked(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492984, "method 'onDismissClicked'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onDismissClicked(p0);
        }
      });
  }

  @Override public void unbind(T target) {
  }
}
