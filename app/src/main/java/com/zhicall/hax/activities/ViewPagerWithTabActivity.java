package com.zhicall.hax.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.zhicall.hax.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xingchen on 2015/12/10.
 * Email:huangjinxin@zhicall.cn
 */
public class ViewPagerWithTabActivity extends Activity implements ViewPager.OnPageChangeListener {
  @Bind(R.id.viewpager_test) ViewPager mViewPager;
  @Bind(R.id.tabLine) View mtabLine;
  TextView btn_view1;
  private int oneThridScrean;
  private int mCurrentPage;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_viewpagerwithtabline);
    ButterKnife.bind(this);
    initOneThridScreenWidth();
    btn_view1 = (TextView) findViewById(R.id.btn_view1);
    btn_view1.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mViewPager.setCurrentItem(0);
      }
    });
    mViewPager.setOnPageChangeListener(this);
    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mtabLine.getLayoutParams();
    lp.width = oneThridScrean;
    TabViewPagerAdapter mTabViewPagerAdapter =
        new TabViewPagerAdapter(ViewPagerWithTabActivity.this);
    mViewPager.setAdapter(mTabViewPagerAdapter);
    mViewPager.setOnPageChangeListener(this);
  }

  /**
   * 初始化1/3屏幕的值，单位为px
   */
  private void initOneThridScreenWidth() {
    Display display = getWindow().getWindowManager().getDefaultDisplay();
    DisplayMetrics displayMetrics = new DisplayMetrics();
    display.getMetrics(displayMetrics);
    oneThridScrean = displayMetrics.widthPixels / 3;
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    //Log.e("info", position + "," + positionOffset + "," + positionOffsetPixels);
    if (mCurrentPage == 0 && position == 0) {
      //0->1
      RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mtabLine.getLayoutParams();
      lp.leftMargin = (int) (mCurrentPage * oneThridScrean + positionOffset * oneThridScrean);
      mtabLine.setLayoutParams(lp);
    } else if (mCurrentPage == 1 && position == 0) {
      //1->0
      RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mtabLine.getLayoutParams();
      lp.leftMargin = (int) (mCurrentPage * oneThridScrean + (positionOffset - 1) * oneThridScrean);
      mtabLine.setLayoutParams(lp);
    } else if (mCurrentPage == 1 && position == 1) {
      //1->2
      RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mtabLine.getLayoutParams();
      lp.leftMargin = (int) (mCurrentPage * oneThridScrean + positionOffset * oneThridScrean);
      mtabLine.setLayoutParams(lp);
    } else if (mCurrentPage == 2 && position == 1) {
      //2->1
      RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mtabLine.getLayoutParams();
      lp.leftMargin = (int) (mCurrentPage * oneThridScrean + (positionOffset - 1) * oneThridScrean);
      mtabLine.setLayoutParams(lp);
    }
  }

  @Override public void onPageSelected(int position) {
    mCurrentPage = position;
  }

  @Override public void onPageScrollStateChanged(int state) {

  }

  public class TabViewPagerAdapter extends PagerAdapter {

    private List<View> mViewList = null;
    private LayoutInflater mLayoutInflater = null;

    public TabViewPagerAdapter(Context context) {
      mViewList = new ArrayList<View>();
      mLayoutInflater = LayoutInflater.from(context);
      View view1 = mLayoutInflater.inflate(R.layout.view1, null);
      View view2 = mLayoutInflater.inflate(R.layout.view2, null);
      View view3 = mLayoutInflater.inflate(R.layout.view3, null);

      mViewList.add(view1);
      mViewList.add(view2);
      mViewList.add(view3);
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {
      container.addView(mViewList.get(position));
      return mViewList.get(position);
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView(mViewList.get(position));
    }

    @Override public int getCount() {
      return mViewList.size();
    }

    @Override public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }
  }
}








