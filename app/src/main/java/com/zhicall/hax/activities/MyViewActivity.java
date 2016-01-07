package com.zhicall.hax.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.zhicall.hax.R;
import com.zhicall.hax.views.TestView;

/**
 * Created by Xingchen on 2015/12/9.
 * Email:huangjinxin@zhicall.cn
 */
public class MyViewActivity extends Activity implements TestView.OnTestViewTouchLisener {
  //@Nullable @Bind(R.id.testview) TestView mView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_myview);
    ButterKnife.bind(this);
    //mView.setOnTestViewTouchLisener(this);
  }

  @Override public void onTestViewTouch(MotionEvent event) {
    Toast.makeText(MyViewActivity.this, "我在 X=" + event.getX() + "Y=" + event.getY() + "被点击了",
        Toast.LENGTH_LONG).show();
  }
}
