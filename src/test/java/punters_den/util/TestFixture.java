package punters_den.util;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import java.sql.SQLException;
import java.sql.Statement;

public class TestFixture extends RunListener {
    @Override
    public void testRunStarted(Description description) throws Exception {
        Configuration.setDatabaseJdbcUrl("jdbc:mysql://localhost/");
        Configuration.setDatabaseUsername("root");
        Configuration.setDatabasePassword("janeka");
        Factory.resetDataSource();
        Statement stmt = Factory.getDataSource().getConnection().createStatement();
        try {
            stmt.executeUpdate("DROP SCHEMA pd_test");
        } catch (SQLException e) {
        }
        stmt.executeUpdate("CREATE SCHEMA IF NOT EXISTS pd_test");
        stmt.executeUpdate("GRANT ALL PRIVILEGES ON pd_test.* TO 'punter_test'@'localhost' IDENTIFIED BY PASSWORD '*7A98434E8AEA299A3BE2FD36282FBE4C69560CE9'");
        Configuration.resetConfiguration();
        Factory.resetDataSource();
        FlywayService.initializeDatabase(Factory.getDataSource(), null);
        FlywayService.initializeDatabase(Factory.getDataSource(), "db/test");
        stmt.close();
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        Configuration.setDatabaseJdbcUrl("jdbc:mysql://localhost/");
        Configuration.setDatabaseUsername("root");
        Configuration.setDatabasePassword("janeka");
        Factory.resetDataSource();
        Statement stmt = Factory.getDataSource().getConnection().createStatement();
        stmt.executeUpdate("DROP SCHEMA pd_test");
        stmt.close();
    }
}
