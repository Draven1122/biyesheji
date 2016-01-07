package com.zhicall.hax.bean;

/**
 * Created by Xingchen on 2015/12/3.
 * Email:huangjinxin@zhical.cn
 */
public class Bean {
  private String name;
  private String time;

  public Bean(String time, String name) {
    this.time = time;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }
}
