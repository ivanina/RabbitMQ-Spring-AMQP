package org.ivanina.examples.e5_topics;

import org.ivanina.examples.e4_routing.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@ComponentScan
@Import(RabbitConfiguration.class)
public class ApplicationE5 {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationE5.class, args);
    }
}
