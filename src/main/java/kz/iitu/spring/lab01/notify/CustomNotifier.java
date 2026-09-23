package kz.iitu.spring.lab01.notify;

import jakarta.annotation.PostConstruct;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component("upper")
@Order(3)
public class CustomNotifier implements Notifier {

    @PostConstruct
    public void init() {
        System.out.println("CustomNotifier initialized");
    }

    @Override
    public String send(String message) {
        return message.toUpperCase();
    }

    @Override
    public String channel() {
        return "upper";
    }
}