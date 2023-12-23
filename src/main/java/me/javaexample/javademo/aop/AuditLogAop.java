package me.javaexample.javademo.aop;


import me.javaexample.javademo.annotation.AuditLogAnnotation;
import me.javaexample.javademo.util.DateUtils;
import me.javaexample.javademo.util.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


@Aspect
@Component
public class AuditLogAop {

    private static final Logger logger = LogManager.getLogger(AuditLogAop.class);

    @Around("@annotation(me.javaexample.javademo.annotation.AuditLogAnnotation)")
    public Object writeAuditLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        // get audit action value
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        AuditLogAnnotation annotation = method.getAnnotation(AuditLogAnnotation.class);

        long start = System.currentTimeMillis();
        try {
            return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } finally {
            long end = System.currentTimeMillis();
            AuditLog auditLog = new AuditLog();
            auditLog.setMethod(request.getMethod());
            auditLog.setPath(request.getRequestURI());
            auditLog.setRemoteAddr(request.getRemoteAddr());
            auditLog.setTime(DateUtils.getNowTimeStr());
            auditLog.setSpendTime(end - start);
            auditLog.setAction(annotation.value());
            if (!request.getParameterMap().isEmpty()) {
                auditLog.setParams(JsonUtils.convertToJsonStr(request.getParameterMap()));
            }
            if (response != null) {
                auditLog.setResult(response.getStatus());
            }

            logger.info(JsonUtils.convertToJsonStr(auditLog));
        }
    }
}
