package com.yuzlink.app.db;

import java.util.List;

public interface Model {


    String shorten(String url);
    String redirect(String key);
    void click(String key);
    List<UrlDto> getUrls(Long userID, int limit, int offset);
}
