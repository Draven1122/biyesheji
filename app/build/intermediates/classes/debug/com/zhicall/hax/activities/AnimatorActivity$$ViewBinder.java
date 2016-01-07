// Generated code from Butter Knife. Do not modify!
package com.zhicall.hax.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AnimatorActivity$$ViewBinder<T extends com.zhicall.hax.activities.AnimatorActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492986, "field 'btn_start' and method 'bc'");
    target.btn_start = finder.castView(view, 2131492986, "field 'btn_start'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.bc(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492985, "field 'image_test'");
    target.image_test = finder.castView(view, 2131492985, "field 'image_test'");
  }

  @Override public void unbind(T target) {
    target.btn_start = null;
    target.image_test = null;
  }
}
