package org.ivanina.tutorial.e4_routing;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

abstract class Magician {
    static final String EXCHANGE_NAME = "direct_tasks";

    public void runner(String[] routingKeys) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct"); // set type of exchanger

        String queueName = channel.queueDeclare().getQueue();

        for (String routingKey : routingKeys){
            channel.queueBind(
                    queueName,      // <-- unimportant. Random temporally queue
                    EXCHANGE_NAME,  // <-- exchanger name
                    routingKey      // <-- !!! important Routing Key. What we listen...
            );
        }
        System.out.println(" [*] "+getMagicianType()+" Waiting for messages.");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {

                String message = new String(body, "UTF-8");
                logSaid(envelope.getRoutingKey(),message);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

    void logSaid(String key, String msg) {
        System.out.println(" [x] "+getMagicianType()+" Magician Received '" + key + "':'" + msg + "'");
    }

    abstract String getMagicianType();

}
