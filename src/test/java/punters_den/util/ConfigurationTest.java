package punters_den.util;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    @org.junit.Test
    public void testGetDatabaseUsername() throws Exception {
        Configuration.buildConfiguration();
        assertEquals("b09319f594b2a1", Configuration.getDatabaseUsername());
    }

    @org.junit.Test
    public void testGetDatabasePassword() throws Exception {
        Configuration.buildConfiguration();
        assertEquals("935ee425", Configuration.getDatabasePassword());
    }

    @org.junit.Test
    public void testGetDatabaseJdbcUrl() throws Exception {
        Configuration.buildConfiguration();
        assertEquals("jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_c388e160fbabe96?reconnect=true", Configuration.getDatabaseJdbcUrl());
    }
}
