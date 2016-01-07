package com.zhicall.hax.net;

import retrofit.RestAdapter;

/**
 * Created by Xingchen on 2015/12/8.
 * Email:huangjinxin@zhical.cn
 */
public class Http {
  private static RestAdapter adapter=new RestAdapter.Builder().setEndpoint("http://www.imooc.com").build();
/*unchecked*/
  public static CourseService courseService() {
    return adapter.create(CourseService.class);
  }
}
/*
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://api.github.com")
    .build();

GitHubService service = retrofit.create(GitHubService.class);
 */