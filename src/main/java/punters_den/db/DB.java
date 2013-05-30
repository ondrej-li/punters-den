package punters_den.db;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import punters_den.util.DatabaseFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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

    public static List<Map<String, Object>> query(String queryString, List queryParameters) throws Exception {
        return new QueryRunner().query(DatabaseFactory.getDataSource().getConnection(), queryString, new MapListHandler(), queryParameters.toArray());
    }

    public static int update(String table, Map<String, ?> values, Map<String, ?> filter) throws Exception {
        String sql = createUpdate(table, values, filter);
        List queryParameters = new ArrayList(values.values());
        if (filter != null) {
            queryParameters.addAll(filter.values());
        }
        return new QueryRunner().update(DatabaseFactory.getDataSource().getConnection(), sql, queryParameters.toArray());
    }

    public static int insert(String table, Map<String, ?> values) throws Exception {
        if (values == null || table == null) {
            return -1;
        }
        String sql = createInsert(table, values);
        PreparedStatement stmt = DatabaseFactory.getDataSource().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int cnt = 1;
        for (String key : values.keySet()) {
            Object value = values.get(key);
            if (value instanceof String) {
                stmt.setString(cnt, (String) value);
            } else if (value instanceof Date) {
                stmt.setTimestamp(cnt, new Timestamp(((Date) value).getTime()));
            } else if (value instanceof Long || value instanceof Integer) {
                stmt.setLong(cnt, (Long) value);
            } else if (value instanceof Double || value instanceof Float) {
                stmt.setDouble(cnt, (Double) value);
            }
            cnt++;
        }
        stmt.execute();
        ResultSet res = stmt.getGeneratedKeys();
        while (res.next()) {
            return res.getInt(1);
        }
        return -1;
    }

    public static int delete(String table, Map<String, ?> filter) throws Exception {
        String sql = createDelete(table, filter);
        List queryParameters = new ArrayList();
        if (filter != null) {
            queryParameters.addAll(filter.values());
        }
        return new QueryRunner().update(DatabaseFactory.getDataSource().getConnection(), sql, queryParameters.toArray());
    }

    static String createUpdate(String table, Map<String, ?> values, Map<String, ?> filter) {
        StringBuffer sql = new StringBuffer("update ").append(table).append(" set ");
        for (String key : values.keySet()) {
            sql.append(key).append(" = ?, ");
        }
        sql.delete(sql.length() - 2, sql.length());
        if (filter != null) {
            sql.append(" where ");
            for (String key : filter.keySet()) {
                sql.append(key).append(" = ? and ");
            }
            sql.delete(sql.length() - 4, sql.length());
        }
        return sql.toString();
    }

    static String createInsert(String table, Map<String, ?> values) {
        if (values == null) {
            return "";
        }
        StringBuffer sql = new StringBuffer("insert into ").append(table).append(" (");
        for (String key : values.keySet()) {
            sql.append(key).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(") values (");
        for (int i = 0; i < values.size(); i++) {
            sql.append("?,");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();
    }

    static String createDelete(String table, Map<String, ?> filter) {
        StringBuffer sql = new StringBuffer("delete ").append(table);
        if (filter != null) {
            sql.append(" where ");
            for (String key : filter.keySet()) {
                sql.append(key).append(" = ? and ");
            }
            sql.delete(sql.length() - 4, sql.length());
        }
        return sql.toString();
    }
}
