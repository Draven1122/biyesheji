// Generated code from Butter Knife. Do not modify!
package com.zhicall.hax.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ViewPagerWithTabActivity$$ViewBinder<T extends com.zhicall.hax.activities.ViewPagerWithTabActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492996, "field 'mViewPager'");
    target.mViewPager = finder.castView(view, 2131492996, "field 'mViewPager'");
    view = finder.findRequiredView(source, 2131492995, "field 'mtabLine'");
    target.mtabLine = view;
  }

  @Override public void unbind(T target) {
    target.mViewPager = null;
    target.mtabLine = null;
  }
}
