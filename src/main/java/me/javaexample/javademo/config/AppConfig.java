package me.javaexample.javademo.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * config.json like bellow { "dbUserName": "ssss" "dbUserPassword": "bbbbb" } if field name is conflict like "userName(in environment)", it
 * cannot be set upper
 */
@Log4j2
@Configuration
@PropertySource(value = "file:/var/config/config.json", factory = JsonPropertiesParseFactory.class, ignoreResourceNotFound = true)
public class AppConfig {

    private String userName;
    private String userPassword;

    @Autowired
    Environment environment;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        this.userName = environment.getProperty("dbUserName");
        this.userPassword = environment.getProperty("dbUserPassword");

        if (dataSource instanceof HikariDataSource) {
            HikariDataSource hikariDataSource = (HikariDataSource) dataSource;

            // Get connection pool information
            int maximumPoolSize = hikariDataSource.getHikariConfigMXBean().getMaximumPoolSize();
            int idleConnections = hikariDataSource.getHikariPoolMXBean().getIdleConnections();

            log.info("hikari max pool size: " + maximumPoolSize + ", idleConnections: " + idleConnections);
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
