package kz.iitu.spring.lab01.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot!";
    }

    @GetMapping("/info")
    public Map<String, String> getInfo() {
        return Map.of(
                "app", "spring-lab-01",
                "developer", "Student Name",
                "status", "Running"
        );
    }

    @GetMapping("/sum")
    public Map<String, Object> calculate(
            @RequestParam int a,
            @RequestParam int b) {

        return Map.of(
                "a", a,
                "b", b,
                "sum", a + b,
                "difference", a - b,
                "product", a * b
        );
    }
}