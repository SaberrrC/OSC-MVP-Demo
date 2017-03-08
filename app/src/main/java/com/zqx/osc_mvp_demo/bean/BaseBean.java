package com.zqx.osc_mvp_demo.bean;

/**
 * 请求发生错误时的信息
 */
public class BaseBean {
    public int    code;
    public String message;
    public Notice notice;
    public String time;

    public static class Notice {
        public int fans;
        public int letter;
        public int like;
        public int mention;
        public int review;
    }
}
