package com.zhicall.hax.bean;

/**
 * Created by Xingchen on 2016/1/12.
 * Email:huangjinxin@zhicall.cn
 */
public class NewsDetail {
  int count;
  String description;
  int fcount;
  int id;
  String img;
  int infoclass;
  String keywords;
  String message;
  int rcount;
  boolean status;
  long time;
  String title;
  String url;

  public int getCount() {
    return count;
  }

  public String getDescription() {
    return description;
  }

  public int getFcount() {
    return fcount;
  }

  public int getId() {
    return id;
  }

  public String getImg() {
    return img;
  }

  public int getInfoclass() {
    return infoclass;
  }

  public String getKeywords() {
    return keywords;
  }

  public int getRcount() {
    return rcount;
  }

  public String getMessage() {
    return message;
  }

  public long getTime() {
    return time;
  }

  public String getTitle() {
    return title;
  }

  public String getUrl() {
    return url;
  }
  public boolean isSuccess(){
    return status;
  }
}