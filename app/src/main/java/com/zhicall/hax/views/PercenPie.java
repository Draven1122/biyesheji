package com.zhicall.hax.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.zhicall.hax.R;

/**
 * Created by Xingchen on 2015/12/22.
 * Email:huangjinxin@zhicall.cn
 */
public class PercenPie extends View {
  private int Radius;
  private int percent;
  private int colors[] = { R.color.light_blue, R.color.colorPrimary };
  private boolean currentColor = true;   //true蓝色

  public PercenPie(Context context) {
    super(context);
  }

  public PercenPie(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public PercenPie(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onDraw(Canvas canvas) {
    int width = getMeasuredWidth();
    int height = getMeasuredHeight();

    Radius = Math.min(width, height) / 2 - 10;  //取宽高小的二分之1-10作为半径
    int cx = width / 2;
    int cy = height / 2;
    Paint paint = new Paint();
    paint.setColor(
        currentColor ? getResources().getColor(colors[0]) : getResources().getColor(colors[1]));
    paint.setStyle(Paint.Style.FILL);
    paint.setAntiAlias(true);
    canvas.drawCircle(cx, cy, Radius, paint);
    canvas.save();
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    currentColor = !currentColor;
    this.invalidate();
    return super.onTouchEvent(event);
  }
}
