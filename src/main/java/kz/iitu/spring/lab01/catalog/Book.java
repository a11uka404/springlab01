package kz.iitu.spring.lab01.catalog;

public record Book(
        Long id,
        String title,
        String author,
        int year
) {
}

