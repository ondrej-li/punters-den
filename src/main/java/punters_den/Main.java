package punters_den;

import punters_den.user.UserService;
import punters_den.util.Configuration;
import punters_den.util.DatabaseFactory;
import punters_den.util.FlywayService;

import static spark.Spark.setPort;
import static spark.Spark.staticFileLocation;

public class Main {
    public static void main(String[] args) throws Exception {
        setPort(System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 5000);
        initializeDatabase();
        staticFileLocation("/content");
        new UserService().addRoutes();
    }

    private static void initializeDatabase() throws Exception {
        Configuration.resetConfiguration();
        DatabaseFactory.resetDataSource();
        FlywayService.initializeDatabase(DatabaseFactory.getDataSource(), null);
    }
}
