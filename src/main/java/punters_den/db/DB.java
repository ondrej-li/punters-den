package punters_den.db;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import punters_den.util.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DB {
    public static <T> List<T> query(String queryString, List queryParameters, final Class<T> type) throws Exception {
        return new QueryRunner().query(Factory.getDataSource().getConnection(), queryString, new ResultSetHandler<List<T>>() {
            @Override
            public List<T> handle(ResultSet resultSet) throws SQLException {
                return new BeanProcessor().toBeanList(resultSet, type);
            }
        }, queryParameters.toArray());
    }
}
