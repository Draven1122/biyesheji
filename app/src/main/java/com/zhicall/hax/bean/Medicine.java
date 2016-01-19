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
  String message;

  public String getMessage() {
    return message;
  }

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
/*
            "description": "骨癌",
            "fcount": 0,
            "id": 12356,
            "img": "/drug/081010/d1d4f40d981a65a3072bb7ab6eb1ab5f.jpg",
            "keywords": "多发性骨髓瘤 综合症 名称 商品名称 英文名称 ",
            "name": "来那度胺胶囊",
            "price": 0,
            "rcount": 0,
            "tag": "骨癌",
            "type": "化学药品"
 */