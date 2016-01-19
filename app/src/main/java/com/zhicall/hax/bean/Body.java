package com.zhicall.hax.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Xingchen on 2016/1/19.
 * Email:huangjinxin@zhicall.cn
 */
public class Body implements Serializable {
  String description;
  int id;
  String keywords;
  String name;
  int place;
  int seq;
  String title;
  List<Body> places;            //二级菜单没有

    public String getDescription() {
        return description;
    }

    public String getKeywords() {
        return keywords;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public String getTitle() {
        return title;
    }

    public int getSeq() {
        return seq;
    }

    public List<Body> getPlaces() {
        return places;
    }
}
/*
{
    "status": true,
    "tngou": [
        {
            "description": "头部",
            "id": 1,
            "keywords": "头部",
            "name": "头部",
            "place": 0,
            "places": [
                {
                    "description": "鼻",
                    "id": 2,
                    "keywords": "鼻",
                    "name": "鼻",
                    "place": 1,
                    "seq": 0,
                    "title": "鼻"
                },
                {
                    "description": "耳",
                    "id": 3,
                    "keywords": "耳",
                    "name": "耳",
                    "place": 1,
                    "seq": 0,
                    "title": "耳"
                },
                {
                    "description": "口",
                    "id": 4,
                    "keywords": "口",
                    "name": "口",
                    "place": 1,
                    "seq": 0,
                    "title": "口"
                },
                {
                    "description": "颅脑",
                    "id": 5,
                    "keywords": "颅脑",
                    "name": "颅脑",
                    "place": 1,
                    "seq": 0,
                    "title": "颅脑"
                },
                {
                    "description": "面部",
                    "id": 6,
                    "keywords": "面部",
                    "name": "面部",
                    "place": 1,
                    "seq": 0,
                    "title": "面部"
                },
                {
                    "description": "咽喉",
                    "id": 7,
                    "keywords": "咽喉",
                    "name": "咽喉",
                    "place": 1,
                    "seq": 0,
                    "title": "咽喉"
                },
                {
                    "description": "眼",
                    "id": 8,
                    "keywords": "眼",
                    "name": "眼",
                    "place": 1,
                    "seq": 0,
                    "title": "眼"
                }
            ],
            "seq": 0,
            "title": "头部"
        },..]}
 */