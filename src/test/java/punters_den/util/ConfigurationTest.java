package punters_den.util;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    @org.junit.Test
    public void testGetDatabaseUsername() throws Exception {
        Configuration.buildConfiguration();
        assertEquals("punter", Configuration.getDatabaseUsername());
    }

    @org.junit.Test
    public void testGetDatabasePassword() throws Exception {
        Configuration.buildConfiguration();
        assertEquals("Janeka1974", Configuration.getDatabasePassword());
    }

    @org.junit.Test
    public void testGetDatabaseJdbcUrlWithQuery() throws Exception {
        Configuration.buildConfiguration();
        assertEquals("jdbc:mysql://localhost/punter", Configuration.getDatabaseJdbcUrl());
    }

//    public static void set(Map<String, String> newenv) throws Exception {
//        Class[] classes = Collections.class.getDeclaredClasses();
//        Map<String, String> env = System.getenv();
//        for(Class cl : classes) {
//            if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
//                Field field = cl.getDeclaredField("m");
//                field.setAccessible(true);
//                Object obj = field.get(env);
//                Map<String, String> map = (Map<String, String>) obj;
//                map.clear();
//                map.putAll(newenv);
//            }
//        }
//    }
}
