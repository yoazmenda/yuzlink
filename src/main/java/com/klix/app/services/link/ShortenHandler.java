package com.klix.app.services.link;

import com.klix.app.db.Model;
import com.klix.app.services.AbstractHandler;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class ShortenHandler extends AbstractHandler {

    public ShortenHandler(Model model) {
        super(model);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String longUrl = request.queryParams("toShort");
        if (longUrl == null || longUrl.equals("")) halt(HttpStatus.BAD_REQUEST_400, "Must supply a url");
        return model.shorten(longUrl);
    }
}
