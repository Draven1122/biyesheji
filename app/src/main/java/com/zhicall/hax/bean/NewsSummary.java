package com.zhicall.hax.bean;

import java.io.Serializable;

/**
 * Created by Xingchen on 2016/1/12.
 * Email:huangjinxin@zhicall.cn
 */
public class NewsSummary implements Serializable {
  int count;
  String description;
  int fcount;
  int id;
  String img;
  int infoclass;
  String keywords;
  int rcount;
  long time;
  String title;

  public int getCount() {
    return count;
  }

  public int getFcount() {
    return fcount;
  }

  public String getDescription() {
    return description;
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

  public long getTime() {
    return time;
  }

  public String getTitle() {
    return title;
  }
}