package com.zhicall.hax.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.zhicall.hax.R;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Xingchen on 2015/12/14.
 * Email:huangjinxin@zhicall.cn
 */
public class ThreadActivity extends Activity {
  @Bind(R.id.btn_downLoad) Button btn_downLoad;
  @Bind(R.id.img_test) ImageView img_test;
  @Bind(R.id.btn_go) Button btn_go;
  private final String mImageURL =
      "http://5.26923.com/download/pic/000/328/ba80a24af0d5aba07e1461eca71f9502.jpg";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_thread);
    ButterKnife.bind(this);
  }

  @OnClick({ R.id.btn_downLoad, R.id.btn_go }) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_downLoad:
        showImageFromUrl(img_test, mImageURL);
        break;
      case R.id.btn_go:
        Intent intent = new Intent(ThreadActivity.this, CourseListActivity.class);
        startActivity(intent);
        break;
    }
  }

  private void showImageFromUrl(final ImageView imageView, final String url) {

    new Thread(new Runnable() {
      @Override public void run() {
        InputStream is = null;
        Bitmap bitmap = null;
        try {
          Thread.sleep(3000);
          Log.i("info", "------------------------Thread aweak!-----------------------");
          URL Url = new URL(url);
          is = Url.openStream();
          bitmap = BitmapFactory.decodeStream(is);
          final Bitmap finalBitmap = bitmap;
          runOnUiThread(new Runnable() {
            @Override public void run() {
              imageView.setImageBitmap(finalBitmap);
              Log.i("info", "------------------------Bitmap set up!-----------------------");
            }
          });
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (MalformedURLException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
}
