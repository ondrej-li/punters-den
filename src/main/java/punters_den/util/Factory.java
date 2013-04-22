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
//    (let [cpds (doto (ComboPooledDataSource.)
//    (.setDriverClass (:classname config))
//            (.setJdbcUrl (str "jdbc:" (:subprotocol config) ":" (:subname config)))
//            (.setUser (:user config))
//            (.setPassword (:password config))
//            (.setMaxPoolSize 6)
//            (.setMinPoolSize 1)
//            (.setInitialPoolSize 1))]
}
