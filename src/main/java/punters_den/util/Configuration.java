package punters_den.util;


import java.net.URI;
import java.net.URISyntaxException;

public class Configuration {
    public static class ConfigurationHolder {
        private String databaseUsername;
        private String databasePassword;
        private String DatabaseJdbcUrl;

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
            return DatabaseJdbcUrl;
        }

        public void setDatabaseJdbcUrl(String databaseJdbcUrl) {
            DatabaseJdbcUrl = databaseJdbcUrl;
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
            holder.setDatabaseJdbcUrl("jdbc:mysql://" + dbUri.getHost() + dbUri.getPath() + (dbUri.getQuery() != null ? "?" + dbUri.getQuery() : ""));
            return holder;
        } catch (URISyntaxException e) {
            return null;
        }
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

    public static void setDatabaseUsername(String username) {
        currentConfiguration.setDatabaseUsername(username);
    }

    public static void setDatabasePassword(String password) {
        currentConfiguration.setDatabasePassword(password);
    }

    public static void setDatabaseJdbcUrl(String url) {
        currentConfiguration.setDatabaseJdbcUrl(url);
    }
}
