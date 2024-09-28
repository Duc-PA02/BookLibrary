package com.booklibrary.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import java.util.Objects;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.booklibrary.bookservice","com.booklibrary.commonservice"})
public class BookserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookserviceApplication.class, args);
    }

}
