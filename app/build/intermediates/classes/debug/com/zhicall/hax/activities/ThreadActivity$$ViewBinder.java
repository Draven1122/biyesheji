// Generated code from Butter Knife. Do not modify!
package com.zhicall.hax.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ThreadActivity$$ViewBinder<T extends com.zhicall.hax.activities.ThreadActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492991, "field 'btn_downLoad' and method 'onClick'");
    target.btn_downLoad = finder.castView(view, 2131492991, "field 'btn_downLoad'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131492985, "field 'img_test'");
    target.img_test = finder.castView(view, 2131492985, "field 'img_test'");
    view = finder.findRequiredView(source, 2131492992, "field 'btn_go' and method 'onClick'");
    target.btn_go = finder.castView(view, 2131492992, "field 'btn_go'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.btn_downLoad = null;
    target.img_test = null;
    target.btn_go = null;
  }
}
