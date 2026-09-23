package kz.iitu.spring.lab01.web;
import kz.iitu.spring.lab01.notify.Notifier;
import org.springframework.beans.factory.annotation.Qualifier;
import kz.iitu.spring.lab01.scope.TicketOffice;
import kz.iitu.spring.lab01.lifecycle.LifecycleDemo;
import kz.iitu.spring.lab01.notify.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/lab2")
public class Lab2Controller {

    private final NotificationService notifications;
    private final LifecycleDemo lifecycle;
    private final TicketOffice ticketOffice;
    private final Notifier customNotifier;

    public Lab2Controller(
            NotificationService notifications,
            LifecycleDemo lifecycle,
            TicketOffice ticketOffice,
            @Qualifier("upper") Notifier customNotifier) {

        this.notifications = notifications;
        this.lifecycle = lifecycle;
        this.ticketOffice = ticketOffice;
        this.customNotifier = customNotifier;
    }

    @GetMapping("/notify")
    public Map<String, Object> notify(
            @RequestParam(defaultValue = "Hello") String text) {

        return Map.of(
                "primary", notifications.viaPrimary(text),
                "console", notifications.viaConsole(text),
                "all", notifications.viaAll(text),
                "beanNames", notifications.names()
        );
    }

    @GetMapping("/lifecycle")
    public List<String> lifecycle() {
        return lifecycle.events();
    }

    @GetMapping("/scopes")
    public Map<String, Object> scopes() {
        return ticketOffice.demo();
    }

    @GetMapping("/custom")
    public String custom(@RequestParam(defaultValue = "Hello") String text) {
        return customNotifier.send(text);
    }

}
