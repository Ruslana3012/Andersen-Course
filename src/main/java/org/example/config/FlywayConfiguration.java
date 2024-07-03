package org.example.config;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FlywayConfiguration {
    public static void applyDbMigrations() throws IOException {
        Properties properties = new Properties();
        InputStream input = FlywayConfiguration.class.getClassLoader().getResourceAsStream("database.properties");
        properties.load(input);

        Flyway flyway = Flyway.configure()
                .dataSource(
                        properties.getProperty("database.url"),
                        properties.getProperty("database.user"),
                        properties.getProperty("database.password"))
                .locations("classpath:db/migration")
                .cleanDisabled(false)
                .baselineOnMigrate(true)
                .baselineVersion("0.5")
                .load();
        flyway.migrate();
    }
}
