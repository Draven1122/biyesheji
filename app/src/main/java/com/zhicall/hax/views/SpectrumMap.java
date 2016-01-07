package com.zhicall.hax.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.zhicall.hax.R;

/**
 * 频谱图
 *
 * Created by Xingchen on 2015/12/24.
 * Email:huangjinxin@zhicall.cn
 */
public class SpectrumMap extends View {
  private int RECT_COUNT = 20;//频谱总条数
  private int mViewWidth = 0;
  private int mViewHeight = 0;
  private int MARGING = 0;    //相邻条形之间的间隔距离
  private int RECT_WIDTH;

  public SpectrumMap(Context context) {
    super(context);
  }

  public SpectrumMap(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public SpectrumMap(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onDraw(Canvas canvas) {
    mViewWidth = getMeasuredWidth();
    mViewHeight = getMeasuredHeight();
    RECT_WIDTH = mViewWidth / RECT_COUNT;
    MARGING = (int) (RECT_WIDTH * 0.1);
    Paint paint = new Paint();
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(getResources().getColor(R.color.light_blue));
    int x1, y1, x2, y2;
    for (int i = 0; i < RECT_COUNT; i++) {
      x1 = RECT_WIDTH * i + MARGING;
      y1 = mViewHeight - (int) (Math.random() * mViewHeight);
      if (y1 < 0) {
        y1 = 0;
      }
      x2 = RECT_WIDTH * (i + 1) - MARGING;
      y2 = mViewHeight;
      canvas.drawRect(x1, y1, x2, y2, paint);
    }
    postInvalidateDelayed(150);
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
  }
}
