package com.zhicall.hax.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Xingchen on 2015/12/22.
 * Email:huangjinxin@zhicall.cn
 */
public class MyTextview extends TextView {
  LinearGradient mLinearGradient = null;
  Matrix mGradientMatrix;
  int mViewWidth;
  Paint mPaint = null;
  int mTranslate;

  public MyTextview(Context context) {
    super(context);
  }

  public MyTextview(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyTextview(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (mGradientMatrix != null) {
      mTranslate += mViewWidth / 5;
      if (mTranslate > 2 * mViewWidth) {
        mTranslate = -mViewWidth;
      }
      mGradientMatrix.setTranslate(mTranslate, 0);
      mLinearGradient.setLocalMatrix(mGradientMatrix);
      postInvalidateDelayed(100);
    }
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    if (mViewWidth == 0) {
      mViewWidth = getMeasuredWidth();
      if (mViewWidth > 0) {
        mPaint = getPaint();
        mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
            new int[] { Color.BLUE, 0xffffffff, Color.BLUE }, null, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
        mGradientMatrix = new Matrix();
      }
    }
  }
}
