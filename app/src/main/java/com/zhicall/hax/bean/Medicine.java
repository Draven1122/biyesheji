package com.zhicall.hax.bean;

import java.io.Serializable;

/**
 * Created by Xingchen on 2016/1/14.
 * Email:huangjinxin@zhicall.cn
 */
public class Medicine implements Serializable {
  String description;
  int fcount;
  int id;
  String img;
  String keywords;
  String name;
  double price;
  int rcount;
  String tag;
  String type;

  public String getTag() {
    return tag;
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

  public String getKeywords() {
    return keywords;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  public int getRcount() {
    return rcount;
  }

  public String getType() {
    return type;
  }
}