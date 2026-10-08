package kz.iitu.spring.lab01.aspect;

import kz.iitu.spring.lab01.audit.Audited;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class AuditAspect {

    private static final Logger log =
            LoggerFactory.getLogger(AuditAspect.class);

    @Around(
            value = "@annotation(audited)",
            argNames = "pjp,audited"
    )
    public Object audit(
            ProceedingJoinPoint pjp,
            Audited audited
    ) throws Throwable {

        String action = audited.action();
        String timestamp = LocalDateTime.now().toString();

        if (audited.logArguments()) {
            log.info(
                    "[AUDIT] start {} timestamp={} args={}",
                    action,
                    timestamp,
                    Arrays.toString(pjp.getArgs())
            );
        } else {
            log.info(
                    "[AUDIT] start {} timestamp={}",
                    action,
                    timestamp
            );
        }

        try {

            Object result = pjp.proceed();

            log.info(
                    "[AUDIT] {} success timestamp={}",
                    action,
                    LocalDateTime.now()
            );

            return result;

        } catch (Throwable ex) {

            log.error(
                    "[AUDIT] {} failure timestamp={} message={}",
                    action,
                    LocalDateTime.now(),
                    ex.getMessage()
            );

            throw ex;
        }
    }
}