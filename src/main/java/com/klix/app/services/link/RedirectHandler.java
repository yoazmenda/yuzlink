package com.klix.app.services.link;

import com.klix.app.db.Model;
import com.klix.app.services.AbstractHandler;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class RedirectHandler extends AbstractHandler {

    private String host;
    private int port;

    public RedirectHandler(Model model, String host, int port) {
        super(model);

        this.host = host;
        this.port = port;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String key = request.params("id");
        if (key == null || key.equals("")) halt(HttpStatus.BAD_REQUEST_400, "Must supply a url");
        String redirectURL = model.redirect(key);
        if (redirectURL == null) {
            response.status(HttpStatus.NOT_FOUND_404);
            response.body("Sorry.. url not found");
            return "Sorry.. url not found";
        }
        response.status(302); // temp redirect - prevent browser cache to gain stats
        response.header("Location", redirectURL);
        model.click(key);
        return response;
    }
}
