package eu.senla.naumovich.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
    }

    @Around("controller()")
    public Object loggingRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object res = joinPoint.proceed();
        long finish = System.currentTimeMillis() - start;
        log.info("Invoked method {}, with args {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        log.info("Result of method {}", res);
        log.info("Execution time {} milliseconds", finish);
        return res;
    }
}
