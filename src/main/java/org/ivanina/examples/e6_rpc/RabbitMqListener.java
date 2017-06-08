package org.ivanina.examples.e6_rpc;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(RabbitMqListener.class);

    @RabbitListener(queues = "query-example-6")
    public String worker(String msg) throws InterruptedException {
        logger.info("Received on worker : " + msg);
        Thread.sleep(3000);
        return "Received on worker : " + msg.toUpperCase();
    }
}
