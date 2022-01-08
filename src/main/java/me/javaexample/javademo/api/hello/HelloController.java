package me.javaexample.javademo.api.hello;

import me.javaexample.javademo.annotation.AuditLogAnnotation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final Logger logger = LogManager.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String hello(){
        logger.info("request /hello!");
        return "Hello World!";
    }

    @GetMapping("/500")
    public ResponseEntity<String> error(){
        logger.error("occur error!");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something was wrong!");
    }

    @AuditLogAnnotation("test")
    @GetMapping("/audit")
    public String audit(){
        return "This is audit log test!";
    }
}
