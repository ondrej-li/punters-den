package punters_den.util;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;

import javax.sql.DataSource;

public class Factory {
    private static final volatile DataSource DATASOURCE;
    
    
    public static DataSource getDataSource() {
        if (DATASOURCE == null) {
            synchronized (Factory.class) {        
                if (DATASOURCE == null) {
                    ComboPooledDataSource DATASOURCE = new ComboPooledDataSource();        
                    DATASOURCE.setUser(Configuration.getDatabaseUsername());
                    DATASOURCE.setPassword(Configuration.getDatabasePassword());
                    DATASOURCE.setJdbcUrl(Configuration.getDatabaseJdbcUrl());
                    DATASOURCE.setDriverClass ("com.mysql.jdbc.Driver");
                    DATASOURCE.setMaxPoolSize(50);
                    DATASOURCE.setMinPoolSize(5);
                    DATASOURCE.setMinPoolSize(5);
                }
            }
        }
        return DATASOURCE;        
    }
}
