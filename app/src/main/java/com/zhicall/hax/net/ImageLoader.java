package com.zhicall.hax.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;
import com.zhicall.hax.R;
import com.zhicall.hax.activities.CourseListActivity;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Xingchen on 2015/12/9.
 * Email:huangjinxin@zhicall.cn
 */
public class ImageLoader {
  private int cacheMemory;
  public LruCache<String, Bitmap> ImageCache = null;
  private ListView mListView;
  private Set<ImageAsyncTask> mAsyncTasks;
  public ImageLoader(ListView mListView) {
    cacheMemory = (int) (Runtime.getRuntime().maxMemory()) / 4;
    ImageCache = new LruCache<String, Bitmap>(cacheMemory) {
      @Override protected int sizeOf(String key, Bitmap bitmap) {
        return bitmap.getByteCount();
      }
    };
    this.mListView = mListView;
    mAsyncTasks = new HashSet<ImageAsyncTask>();
  }

  //增加BitMap到缓存
  private void addBitMapToCache(String key, Bitmap bitmap) {
    if (getBitMapFromCache(key) == null) {
      ImageCache.put(key, bitmap);
    } else {

    }
  }

  private Bitmap getBitMapFromCache(String key) {
    Bitmap bitmap = ImageCache.get(key);
    return bitmap;
  }

  public void showImageByAsyncTask(ImageView imageView, String url) {
    Bitmap bitmap = getBitMapFromCache(url);
    if (bitmap == null) {
      ImageAsyncTask imageAsyncTask = new ImageAsyncTask(url);
      imageAsyncTask.execute(url);
      return;
    }
    imageView.setImageResource(R.mipmap.ic_launcher);
  }

  public void loadImages(int Start, int End) {
    for (int i = Start; i < End; i++) {
      String url = CourseListActivity.URLs[i];
      Bitmap bitmap = getBitMapFromCache(url);
      if (bitmap == null) {
        ImageAsyncTask imageAsyncTask = new ImageAsyncTask(url);
        imageAsyncTask.execute(url);
        mAsyncTasks.add(imageAsyncTask);
      } else {
        ImageView imageView = (ImageView) mListView.findViewWithTag(url);
        imageView.setImageBitmap(bitmap);
      }
    }
  }

  public void cancelAllTasks() {
    if (mAsyncTasks != null) {
      for (ImageAsyncTask task : mAsyncTasks) {
        task.cancel(false);
      }
    }
  }

  /**
   * Created by Xingchen on 2015/12/9.
   * Email:huangjinxin@zhical.cn
   */
  public class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private String Url;

    public ImageAsyncTask(String Url) {
      this.Url = Url;
    }

    @Override protected Bitmap doInBackground(String... params) {
      String url = params[0];
      Bitmap bitmap = getBitMapFromURL(url);
        addBitMapToCache(url, bitmap);
      return bitmap;
    }

    @Override protected void onPostExecute(Bitmap bitmap) {

      ImageView mImageView = (ImageView) mListView.findViewWithTag(Url);
      if(((String)mImageView.getTag()).equals(Url)){
        if (mImageView != null && bitmap != null) {
          mImageView.setImageBitmap(bitmap);
        }
      }
    }

    public Bitmap getBitMapFromURL(String url) {
      URL Url;
      InputStream is;
      Bitmap bitmap = null;
      try {
        Url = new URL(url);
        is = Url.openStream();
        bitmap = BitmapFactory.decodeStream(is);
      } catch (MalformedURLException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return bitmap;
    }
  }
}
