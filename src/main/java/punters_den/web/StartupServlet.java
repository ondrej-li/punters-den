package punters_den.web;

import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.api.FlywayException;
import org.apache.log4j.Logger;
import punters_den.util.Factory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class StartupServlet extends HttpServlet {
    Logger logger = Logger.getLogger(StartupServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.initializeDatabase();
    }

    private void initializeDatabase() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(Factory.getDataSource());
        flyway.setLocations("db");
        try {
            flyway.init();
        } catch (FlywayException e) {
            logger.warn("Error initializing database - is database already initialized?", e);
        }
        flyway.migrate();
    }
}
