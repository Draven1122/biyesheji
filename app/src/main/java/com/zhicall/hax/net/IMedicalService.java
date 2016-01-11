package com.zhicall.hax.net;

import com.zhicall.hax.bean.MedicalCategory;
import com.zhicall.hax.bean.Result;
import retrofit.http.GET;
import retrofit.http.Headers;
import rx.Observable;

/**
 * Created by Xingchen on 2016/1/11.
 * Email:huangjinxin@zhicall.cn
 */
public interface IMedicalService {
  @GET("/tngou/drug/classify")
  @Headers({"apikey:d99a9ad0a9531fcbd1bb3139651e7249"})
  Observable<Result<MedicalCategory>> category();
}
