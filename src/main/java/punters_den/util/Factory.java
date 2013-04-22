package punters_den.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class Factory {
    private static volatile DataSource DATA_SOURCE;
    
    
    public static DataSource getDataSource() throws Exception {
        if (DATA_SOURCE == null) {
            synchronized (Factory.class) {        
                if (DATA_SOURCE == null) {
                    DATA_SOURCE = new ComboPooledDataSource();
                    ((ComboPooledDataSource) DATA_SOURCE).setUser(Configuration.getDatabaseUsername());
                    ((ComboPooledDataSource) DATA_SOURCE).setPassword(Configuration.getDatabasePassword());
                    ((ComboPooledDataSource) DATA_SOURCE).setJdbcUrl(Configuration.getDatabaseJdbcUrl());
                    ((ComboPooledDataSource) DATA_SOURCE).setDriverClass("com.mysql.jdbc.Driver");
                    ((ComboPooledDataSource) DATA_SOURCE).setMaxPoolSize(50);
                    ((ComboPooledDataSource) DATA_SOURCE).setMinPoolSize(5);
                    ((ComboPooledDataSource) DATA_SOURCE).setMinPoolSize(5);
                }
            }
        }
        return DATA_SOURCE;
    }
}
