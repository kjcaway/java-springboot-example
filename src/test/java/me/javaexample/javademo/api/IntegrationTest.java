package me.javaexample.javademo.api;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;

@SpringBootTest(properties = "spring.profiles.active:test")
@ActiveProfiles("test")
@ContextConfiguration(initializers = IntegrationTest.ContainerPropertyInitializer.class)
public abstract class IntegrationTest {

    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.28")
            .withDatabaseName("testdbdb")
            .withUsername("root")
            .withPassword("root")
            .withInitScript("testcontainers/mysql/init.sql")        // 스크립트 실행 path는 기본적으로 src/test/resources를 참조
            .waitingFor(Wait.forHttp("/"))                                // 가용가능 한지 기다렸다가
            .withReuse(true);                                           // 재사용


    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @BeforeAll
    static void init() {
        if (!mySQLContainer.isRunning()) {
            mySQLContainer.start();
            var containerDelegate = new JdbcDatabaseDelegate(mySQLContainer, "");
            ScriptUtils.runInitScript(containerDelegate, "testcontainers/mysql/insert_test_data.sql");
        }
    }

    @AfterAll
    static void close() {
        mySQLContainer.close();
    }

    static class ContainerPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext context) {
            System.out.println("testcontainer logs: " + mySQLContainer.getLogs());
        }
    }
}
