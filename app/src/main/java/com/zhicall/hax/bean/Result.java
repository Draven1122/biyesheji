package com.zhicall.hax.bean;

import java.util.List;

/**
 * Created by Xingchen on 2015/12/8.
 * Email:huangjinxin@zhical.cn
 */
public class Result<T> {
  private int status;
  private List<T> tngou;
  private String msg;

  public int getStatus() {
    return status;
  }

  public List<T> getTngou() {
    return tngou;
  }

  public String getMsg() {
    return msg;
  }
}
