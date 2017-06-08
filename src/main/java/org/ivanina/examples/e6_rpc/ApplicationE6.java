package org.ivanina.examples.e6_rpc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@ComponentScan
@Import(RabbitConfiguration.class)
public class ApplicationE6 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationE6.class,args);
    }
}
