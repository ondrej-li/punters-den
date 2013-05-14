package punters_den.user;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.get;

public class UserService {
    public void addRoutes() {
        get(new Route("/user/:username/login") {
            @Override
            public Object handle(Request request, Response response) {
                return "";
            }
        });
        get(new Route("/user/:username/logout") {
            @Override
            public Object handle(Request request, Response response) {
                return "";
            }
        });
    }
}
