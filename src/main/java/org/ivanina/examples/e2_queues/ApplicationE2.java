package org.ivanina.examples.e2_queues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@ComponentScan
@Import(RabbitConfiguration.class)
public class ApplicationE2 {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationE2.class, args);
    }
}
