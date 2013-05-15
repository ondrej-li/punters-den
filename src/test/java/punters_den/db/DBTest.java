package punters_den.db;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Map;
import java.util.HashMap;

public class DBTest {
    @org.junit.Test
    public void testCreateUpdateWithFilters() throws Exception {
        Map values = new HashMap();
        values.put("username", "test");
        Map filter = new HashMap();
        filter.put("id", 1);
        String sql = DB.createUpdate("user", values, filter);
        assertTrue (sql.startsWith("update user set username = ? where id = ?"));
    }
    
    @org.junit.Test
    public void testCreateInsert() throws Exception {
        Map values = new HashMap();
        values.put("username", "test");
        values.put("password", "test");
        String sql = DB.createInsert("user", values);
        assertTrue (sql.startsWith("insert into user (username,password) values (?,?)"));
    }
    
    @org.junit.Test
    public void testCreateDeleteWithFilters() throws Exception {
        Map filter = new HashMap();
        filter.put("id", 1);
        String sql = DB.createDelete("user", filter);
        assertTrue (sql.startsWith("delete user where id = ?"));
    }    
}