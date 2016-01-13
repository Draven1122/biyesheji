package com.zhicall.hax.bean;

import com.google.gson.GsonBuilder;

/**
 * Created by Xingchen on 2016/1/11.
 * Email:huangjinxin@zhicall.cn
 */
public class MedicalCategory {

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
/*
* "description": "性病用药,性病用药药品,性病用药相关药品,治疗性病用药相关功能的药品,性病用药相关药品查询,性病用药药品库",
            "drugclass": 0,
            "id": 1,
            "keywords": "性病用药",
            "name": "性病用药",
            "seq": 0,
            "title": "性病用药"
        }
 */