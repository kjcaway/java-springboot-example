package me.javaexample.javademo.api.hello;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import me.javaexample.javademo.annotation.AuditLogAnnotation;
import me.javaexample.javademo.api.base.ApiResult;
import me.javaexample.javademo.api.hello.dto.Member;
import me.javaexample.javademo.api.hello.service.HelloService;
import me.javaexample.javademo.config.AppConfig;
import me.javaexample.javademo.exception.CustomException;
import me.javaexample.javademo.threadlocal.ContextHolder;
import me.javaexample.javademo.threadlocal.RequestInfo;
import me.javaexample.javademo.util.ResourceUtils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
    public String hello() {
        logger.info("request /hello!");
        return "Hello World!";
    }

    @GetMapping("/500")
    public ResponseEntity<String> error() {
        logger.error("occur error!");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something was wrong!");
    }

    @AuditLogAnnotation("test")
    @GetMapping("/audit")
    public String audit() {
        return "This is audit log test!";
    }

    @GetMapping("/json")
    public ApiResult<?> json() {
        Member member = new Member(1, "test");
        return ApiResult.ok(member);
    }

    @GetMapping("/exception/{value}")
    public ApiResult<?> exception(@PathVariable String value) {
        try {
            int id = Integer.parseInt(value);
            Member member = new Member(id, "test");
            return ApiResult.ok(member);
        } catch (Exception ex) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Something was wrong!", ex);
        }
    }

    @GetMapping("/proptest")
    public ApiResult<?> proptest() {
        return ApiResult.ok(Map.of("otherServiceUrl", otherServiceUrl));
    }

    @GetMapping("/proptest2")
    public ApiResult<?> proptest2() {
        logger.info(appConfig.toString());
        return ApiResult.ok(Map.of("appConfig.userName", appConfig.getUserName(),
            "appConfig.userPassword", appConfig.getUserPassword()));
    }

    @GetMapping("/contextholder")
    public ApiResult<?> contextholder() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(
            RequestContextHolder.getRequestAttributes())).getRequest();
        String uuid = UUID.randomUUID().toString();
        ContextHolder.setRequestInfo(
            new RequestInfo(uuid, request.getRequestURL().toString(), request.getMethod()));

        // if destroy stored data
        // ContextHolder.removeRequestInfo();

        return ApiResult.ok(ContextHolder.getRequestInfo());
    }

    @GetMapping("/forceReturn")
    public ApiResult<?> forceReturn() {
        Object result = helloService.forceReturnExam();
        return ApiResult.ok(result);
    }

    /* test for me.anno */
//    @GetMapping("/mylog")
//    public ApiResult<?> mylog() {
//        Object result = helloService.myLogTest();
//        return ApiResult.ok(result);
//    }

    @GetMapping("/filedownload")
    public ResponseEntity<Resource> fileDownLoad() {
        try {
            File file = ResourceUtils.getFileFromResource("/views/templates/template-1.html");
            String contents = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

            logger.info("fileName: " + file.getName() + ", contents: \n" + contents);

            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .cacheControl(CacheControl.noCache())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .body(resource);
        } catch (IOException e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Something was wrong!", e);
        }
    }

    @GetMapping("/httpclient")
    public ApiResult<?> httpClientTest() throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {
        var ssl = SSLContext.getInstance("TLSv1.1");
        ssl.init(null, null, null);
        var httpClient = HttpClient.newBuilder().sslContext(ssl).build();
        var request = HttpRequest.newBuilder()
            .uri(URI.create("https://browserleaks.com/tls"))
            .header("Authorization", "Basic test")
            .GET().build();
        var resp = httpClient.send(request, BodyHandlers.ofString());

        return ApiResult.ok(resp.body());
    }
}
