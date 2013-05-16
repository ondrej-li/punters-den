package punters_den.web;

import spark.Request;
import spark.Response;
import spark.Route;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;


public abstract class Joute extends Route {
    
    @Override
    public Object handle(Request request, Response response) {
        Jesponse jesponse = new Jesponse(response);
        response.type("text/json");
        
        try {
            jesponse = handle(request, jesponse);
        } catch (Exception e) {
            jesponse.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            jesponse.addData("status", "error");
            jesponse.addData("statusMessage", e.getMessage());
        }
        return jesponse.toResponse().body();
    }    
    
    protected Map<String, ?> initJesponse(Request request, Response response) {
            Jesponse jesponse = new Jesponse(response);
            jesponse.addData("status", "ok");
            jesponse.addData("statusMessage", "");
            jesponse.addData("action", request.path_info());
    }
    
    public abstract void handle (Request request, Jesponse jesponse);
    
    
} 