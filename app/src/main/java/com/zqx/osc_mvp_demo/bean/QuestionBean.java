package com.zqx.osc_mvp_demo.bean;


import java.util.List;

public class QuestionBean extends BaseBean {

    public ResultBean result;

    public static class ResultBean {
        public String nextPageToken;
        public String prevPageToken;
        public int    requestCount;
        public int    responseCount;
        public int    totalResults;

        public List<Item> items;

        public static class Item {
            public String author;
            public int    authorId;
            public String authorPortrait;
            public String body;
            public int    commentCount;
            public int    id;
            public String pubDate;
            public String title;
            public int    type;
            public int    viewCount;

        }
    }
}
