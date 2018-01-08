package com.yuzlink.app.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuzlink.app.db.Model;
import com.yuzlink.app.db.UrlDto;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.List;

import static com.yuzlink.app.utils.Utils.writeListToJsonArray;
import static spark.Spark.halt;

public class GetUrlsHandler extends AbstractHandler {

    public GetUrlsHandler(Model model) {
        super(model);
    }

    @Override
    public Object handle(Request request, Response response) throws IOException {
        String limit = request.queryParams("limit");
        String offset = request.queryParams("offset");
        String userID = request.params("userID");
        if (userID == null || userID.equals("")) halt(HttpStatus.BAD_REQUEST_400, "Must supply user id");
        if (limit == null || limit.equals("")) halt(HttpStatus.BAD_REQUEST_400, "Must supply a limit");
        if (offset == null || offset.equals("")) halt(HttpStatus.BAD_REQUEST_400, "Must supply a offset");
        Integer limitVal = 0;
        Integer offsetVal = 0;
        Long userIdVal = 0l;
        try {
            limitVal = Integer.valueOf(limit);
            offsetVal = Integer.valueOf(offset);
            userIdVal = Long.valueOf(userID);
        } catch (NumberFormatException e) {
            halt(HttpStatus.BAD_REQUEST_400, "Must supply user id, limit and offset integers");
        }
        List<UrlDto> result = model.getUrls(userIdVal, limitVal, offsetVal);
        return writeListToJsonArray(result);

    }
}
