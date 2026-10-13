package kz.iitu.spring.lab01.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
@Order(4)
public class CallCounterAspect {

    private final ConcurrentHashMap<String, AtomicInteger> counters =
            new ConcurrentHashMap<>();

    @Before("kz.iitu.spring.lab01.aspect.Pointcuts.serviceOperation()")
    public void countCall(JoinPoint jp) {

        String methodName = jp.getSignature().getName();

        counters
                .computeIfAbsent(methodName, key -> new AtomicInteger())
                .incrementAndGet();
    }

    public Map<String, Integer> getStatistics() {

        Map<String, Integer> result = new ConcurrentHashMap<>();

        counters.forEach(
                (method, count) ->
                        result.put(method, count.get())
        );

        return result;
    }
}
