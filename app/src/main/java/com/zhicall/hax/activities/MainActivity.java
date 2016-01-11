package com.zhicall.hax.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.Bind;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;

/**APP首页
 * Created by Xingchen on 2016/1/7.
 * Email:huangjinxin@zhicall.cn
 */
public class MainActivity extends BaseActivity {
  @Bind(R.id.rlayout_fragmengt_container) RelativeLayout mFragmentContainer;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initActionbar(true, false, "首页");
    Button mButton=new Button(this);
  }
}
