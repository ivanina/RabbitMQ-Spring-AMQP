package org.ivanina.tutorial_one_java.hw;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver {
    public final static String Q_NAME = "queue-1";
    public static void main(String[] args) throws IOException, TimeoutException {
        // -1-
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        // -2-
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Q_NAME, false, false, false, null);

        System.out.println(" [*] Waiting for messages.");

        // -3-
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(Q_NAME, true, consumer);
    }
}
