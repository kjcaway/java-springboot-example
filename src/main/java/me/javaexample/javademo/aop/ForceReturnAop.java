package me.javaexample.javademo.aop;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ForceReturnAop {

    private static final Logger logger = LogManager.getLogger(ForceReturnAop.class);

    @Around("@annotation(me.javaexample.javademo.annotation.ForceReturnAnnotation)")
    public Object forceReturn(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
            return "Converted!";
        } finally {
            logger.info(">>> finally");
        }
    }
}
