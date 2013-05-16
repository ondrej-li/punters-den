package punters_den;

import punters_den.user.UserService;
import punters_den.util.Configuration;
import punters_den.util.DatabaseFactory;
import punters_den.util.FlywayService;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) throws Exception {
        setPort(System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 5000);
        initializeDatabase();
        staticFileLocation("/content");
        get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                response.redirect("/html/index.html");
                halt();
                return null;
            }
        });
        new UserService().addRoutes();
    }

    private static void initializeDatabase() throws Exception {
        Configuration.resetConfiguration();
        DatabaseFactory.resetDataSource();
        FlywayService.initializeDatabase(DatabaseFactory.getDataSource(), null);
    }
}
