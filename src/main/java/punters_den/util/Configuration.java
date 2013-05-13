package punters_den.util;


import java.net.URI;
import java.net.URISyntaxException;

public class Configuration {
    public static class ConfigurationHolder {
        private String databaseUsername;
        private String databasePassword;
        private String databaseJdbcUrl;
        private String datasourceClass;
        

        public String getDatabaseUsername() {
            return databaseUsername;
        }

        public void setDatabaseUsername(String databaseUsername) {
            this.databaseUsername = databaseUsername;
        }

        public String getDatabasePassword() {
            return databasePassword;
        }

        public void setDatabasePassword(String databasePassword) {
            this.databasePassword = databasePassword;
        }

        public String getDatabaseJdbcUrl() {
            return databaseJdbcUrl;
        }

        public void setDatabaseJdbcUrl(String databaseJdbcUrl) {
            this.databaseJdbcUrl = databaseJdbcUrl;
        }
        
        public void setDatasourceClass (String datasourceClass) {
            this.datasourceClass = datasourceClass;
        }
        
        public String getDatasourceClass () {
            return datasourceClass;
        }
    }

    private static ConfigurationHolder currentConfiguration;

    static {
        resetConfiguration();
    }

    public static synchronized ConfigurationHolder buildConfiguration() {
        try {
            ConfigurationHolder holder = new ConfigurationHolder();
            URI dbUri = new URI(System.getProperty("CLEARDB_DATABASE_URL", System.getenv("CLEARDB_DATABASE_URL")));
            holder.setDatabaseUsername(dbUri.getUserInfo().split(":")[0]);
            holder.setDatabasePassword(dbUri.getUserInfo().split(":")[1]);
            holder.setDatabaseJdbcUrl(buildURL(dbUri));
            holder.setDatasourceClass("h2".equalsIgnoreCase(dbUri.getScheme()) ? "org.h2.Driver" : "com.mysql.jdbc.Driver");
            return holder;
        } catch (URISyntaxException e) {
            return null;
        }
    }
    
    private static String buildURL (URI dbUri) {
        String url = "";
        if ("h2".equalsIgnoreCase(dbUri.getScheme())) {
            url = "jdbc:" + dbUri.getScheme() + ":mem:" + (dbUri.getQuery() != null ? "?" + dbUri.getQuery() : "") + ";MODE=MySQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
        } else if ("mysql".equalsIgnoreCase(dbUri.getScheme() )) {
            url = "jdbc:" + dbUri.getScheme() + "://" + dbUri.getHost() + dbUri.getPath() + (dbUri.getQuery() != null ? "?" + dbUri.getQuery() : "");
        }
        return url;
    }

    public static void resetConfiguration() {
        currentConfiguration = buildConfiguration();
    }

    public static String getDatabaseUsername() {
        return currentConfiguration.getDatabaseUsername();
    }

    public static String getDatabasePassword() {
        return currentConfiguration.getDatabasePassword();
    }

    public static String getDatabaseJdbcUrl() {
        return currentConfiguration.getDatabaseJdbcUrl();
    }

    public static String getDatasourceClass() {
        return currentConfiguration.getDatasourceClass();
    }

    public static void setDatabaseUsername(String username) {
        currentConfiguration.setDatabaseUsername(username);
    }

    public static void setDatabasePassword(String password) {
        currentConfiguration.setDatabasePassword(password);
    }

    public static void setDatabaseJdbcUrl(String url) {
        currentConfiguration.setDatabaseJdbcUrl(url);
    }   

    public static void setDatasourceClass(String datasourceClass) {
        currentConfiguration.setDatasourceClass(datasourceClass);
    }   
}
