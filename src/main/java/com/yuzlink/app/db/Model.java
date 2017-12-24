package com.yuzlink.app.db;

public interface Model {


    String shorten(String url);
    String redirect(String key);

    void click(String key);
}
