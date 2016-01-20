package com.zhicall.hax.net;

import android.support.annotation.Nullable;
import com.zhicall.hax.bean.Body;
import com.zhicall.hax.bean.Disease;
import com.zhicall.hax.bean.MedicalCategory;
import com.zhicall.hax.bean.Medicine;
import com.zhicall.hax.bean.Result;
import java.util.List;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

/**
 * 医疗信息相关类都在这里(疾病药品等)
 * Created by Xingchen on 2016/1/11.
 * Email:huangjinxin@zhicall.cn
 */
public interface IMedicalService {
  @GET("/tngou/drug/classify") @Headers({ "apikey:d99a9ad0a9531fcbd1bb3139651e7249" })
  Observable<Result<List<MedicalCategory>>> category(@Nullable @Query("id") int id);

  @GET("/tngou/drug/list") @Headers({ "apikey:d99a9ad0a9531fcbd1bb3139651e7249" })
  Observable<Result<List<Medicine>>> medicineList(@Nullable @Query("id") int id,
      @Query("page") int page, @Query("rows") int pagesize);

  @GET("/tngou/drug/show") @Headers({ "apikey:d99a9ad0a9531fcbd1bb3139651e7249" })
  Observable<Medicine> medicineDetail(@Query("id") int id);

  @GET("/tngou/disease/pclassify") @Headers({ "apikey:d99a9ad0a9531fcbd1bb3139651e7249" })
  Observable<Result<List<Body>>> body();

  @GET("/tngou/disease/place") @Headers({ "apikey:d99a9ad0a9531fcbd1bb3139651e7249" })
  Observable<Result<List<Disease>>> diseaseByBody(@Nullable @Query("id") int id,
      @Query("page") int page, @Query("rows") int pagesize);

  @GET("/tngou/disease/name") @Headers({ "apikey:d99a9ad0a9531fcbd1bb3139651e7249" })
  Observable<Disease> searchDisease(@Query("name") String name);
}
