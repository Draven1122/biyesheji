package com.zhicall.hax.bean;


import java.io.Serializable;

/**
 * Created by Xingchen on 2016/1/11.
 * Email:huangjinxin@zhicall.cn
 */
public class MedicalCategory implements Serializable {

  String description;
  int drugclass;
  int id;
  String keywords;
  String name;
  int seq;
  String title;

  public String getDescription() {
    return description;
  }

  public int getDrugclass() {
    return drugclass;
  }

  public int getId() {
    return id;
  }

  public String getKeywords() {
    return keywords;
  }

  public int getSeq() {
    return seq;
  }

  public String getName() {
    return name;
  }

  public String getTitle() {
    return title;
  }
}