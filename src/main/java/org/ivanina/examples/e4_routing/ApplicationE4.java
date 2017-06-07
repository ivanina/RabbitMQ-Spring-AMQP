package org.ivanina.examples.e4_routing;

import org.ivanina.examples.e3_publish_subscribe.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@ComponentScan
@Import(RabbitConfiguration.class)
public class ApplicationE4 {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationE4.class, args);
    }
}
