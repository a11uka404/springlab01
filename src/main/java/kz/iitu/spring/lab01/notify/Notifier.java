package kz.iitu.spring.lab01.notify;

public interface Notifier {
    String send(String message);
    String channel();
}