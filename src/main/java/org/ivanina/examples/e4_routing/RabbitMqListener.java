package org.ivanina.examples.e4_routing;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(RabbitMqListener.class);

    @RabbitListener(queues = "query-example-4-1")
    public void worker1(String msg){
        logger.info("accepted on worker 1 : " + msg);
    }

    @RabbitListener(queues = "query-example-4-2")
    public void worker2(String msg){
        logger.info("accepted on worker 2 : " + msg);
    }
}
