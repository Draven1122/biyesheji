package com.zhicall.hax.bean;

/**
 * Created by Xingchen on 2015/12/8.
 * Email:huangjinxin@zhical.cn
 */
public class Course {
  private int id;
  private String name;
  private String picSmall;
  private String picBig;
  private String description;
  private long learner;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPicSmall() {
    return picSmall;
  }

  public String getPicBig() {
    return picBig;
  }

  public String getDescription() {
    return description;
  }

  public long getLearner() {
    return learner;
  }
}
/*
            "id": 1,
            "name": "Tony老师聊shell——环境变量配置文件",
            "picSmall": "http://img.mukewang.com/55237dcc0001128c06000338-300-170.jpg",
            "picBig": "http://img.mukewang.com/55237dcc0001128c06000338.jpg",
            "description": "为你带来shell中的环境变量配置文件",
            "learner": 12312l
 */