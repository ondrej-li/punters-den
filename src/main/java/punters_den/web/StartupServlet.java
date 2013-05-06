package punters_den.web;

import org.apache.log4j.Logger;
import punters_den.util.Configuration;
import punters_den.util.Factory;
import punters_den.util.FlywayService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class StartupServlet extends HttpServlet {
    Logger logger = Logger.getLogger(StartupServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            this.initializeDatabase();
        } catch (Exception e) {
            throw new ServletException("Exception during database init.", e);
        }
    }

    private void initializeDatabase() throws Exception {
        Configuration.resetConfiguration();
        Factory.resetDataSource();
        FlywayService.initializeDatabase(Factory.getDataSource(), null);
    }
}
