package org.ivanina.examples.e1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@ComponentScan
@Import(RabbitConfiguration.class)
public class ApplicationE1 {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationE1.class, args);
    }
}
