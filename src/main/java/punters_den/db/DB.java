package punters_den.db;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import punters_den.util.DatabaseFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DB {
    public static <T> List<T> query(String queryString, List queryParameters, final Class<T> type) throws Exception {
        return new QueryRunner().query(DatabaseFactory.getDataSource().getConnection(), queryString, new ResultSetHandler<List<T>>() {
            @Override
            public List<T> handle(ResultSet resultSet) throws SQLException {
                return new BeanProcessor().toBeanList(resultSet, type);
            }
        }, queryParameters.toArray());
    }

    public static int update(String table, Map<String, ?> values, Map<String, ?> filter) throws Exception {
        StringBuffer sql = new StringBuffer("update ").append(table).append(" set ");
        for (String key : values.keySet()) {
            sql.append(key).append(" = ?, ");
        }
        sql.deleteCharAt(sql.length());
        if (filter != null) {
            sql.append(" where ");
            for (String key : filter.keySet()) {
                sql.append(key).append(" = ?, ");
            }
        }
        List queryParameters = new ArrayList(values.values());
        if (filter != null) {
            queryParameters.addAll(filter.values());
        }
        return new QueryRunner().update(DatabaseFactory.getDataSource().getConnection(), sql.toString(), queryParameters.toArray());
    }

    public static int insert(String table, Map<String, ?> values) throws Exception {
        StringBuffer sql = new StringBuffer("insert into ").append(table).append(" (");
        if (values != null) {
            sql.append(") values (");
            for (String keys : values.keySet()) {

            }
            sql.append(")");
        }
        PreparedStatement stmt = DatabaseFactory.getDataSource().getConnection().prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
        stmt.execute();
        ResultSet res = stmt.getGeneratedKeys();
        while (res.next()) {
            return res.getInt(1);
        }
        return -1;
    }

    public static int delete(String table, Map<String, ?> filter) {
        return 0;
    }
}
