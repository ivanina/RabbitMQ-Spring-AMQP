package org.ivanina.examples.e1;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(RabbitMqListener.class);

    @RabbitListener(queues = "queue2")
    public void processQueue1(String message) {
        logger.info("-->>  Received (@RabbitListener) from queue 2: " + message);
    }
}
