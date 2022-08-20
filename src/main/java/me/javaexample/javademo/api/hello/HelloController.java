package me.javaexample.javademo.api.hello;

import lombok.RequiredArgsConstructor;
import me.javaexample.javademo.annotation.AuditLogAnnotation;
import me.javaexample.javademo.api.base.ApiResult;
import me.javaexample.javademo.api.hello.service.HelloService;
import me.javaexample.javademo.config.AppConfig;
import me.javaexample.javademo.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("${demo.api}/hello")
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);
    private final HelloService helloService;

    @Value("${otherservice.url:unknown}")
    String otherServiceUrl;

    @Autowired
    AppConfig appConfig;

    @GetMapping()
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

    @GetMapping("/json")
    public ApiResult<?> json(){
        Member member = new Member(1,"test");
        return ApiResult.ok(member);
    }

    @GetMapping("/exception/{value}")
    public ApiResult<?> exception(@PathVariable String value){
        try {
            int id = Integer.parseInt(value);
            Member member = new Member(id,"test");
            return ApiResult.ok(member);
        } catch (Exception ex){
            throw new CustomException("Something was wrong!");
        }
    }

    @GetMapping("/proptest")
    public ApiResult<?> proptest(){
        return ApiResult.ok(Map.of("otherServiceUrl", otherServiceUrl));
    }

    @GetMapping("/proptest2")
    public ApiResult<?> proptest2(){
        logger.info(appConfig.toString());
        return ApiResult.ok(Map.of("appConfig.userName", appConfig.getUserName(),
                                    "appConfig.userPassword", appConfig.getUserPassword()));
    }

    // Sample Response DTO
    class Member{
        public int id;
        public String name;

        Member(int id, String name){
            this.id = id;
            this.name = name;
        }
    }

    @GetMapping("/forceReturn")
    public ApiResult<?> forceReturn(){
        Object result = helloService.forceReturnExam();
        return ApiResult.ok(result);
    }

    @GetMapping("/anno")
    public ApiResult<?> anno(){
        Object result = helloService.anno();
        return ApiResult.ok(result);
    }
}
