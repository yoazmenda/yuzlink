package com.klix.app.services.link;

import com.klix.app.db.Model;
import com.klix.app.services.AbstractHandler;
import org.apache.commons.validator.routines.UrlValidator;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class ShortenHandler extends AbstractHandler {


    private UrlValidator urlValidator;
    private String host;
    private int port;

    public ShortenHandler(Model model, String host, int port) {
        super(model);
        this.host = host;
        this.port = port;
        String[] schemes = {"http", "https"};
        urlValidator = new UrlValidator(schemes);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String longUrl = request.queryParams("textBox");
        if (longUrl == null || longUrl.equals("")) halt(HttpStatus.BAD_REQUEST_400, "Must supply a url");
        if (!longUrl.startsWith("http://") && !(longUrl.startsWith("https://"))) longUrl = "http://" + longUrl;
        if (!urlValidator.isValid(longUrl)) halt(HttpStatus.BAD_REQUEST_400, "URL not valid");
        String shortKey = model.shorten(longUrl);
        String link = "klix.herokuapp.com" + "/" + shortKey;
        String html = "" +
                "<div>" +
                "<center>Here is your new link:<br><br>" +
                "<a href=//" + link + ">" +
                link +
                "</a>" +
                "</center>" +
                "</div>";
        return html;
    }
}
