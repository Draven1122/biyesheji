package com.zhicall.hax.net;

import com.zhicall.hax.bean.Course;
import com.zhicall.hax.bean.Result;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Xingchen on 2015/12/8.
 * Email:huangjinxin@zhical.cn
 */
public interface CourseService {
  @GET("/api/teacher")
  Observable<Result<Course>> getCourseList(@Query("type") int type,
      @Query("num") int num);
}
/*
?type=4&num=30
 */