package org.ivanina.tutorial.e5_topics;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

abstract class AbstractService {

    public void runner(String[] bindingKeys) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare( Producer.EXCHANGE_NAME , "topic"); // set type of exchanger

        String queueName = channel.queueDeclare().getQueue();

        for (String bindingKey : bindingKeys){
            channel.queueBind(
                    queueName,              // <-- unimportant. Random temporally queue
                    Producer.EXCHANGE_NAME,  // <-- exchanger name
                    bindingKey              // <-- !!! important Routing Key. What we listen...
            );
        }
        System.out.println(" [*] "+getAnimalType()+" Waiting for messages.");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {

                String message = new String(body, "UTF-8");
                System.out.println(" [x] "+getAnimalType()+" caught '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

    abstract String getAnimalType();
}
