package com.klix.app.db;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class KlixModel implements Model {


    private final Sql2o sql2o;

    public KlixModel(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public String shorten(String url) {
//        try (Connection conn = sql2o.beginTransaction()) {



/*
            conn.createQuery("insert into posts(post_uuid, title, content, publishing_date) VALUES (:post_uuid, :title, :content, :date)")
                    .addParameter("post_uuid", postUuid)
                    .addParameter("title", title)
                    .addParameter("content", content)
                    .addParameter("date", new Date())
                    .executeUpdate();




            categories.forEach((category) ->
                    conn.createQuery("insert into posts_categories(post_uuid, category) VALUES (:post_uuid, :category)")
                            .addParameter("post_uuid", postUuid)
                            .addParameter("category", category)
                            .executeUpdate());



            conn.commit();*/
            return url;
//        }
    }

    @Override
    public String redirect(String key) {
        return "your long url";
    }
}
