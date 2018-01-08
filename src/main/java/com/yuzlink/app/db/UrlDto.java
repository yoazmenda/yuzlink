package com.yuzlink.app.db;

import java.sql.Timestamp;

public class UrlDto {

    public String id;
    public String url;
    public Long clicks;
    public Timestamp created;

    public UrlDto(String id, String url, Long clicks, Timestamp created) {
        this.id = id;
        this.url = url;
        this.clicks = clicks;
        this.created = created;
    }
}
