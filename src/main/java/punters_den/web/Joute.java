package punters_den.web;

import spark.Request;
import spark.Response;
import spark.Route;

import javax.servlet.http.HttpServletResponse;


public abstract class Joute extends Route {

    public Joute(String path) {
        super(path);
    }

    @Override
    public Object handle(Request request, Response response) {
        Jesponse jesponse = initJesponse(request, response);
        response.type("text/json");

        try {
            handle(request, jesponse);
        } catch (Exception e) {
            jesponse.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            jesponse.addData("status", "error");
            jesponse.addData("statusMessage", e.getMessage());
        }
        return jesponse.toResponse();
    }

    protected Jesponse initJesponse(Request request, Response response) {
        Jesponse jesponse = new Jesponse(response);
        jesponse.addData("status", "ok");
        jesponse.addData("statusMessage", "");
        jesponse.addData("action", request.pathInfo());
        return jesponse;
    }

    public abstract void handle(Request request, Jesponse jesponse) throws Exception;


} 