package com.yuzlink.app.db;

import com.yuzlink.app.utils.Utils;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class YuzLink implements Model {


    private final Sql2o sql2o;

    public YuzLink(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public String shorten(String longURL) {
        try (Connection conn = sql2o.beginTransaction()) {


            //0. is exist?
            String shortKey = conn.createQuery("SELECT id from links where url = '" + longURL + "'")
                    .executeScalar(String.class);
            if (shortKey != null && !shortKey.isEmpty()) {
                return shortKey;
            }

            //1. get next value from counter
            Long nextVal = conn.createQuery("SELECT nextval('short_key_seq');").executeAndFetchFirst(Long.class);
            shortKey = Utils.keyToBase62(nextVal);

            //2. SELECT USER ID (currently only guest is supported)
            Long userID = conn.createQuery("SELECT id from users where user_metadata_id = 'guest'").executeAndFetchFirst(Long.class);

            //3. create link metadata
            Long linkMetadataID = conn.createQuery("INSERT INTO link_metadata (clicks, created, user_id) VALUES (:clicks, CURRENT_TIMESTAMP, :userID)", true)
                    .addParameter("clicks", 0)
                    .addParameter("userID", userID)
                    .executeUpdate()
                    .getKey(Long.class);


            //4. create link
            String shortKeyResult = conn.createQuery("INSERT INTO links (id, url, link_metadata_id) VALUES (:linkID, :longURL, :linkMetadataID)", true)

                    .addParameter("linkID", shortKey)
                    .addParameter("longURL", longURL)
                    .addParameter("linkMetadataID", linkMetadataID)
                    .executeUpdate().getKey(String.class);


            conn.commit();

            return shortKeyResult;
        }
    }

    @Override
    public String redirect(String key) {
        try (Connection conn = sql2o.open()) {


            //0. is exist?
            String longURL = conn.createQuery("SELECT url from links where id = '" + key + "'")
                    .executeScalar(String.class);
            if (longURL != null && !longURL.isEmpty()) {
                return longURL;
            }
            return null;
        }
    }

    @Override
    public void click(String key) {
        try (Connection conn = sql2o.beginTransaction()) {

            Long linkMetadata = conn.createQuery(
                    "SELECT link_metadata_id FROM links where links.id = '" + key + "'")
            .executeScalar(Long.class);

            conn.createQuery("Update link_metadata Set clicks = clicks + 1 where id = " + String.valueOf(linkMetadata))
                    .executeUpdate();

            conn.commit();
        }
    }
}
