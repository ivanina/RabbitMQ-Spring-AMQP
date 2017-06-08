package org.ivanina.examples.e6_rpc;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    private Logger logger = Logger.getLogger(RabbitConfiguration.class);

    // connection factory
    @Bean
    public ConnectionFactory connectionFactory(){
        return new CachingConnectionFactory("localhost");
    }

    // amqp Admin
    @Bean
    public AmqpAdmin amqpAdmin(){
        return new RabbitAdmin( connectionFactory() );
    }

    // template
    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setQueue("query-example-6");
        template.setReplyTimeout(60 * 1000);
        return template;
    }

    // e2_queues
    @Bean
    public Queue queue(){
        return new Queue("query-example-6");
    }
}
