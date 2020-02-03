package com.bookstore;

import com.bookstore.ds2.BookService;
import com.bookstore.ds1.AuthorService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookService bookService;
    private final AuthorService authorService;

    public MainApplication(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("\n Saving an author (check the 'authorsdb' database) ...");
            authorService.persistAuthor();

            System.out.println("\n Saving a book (check the 'booksdb' database) ...");
            bookService.persistBook();
        };
    }
}

/*
 * 
 * 
 * 
 * 
 * How To Configure Two Data Sources With Two Connection Pools

Note: The best way to tune the connection pool parameters consist in using Flexy Pool by Vlad Mihalcea. Via Flexy Pool you can find the optim settings that sustain high-performance of your connection pool.

Description: This is a kickoff application that uses two data sources (two MySQL databases, one named authorsdb and one named booksdb) with two connection pools (each database uses its own HikariCP connection pool with different settings). Based on the above items is pretty easy to configure two connection pools from two different providers as well.

Key points:

in application.properties, configure two HikariCP connection pools via a two custom prefixes, e.g., app.datasource.ds1 and app.datasource.ds2
write a @Bean that returns the first DataSource and mark it as @Primary
write another @Bean that returns the second DataSource
configure two EntityManagerFactory and point out the packages to scan for each of them
put the domains and repositories for each EntityManager in the right packages
Output sample:
 * 
 * 
 * 
 */
