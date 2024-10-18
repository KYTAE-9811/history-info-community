package com.kimyeontae.history_info;

import java.sql.Timestamp;
import java.util.Date;

public class TestPost {
    private int id;
    private String title;
    private String content;
    private String subtitle;
    private Date date;

    public void setDate() {
        this.date = new Timestamp(System.currentTimeMillis());
    }
    public TestPost(String content, int id, String subtitle, String title) {
        this.content = content;
        this.id = id;
        this.subtitle = subtitle;
        this.title = title;
    }
}
