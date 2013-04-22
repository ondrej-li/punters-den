package punters_den.util;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

public class Factory {
    public static DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setUser(Configuration.getDatabaseUsername());
        dataSource.setPassword(Configuration.getDatabasePassword());
        dataSource.setURL(Configuration.getDatabaseJdbcUrl());
        return dataSource;
    }
}
