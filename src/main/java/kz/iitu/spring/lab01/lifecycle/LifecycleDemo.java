package kz.iitu.spring.lab01.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class LifecycleDemo {
    private static final Logger log = LoggerFactory.getLogger(LifecycleDemo.class);
    private final DateTimeFormatter formatter;
    private final List<String> events = new ArrayList<>();

    // 1. Конструктор: вызывается первым при создании объекта
    public LifecycleDemo(DateTimeFormatter formatter) { // внедряет бин из FormatConfig (Задание 4)
        this.formatter = formatter;
        record("1. Constructor called");
    }

    // 2. Выполняется сразу после создания бина и внедрения зависимостей
    @PostConstruct
    void init() {
        record("2. @PostConstruct executed");
    }

    // 3. Выполняется перед уничтожением бина при остановке приложения
    @PreDestroy
    void shutdown() {
        record("3. @PreDestroy executed");
    }

    public List<String> events() {
        return List.copyOf(events);
    }

    private void record(String stage) {
        String line = LocalDateTime.now().format(formatter) + " " + stage;
        events.add(line);
        log.info("LIFECYCLE >> {}", line);
    }
}
