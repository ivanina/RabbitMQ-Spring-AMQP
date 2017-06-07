package org.ivanina.examples.e1;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;


public class RabbitConfiguration {
    Logger logger = Logger.getLogger(RabbitConfiguration.class);

    // Configure connect with RabbitMQ
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    // Declare Queue with name queue1
    @Bean
    public Queue myQueue1() {
        return new Queue("queue1");
    }

    // Declare Queue with name queue2
    @Bean
    public Queue myQueue2() {
        return new Queue("queue2");
    }

    // Declare the container for message listener
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer1() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("queue1");
        container.setMessageListener(new MessageListener() {

            //Here we catch messages from queue1
            public void onMessage(Message message) {
                logger.info("-->>  Received from queue1 : " + new String(message.getBody()));
            }
        });
        return container;
    }
}
