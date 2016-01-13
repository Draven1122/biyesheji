package com.zhicall.hax.net;

import com.zhicall.hax.bean.Test;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by draven on 2016/1/13.
 * E-mail:draven1122@163.com
 */
public interface TestService {
    @GET("/MyApp/test")
    Observable<Test> getTest(@Query("op") String op);
}
