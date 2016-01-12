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
  /*
  说明：img字段返回的是不完整的图片路径src，
  需要在前面添加【http://tnfs.tngou.net/image】或者【http://tnfs.tngou.net/img】
  前者可以再图片后面添加宽度和高度，如：http://tnfs.tngou.net/image/top/default.jpg_180x120
  详情请参考：http://www.tngou.net/doc/info
   */
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
/*
{
    "status": true,
    "total": 126,
    "tngou": [
        {
            "count": 50,
            "description": "九成前列腺癌可筛查发现 生存率高达90%以上 在欧美发达国家，大部分前列腺癌病人在早期得到了及时发现和诊断，这个阶段的病人往往没有任何不舒服；而我国大部分病人往往直到有排尿困难、骨痛等不舒服时才去看医生，大多已出现转移，失去了治愈的机会",
            "fcount": 0,
            "id": 6287,
            "img": "/info/160109/57bf25ffdfbace9c29a711f1858cf12e.jpg",
            "infoclass": 7,
            "keywords": "前列腺癌 前列腺特异性抗原 前列腺 数值 诊断 ",
            "rcount": 0,
            "time": 1452300900000,
            "title": "前列腺癌病人在早期得到了及时发现和诊断"
        },...]
}
 */