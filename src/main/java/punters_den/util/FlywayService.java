package punters_den.util;

import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.api.FlywayException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

public class FlywayService {
    private static Logger LOGGER = Logger.getLogger(FlywayService.class);

    public static void initializeDatabase(DataSource ds, String location) {
        Flyway flyway = new Flyway();
        try {
            flyway.setDataSource(ds);
        } catch (Exception e) {
            LOGGER.error("Error getting datasource.", e);
        }
        flyway.setLocations(location != null ? location : "db");
        try {
            flyway.init();
        } catch (FlywayException e) {
            LOGGER.debug("Error initializing database - is database already initialized?", e);
        }
        try {
            flyway.repair();
        } catch (FlywayException e) {
            LOGGER.debug("Error repairing database - is database already repaired?", e);
        }
        try {
            flyway.migrate();
        } catch (FlywayException e) {
            e.printStackTrace();
            LOGGER.fatal("Failed migrating database - try to fix it later.", e);
        }
    }
}
