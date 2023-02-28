package me.javaexample.javademo.api;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers // 1
public abstract class IntegrationTest {

    @Container // 1과 함께 써줌으로써 @BeforeAll을 만들어 mySQLContainer.start()를 해줄필요 없게해준다
    static GenericContainer mySQLContainer = new MySQLContainer("mysql:8.0.28")
            .withDatabaseName("testdbdb")
            .withUsername("root")
            .withPassword("root")
            .withInitScript("testcontainers/mysql/init.sql")          // 스크립트 실행 path는 기본적으로 src/test/resources를 참조
            .waitingFor(Wait.forHttp("/"));                     // 가용가능 한지 기다렸다가

}
