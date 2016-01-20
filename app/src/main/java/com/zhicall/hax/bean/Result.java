package com.zhicall.hax.bean;

/**
 * Created by Xingchen on 2015/12/8.
 * Email:huangjinxin@zhical.cn
 */
public class Result<T> {
  private boolean status;
  private T tngou;
  private String msg;
  private T list;       //因为第三方返回数据格式不一致不得已使用此方法
  private  int total;

  public int getTotal() {
    return total;
  }

  public T getList() {
    return list;
  }

  public T getTngou() {
    return tngou;
  }

  public boolean isSuccess() {
    return status;
  }

  public String getMsg() {
    return msg;
  }
}
