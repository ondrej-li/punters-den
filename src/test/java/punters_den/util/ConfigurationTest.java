package punters_den.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ConfigurationTest {
    @org.junit.Test
    public void testGetDatabaseUsername() throws Exception {
        Configuration.buildConfiguration();
        assertNotNull(Configuration.getDatabaseUsername());
    }

    @org.junit.Test
    public void testGetDatabasePassword() throws Exception {
        Configuration.buildConfiguration();
        assertNotNull(Configuration.getDatabasePassword());
    }

    @org.junit.Test
    public void testGetDatabaseJdbcUrlWithQuery() throws Exception {
        Configuration.buildConfiguration();
        assertTrue(Configuration.getDatabaseJdbcUrl().startsWith("jdbc:"));
    }
}
