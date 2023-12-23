package me.javaexample.javademo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * config.json like bellow
 * {
 *     "dbUserName": "ssss"
 *     "dbUserPassword": "bbbbb"
 * }
 * if field name is conflict like "userName(in environment)", it cannot be set upper
 */
@Configuration
@PropertySource(value = "file:/var/config/config.json", factory = JsonPropertiesParseFactory.class, ignoreResourceNotFound = true)
public class AppConfig {

    private String userName;
    private String userPassword;

    @Autowired
    Environment environment;

    @PostConstruct
    public void init() {
        this.userName = environment.getProperty("dbUserName");
        this.userPassword = environment.getProperty("dbUserPassword");
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
