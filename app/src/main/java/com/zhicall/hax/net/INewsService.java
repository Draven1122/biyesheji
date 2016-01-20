package com.zhicall.hax.net;

import android.support.annotation.Nullable;
import com.zhicall.hax.bean.NewsCategory;
import com.zhicall.hax.bean.NewsDetail;
import com.zhicall.hax.bean.NewsSummary;
import com.zhicall.hax.bean.Result;
import java.util.List;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Xingchen on 2016/1/12.
 * Email:huangjinxin@zhicall.cn
 */
public interface INewsService {
  @GET("/tngou/info/classify") @Headers({ "apikey:d99a9ad0a9531fcbd1bb3139651e7249" })
  Observable<Result<List<NewsCategory>>> getNewsCategory();

  @GET("/tngou/info/list") @Headers({ "apikey:d99a9ad0a9531fcbd1bb3139651e7249" })
  Observable<Result<List<NewsSummary>>> getNewsSummary(@Nullable @Query("id") int CategoryId,
      @Nullable @Query("page") int page, @Nullable @Query("rows") int rows);


  @GET("/tngou/info/show") @Headers({ "apikey:d99a9ad0a9531fcbd1bb3139651e7249" })
  Observable<NewsDetail> getNewsDetail(@Query("id") int newsID);
}
