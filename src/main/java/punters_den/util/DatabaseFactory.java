package punters_den.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class DatabaseFactory {
    private static volatile DataSource DATA_SOURCE;
    private static Logger logger = Logger.getLogger(DatabaseFactory.class);

    public static DataSource getDataSource() throws Exception {
        if (DATA_SOURCE == null) {
            synchronized (DatabaseFactory.class) {
                if (DATA_SOURCE == null) {
                    DATA_SOURCE = new ComboPooledDataSource();
                    ((ComboPooledDataSource) DATA_SOURCE).setUser(Configuration.getDatabaseUsername());
                    ((ComboPooledDataSource) DATA_SOURCE).setPassword(Configuration.getDatabasePassword());
                    ((ComboPooledDataSource) DATA_SOURCE).setJdbcUrl(Configuration.getDatabaseJdbcUrl());
                    ((ComboPooledDataSource) DATA_SOURCE).setDriverClass("com.mysql.jdbc.Driver");
                    ((ComboPooledDataSource) DATA_SOURCE).setMaxPoolSize(50);
                    ((ComboPooledDataSource) DATA_SOURCE).setMinPoolSize(5);
                    ((ComboPooledDataSource) DATA_SOURCE).setMaxIdleTimeExcessConnections(1800);
                    ((ComboPooledDataSource) DATA_SOURCE).setMaxIdleTime(1800);
                    ((ComboPooledDataSource) DATA_SOURCE).setMaxConnectionAge(3600);
                    testConnection();
                }
            }
        }
        return DATA_SOURCE;
    }

    public static void resetDataSource() {
        DATA_SOURCE = null;
        return;
    }

    private static void testConnection() throws Exception {
        logger.info("Connection DB:" + DatabaseFactory.getDataSource().getConnection().getMetaData().getDatabaseProductName());
    }
}
