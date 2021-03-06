package org.ivanina.examples.e3_publish_subscribe;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(RabbitMqListener.class);

    @RabbitListener(queues = "query-example-3-1")
    public void worker1(String message) {
        logger.info("accepted on worker 1 : " + message);
    }

    @RabbitListener(queues = "query-example-3-2")
    public void worker2(String message) {
        logger.info("accepted on worker 2 : " + message);
    }
}
