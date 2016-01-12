package com.zhicall.hax.bean;

/**
 * Created by Xingchen on 2016/1/12.
 * Email:huangjinxin@zhicall.cn
 */
public class NewsCategory {
  String description;
  int id;
  String keywords;
  String name;
  int seq;
  String title;

  public String getDescription() {
    return description;
  }

  public String getKeywords() {
    return keywords;
  }

  public int getId() {
    return id;
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
{
    "status": true,
    "tngou": [
        {
            "description": "社会热点，健康资讯，综合健康资讯,生活热点新闻,社会热点新闻,社会热点查询,提供最新的健康资讯,社会热点新闻网",
            "id": 6,
            "keywords": "社会热点",
            "name": "社会热点",
            "seq": 1,
            "title": "社会热点"
        },
        ...]
}
 */