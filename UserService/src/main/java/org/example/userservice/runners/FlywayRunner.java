package org.example.userservice.runners;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author Tribushko Danil
 * @since 06.12.2024
 * Раннер для запуска мигация flyway
 */
@Component
public class FlywayRunner implements CommandLineRunner {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public void run(String... args) throws Exception {
        DataSource dataSource = DataSourceBuilder.create()
                .url(url)
                .driverClassName(driver)
                .username(username)
                .password(password)
                .build();

        Flyway.configure()
                .baselineOnMigrate(true)
                .dataSource(dataSource)
                .baselineVersion("0")
                .load()
                .migrate();
    }
}
