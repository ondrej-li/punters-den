package punters_den.util;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    @org.junit.Test
    public void testGetDatabaseUsername() throws Exception {
        Configuration.buildConfiguration();
        assertEquals("punter_test", Configuration.getDatabaseUsername());
    }

    @org.junit.Test
    public void testGetDatabasePassword() throws Exception {
        Configuration.buildConfiguration();
        assertEquals("Janeka1974", Configuration.getDatabasePassword());
    }

    @org.junit.Test
    public void testGetDatabaseJdbcUrlWithQuery() throws Exception {
        Configuration.buildConfiguration();
        assertEquals("jdbc:mysql://localhost/pd_test", Configuration.getDatabaseJdbcUrl());
    }
}
