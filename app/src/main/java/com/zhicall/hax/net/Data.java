package com.zhicall.hax.net;

import android.app.ProgressDialog;
import com.zhicall.hax.MyApplication;
import com.zhicall.hax.R;
import com.zhicall.hax.utils.ToastManager;
import java.util.Map;
import java.util.WeakHashMap;
import retrofit.RestAdapter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Xingchen on 2016/1/11.
 * Email:huangjinxin@zhicall.cn
 * 获取json数据获取的方法类
 */
public final class Data {
  private static RestAdapter mInfoRestAdapter =                               //获取药品信息
      new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
          .setEndpoint(MyApplication.getContext().getResources().getString(R.string.medical_url))
          .build();
  private static RestAdapter mTestRestAdapter =                               //获取药品信息
      new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
          .setEndpoint(
              MyApplication.getContext().getResources().getString(R.string.locallhost_8080))
          .build();
  private static Map services = new WeakHashMap<String, Object>();

  public static <T> Func1<Observable<T>, Observable<T>> flatmaper(Observable<T> mObservabler) {
    return new Func1<Observable<T>, Observable<T>>() {
      @Override public Observable<T> call(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
      }
    };
  }

  public static <T> T tianGouService(Class<T> clazz) {
    T service = (T) services.get(clazz.getName());
    if (service == null) {
      service = mInfoRestAdapter.create(clazz);
      services.put(clazz.getName(), service);
      return service;
    }
    return service;
  }

  public static <T> T testService(Class<T> clazz) {
    T service = (T) services.get(clazz.getName());
    if (service == null) {
      service = mTestRestAdapter.create(clazz);
      services.put(clazz.getName(), service);
      return service;
    }
    return service;
  }

  public static Action1<Throwable> errorHanlder() {
    return e -> ToastManager.showToast(e.getMessage());
  }

  public static Action1<Throwable> errorHanlder(ProgressDialog mProgressDialog) {
    return e -> {
      ToastManager.showToast(e.getMessage());
      if (mProgressDialog != null) mProgressDialog.dismiss();
    };
  }
}
