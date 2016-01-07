package com.zhicall.hax.activities;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.zhicall.hax.R;

/**
 * Created by Xingchen on 2015/12/9.
 * Email:huangjinxin@zhicall.cn
 */
public class AnimatorActivity extends Activity {
  @Bind(R.id.btn_start) Button btn_start;
  @Bind(R.id.img_test) ImageView image_test;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_myview);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.btn_start) void bc(final View view) {
    ((Button) view).setText("登陆中");
    ObjectAnimator anim = ObjectAnimator.ofFloat(image_test, "translationX", -100, 100).setDuration(500);
    anim.start();
  }
}
