package me.aikin.mockbean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MockBeanAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockBeanAnnotationApplication.class, args);
    }

}